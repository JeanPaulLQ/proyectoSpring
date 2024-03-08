package com.example.productos.models;

import jakarta.persistence.*;
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idEmpresa;
    @Column(name = "nombre",nullable = false)
    public String nombre;

}
