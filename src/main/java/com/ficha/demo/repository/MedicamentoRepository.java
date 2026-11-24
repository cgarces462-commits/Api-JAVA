package com.ficha.demo.repository;

import com.ficha.demo.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CAPA DE REPOSITORIO (ACCESO A DATOS):
 * Al extender JpaRepository<Medicamento, Long>, Spring Data JPA genera en tiempo de
 * ejecución la implementación con métodos como findAll(), findById(), save(), deleteById(), etc.
 */
@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    // Método de consulta derivado: Busca medicamentos cuyo nombre contenga el texto (ignorando mayúsculas/minúsculas)
    List<Medicamento> findByNombreContainingIgnoreCase(String nombre);

    // Método para filtrar por laboratorio
    List<Medicamento> findByLaboratorioIgnoreCase(String laboratorio);
}
