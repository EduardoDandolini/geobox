package dev.saas.geobox.controller;

import dev.saas.geobox.dto.DeliveryRequest;
import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.dto.WithdrawalRequest;
import dev.saas.geobox.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
@Tag(name = "Delivery", description = "Gerenciamento de entregas")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/save")
    @Operation(summary = "Salvar entrega", description = "Cria uma nova entrega e retorna os detalhes salvos")
    public void save(@RequestBody DeliveryRequest request) {
        try {
            deliveryService.save(request);
            ResponseEntity.ok();
        } catch (Exception e) {
            ResponseEntity.badRequest();
        }
    }

    @GetMapping("/locations")
    @Operation(summary = "Lista de entregas", description = "Lista todas as entregas salvas")
    public ResponseEntity<List<DeliveryResponse>> findAllLocations() {
        return ResponseEntity.ok(deliveryService.findAllLocations());
    }

    @PostMapping("/withdrawal")
    @Operation(summary = "Retirada de entrega", description = "Permite coletar a caixa")
        public void withdrawal(@RequestBody WithdrawalRequest request) {
        try {
            deliveryService.withdrawal(request);
            ResponseEntity.ok();
        } catch (Exception e) {
            ResponseEntity.badRequest();
        }
    }

    @GetMapping("/generate-sheet")
    @Operation(summary = "Relatório", description = "Gerar relatórios em .xls")
    public ResponseEntity<byte[]> generateSheet() {
        byte[] fileContent = deliveryService.generateSheet();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "relatorio-entregas.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileContent);
    }
}
