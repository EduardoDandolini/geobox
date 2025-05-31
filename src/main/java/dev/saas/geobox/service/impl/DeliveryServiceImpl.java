package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.DeliveryRequest;
import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.dto.GamificationRequest;
import dev.saas.geobox.dto.WithdrawalRequest;
import dev.saas.geobox.enuns.DeliveryStatus;
import dev.saas.geobox.exception.NotFoundException;
import dev.saas.geobox.mapper.DeliveryMapper;
import dev.saas.geobox.models.Box;
import dev.saas.geobox.models.Delivery;
import dev.saas.geobox.models.Truck;
import dev.saas.geobox.models.User;
import dev.saas.geobox.repository.BoxRepository;
import dev.saas.geobox.repository.DeliveryRepository;
import dev.saas.geobox.repository.TruckRepository;
import dev.saas.geobox.repository.UserRepository;
import dev.saas.geobox.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository repository;
    private final TruckRepository truckRepository;
    private final UserRepository userRepository;
    private final BoxRepository boxRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();
    private final GamificationServiceImpl gamificationService;

    @Override
    public void save(DeliveryRequest request) {
        Truck truck = validateTruck(request.truckPlate());
        User user = validateUser(request.userId());
        Box box = validateBox(request.boxNumber());
        Delivery delivery = createDelivery(request, truck, user, box);

        GamificationRequest gamificationRequest = buildGamificationRequest(user, delivery);
        gamificationService.addPointsToUser(gamificationRequest);
    }

    private Truck validateTruck(String truckPlate) {
        return truckRepository.findByPlate(truckPlate)
                .orElseThrow(() -> new NotFoundException("Truck not found"));
    }

    private User validateUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private Box validateBox(String boxNumber) {
        return boxRepository.findByBoxNumber(boxNumber)
                .orElseThrow(() -> new NotFoundException("Box not found"));
    }

    private Delivery createDelivery(DeliveryRequest request, Truck truck, User user, Box box) {
        Point location = geometryFactory.createPoint(new Coordinate(request.longitude(), request.latitude()));
        return repository.save(Delivery.builder()
                .location(location)
                .truck(truck)
                .user(user)
                .box(box)
                .status(DeliveryStatus.ENTREGA.getId())
                .build());
    }

    private GamificationRequest buildGamificationRequest(User user, Delivery delivery) {
        return new GamificationRequest(user, delivery);
    }

    @Override
    public List<DeliveryResponse> findAllLocations() {
        return repository.findDeliveryStatus().stream()
                .map(DeliveryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Delivery findDeliveryById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Delivery not found"));
    }

    @Override
    public void withdrawal(WithdrawalRequest request) {
        Delivery delivery = findDeliveryById(request.deliveryId());

        delivery.setStatus(DeliveryStatus.RETIRADA.getId());
        repository.save(delivery);
    }

    @Override
    public byte[] generateSheet() {
        List<Delivery> deliveries = repository.findAll();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Relatório de entregas");
            createHeader(sheet);

            int rowNum = 1;
            for (Delivery delivery : deliveries) {
                createRow(sheet, rowNum++, delivery);
            }

            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar planilha", e);
        }
    }

    private void createHeader(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Código", "Localização", "Placa do caminhão", "Usuário", "Caixa", "Data de Criação"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
    }

    private void createRow(Sheet sheet, int rowNum, Delivery delivery) {
        Row row = sheet.createRow(rowNum);

        double latitude = delivery.getLocation().getY();
        double longitude = delivery.getLocation().getX();
        String address = getAddressFromCoordinates(latitude, longitude);

        int col = 0;
        row.createCell(col++).setCellValue(delivery.getId());
        row.createCell(col++).setCellValue(address);
        row.createCell(col++).setCellValue(delivery.getTruck().getPlate());
        row.createCell(col++).setCellValue(delivery.getUser().getName());
        row.createCell(col++).setCellValue(delivery.getBox().getBoxNumber());
        row.createCell(col).setCellValue(delivery.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    private String getAddressFromCoordinates(double latitude, double longitude) {
        String url = String.format(Locale.US,
                "https://nominatim.openstreetmap.org/reverse?lat=%f&lon=%f&format=json",
                latitude, longitude
        );

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "Geobox/1.0") // Nominatim requer um User-Agent
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonData = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonData);
                return jsonObject.getString("display_name");
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Address not found";
        }
    }
}
