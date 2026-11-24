package com.ficha.demo.service;

import com.ficha.demo.model.Medicamento;

import java.util.List;
import java.util.Optional;

/**
 * CAPA DE SERVICIO (CONTRATO / INTERFAZ):
 * Define las operaciones de negocio disponibles para la entidad Medicamento.
 * Desacopla la lógica de negocio de la capa de presentación (controlador).
 */
public interface MedicamentoService {

    List<Medicamento> listarTodos();

    Optional<Medicamento> buscarPorId(Long id);

    List<Medicamento> buscarPorNombre(String nombre);

    List<Medicamento> buscarPorLaboratorio(String laboratorio);

    Medicamento guardar(Medicamento medicamento);

    Optional<Medicamento> actualizar(Long id, Medicamento medicamento);

    boolean eliminar(Long id);
}
