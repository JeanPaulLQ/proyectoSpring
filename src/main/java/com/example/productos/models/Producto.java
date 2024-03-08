package com.example.productos.models;

import jakarta.persistence.*;
import com.example.productos.models.Empresa;
import java.math.BigDecimal;
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "nombre",nullable = false)
    public String nombre;
    @Column(name = "precio",nullable = false)
    public BigDecimal precio;
    @ManyToOne
    @JoinColumn(name = "idEmpresa",nullable = false)
    public Empresa idEmpresa;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
