package com.example.productos.repository;

import com.example.productos.models.Empresa;
import com.example.productos.models.EmpresaReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa,Long> {
    @Query(value = "SELECT emp.id_empresa AS id, emp.nombre AS nombre, COUNT(p.id) AS cantidad " +
            "FROM empresa emp " +
            "LEFT JOIN producto p ON emp.id_empresa = p.id_empresa " +
            "GROUP BY emp.id_empresa", nativeQuery = true)
    List<Object[]> getEmpresasConCantidadProductos();
}
