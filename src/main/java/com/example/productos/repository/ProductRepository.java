package com.example.productos.repository;

import com.example.productos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Producto,Long> {
}
