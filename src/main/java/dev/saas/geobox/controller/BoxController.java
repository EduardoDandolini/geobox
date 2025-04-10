package dev.saas.geobox.controller;

import dev.saas.geobox.dto.BoxRequest;
import dev.saas.geobox.dto.BoxResponse;
import dev.saas.geobox.service.impl.BoxServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/box")
@Tag(name = "Box", description = "Gerenciamento de caixas")
public class BoxController {

    private final BoxServiceImpl boxService;

    @PostMapping
    @Operation(summary = "Salvar caixa", description = "Cria uma nova caixa e retorna os detalhes salvos")
    public ResponseEntity<BoxResponse> save(BoxRequest request) {
        return ResponseEntity.ok(boxService.save(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar caixa por ID", description = "Retorna os detalhes de uma caixa específica pelo ID")
    public ResponseEntity<BoxResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(boxService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Listar caixas", description = "Retorna a lista de todas as caixas cadastradas")
    public ResponseEntity<Page<BoxResponse>> findAllBoxes(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(boxService.findAllBoxes(pageable));
    }

    @PutMapping
    @Operation(summary = "Atualizar caixa", description = "Atualiza os detalhes de uma caixa existente")
    public ResponseEntity<BoxResponse> update(Long id, BoxRequest request) {
        return ResponseEntity.ok(boxService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir caixa", description = "Exclui uma caixa existente")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boxService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
