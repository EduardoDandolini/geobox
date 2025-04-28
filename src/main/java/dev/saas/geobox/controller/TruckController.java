package dev.saas.geobox.controller;

import dev.saas.geobox.dto.TruckRequest;
import dev.saas.geobox.dto.TruckResponse;
import dev.saas.geobox.service.TruckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/truck")
@RequiredArgsConstructor
@Tag(name = "Truck", description = "Gerenciamento de caminhões")
public class TruckController {

    private final TruckService truckService;

    @PostMapping
    @Operation(summary = "Salvar caminhão", description = "Cria um novo caminhão e retorna os detalhes salvos")
    public ResponseEntity<TruckResponse> save(@RequestBody TruckRequest request) {
        return ResponseEntity.ok(truckService.save(request));
    }

    @GetMapping
    @Operation(summary = "Listar caminhões", description = "Retorna a lista de todos os caminhões cadastrados")
    public ResponseEntity<List<TruckResponse>> findAllTrucks() {
        return ResponseEntity.ok(truckService.findAllTrucks());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar caminhão por ID", description = "Retorna os detalhes de um caminhão específico pelo ID")
    public ResponseEntity<TruckResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(truckService.findById(id));
    }
}
