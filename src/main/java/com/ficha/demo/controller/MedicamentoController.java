package com.ficha.demo.controller;

import com.ficha.demo.model.Medicamento;
import com.ficha.demo.service.MedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CAPA DE CONTROLADOR (PRESENTACIÓN / API REST):
 * Expone los endpoints HTTP para interactuar con los recursos de Medicamentos.
 * Recibe peticiones HTTP, delega la lógica al servicio y retorna respuestas HTTP con códigos de estado.
 */
@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "*") // Permite peticiones desde aplicaciones frontend (Angular, React, etc.)
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    // Inyección de dependencias del servicio mediante constructor
    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    /**
     * GET /api/medicamentos
     * GET /api/medicamentos?nombre=paracetamol
     * Lista todos los medicamentos o filtra por nombre si se pasa el parámetro query.
     */
    @GetMapping
    public ResponseEntity<List<Medicamento>> listar(@RequestParam(required = false) String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            return ResponseEntity.ok(medicamentoService.buscarPorNombre(nombre));
        }
        return ResponseEntity.ok(medicamentoService.listarTodos());
    }

    /**
     * GET /api/medicamentos/{id}
     * Retorna un medicamento por su ID. Retorna 200 OK si existe, 404 NOT FOUND si no.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> obtenerPorId(@PathVariable Long id) {
        return medicamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/medicamentos
     * Crea un nuevo medicamento. Recibe el JSON en el cuerpo de la petición.
     * Retorna 201 CREATED y el objeto creado.
     */
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Medicamento medicamento) {
        try {
            Medicamento guardado = medicamentoService.guardar(medicamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * PUT /api/medicamentos/{id}
     * Actualiza los datos de un medicamento existente.
     * Retorna 200 OK con el medicamento actualizado o 404 NOT FOUND si el id no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        try {
            return medicamentoService.actualizar(id, medicamento)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * DELETE /api/medicamentos/{id}
     * Elimina un medicamento por su ID.
     * Retorna 204 NO CONTENT si se eliminó con éxito, o 404 NOT FOUND si no existía.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = medicamentoService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
