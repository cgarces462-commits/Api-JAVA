package com.ficha.demo.service.impl;

import com.ficha.demo.model.Medicamento;
import com.ficha.demo.repository.MedicamentoRepository;
import com.ficha.demo.service.MedicamentoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CAPA DE SERVICIO (IMPLEMENTACIÓN DE LÓGICA DE NEGOCIO):
 * Contiene las reglas de negocio, validaciones y orquesta las llamadas al repositorio.
 */
@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    // Inyección de dependencias por constructor del repositorio
    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoServiceImpl(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public List<Medicamento> listarTodos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Optional<Medicamento> buscarPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    @Override
    public List<Medicamento> buscarPorNombre(String nombre) {
        return medicamentoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Medicamento> buscarPorLaboratorio(String laboratorio) {
        return medicamentoRepository.findByLaboratorioIgnoreCase(laboratorio);
    }

    @Override
    public Medicamento guardar(Medicamento medicamento) {
        // Regla de negocio: validar que el precio y stock no sean negativos
        if (medicamento.getPrecio() != null && medicamento.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio del medicamento no puede ser negativo.");
        }
        if (medicamento.getStock() != null && medicamento.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public Optional<Medicamento> actualizar(Long id, Medicamento medicamento) {
        return medicamentoRepository.findById(id).map(existente -> {
            existente.setNombre(medicamento.getNombre());
            existente.setDescripcion(medicamento.getDescripcion());
            existente.setPrecio(medicamento.getPrecio());
            existente.setStock(medicamento.getStock());
            existente.setLaboratorio(medicamento.getLaboratorio());
            return medicamentoRepository.save(existente);
        });
    }

    @Override
    public boolean eliminar(Long id) {
        if (medicamentoRepository.existsById(id)) {
            medicamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
