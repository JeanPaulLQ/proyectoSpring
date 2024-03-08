package com.example.productos.services;

import com.example.productos.models.Empresa;
import com.example.productos.models.EmpresaReporte;
import com.example.productos.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;
    public List<Empresa> getAllEmpresas(){
        return repository.findAll();
    }
    public List<Object[]> getEmpresaCantidad(){
        return repository.getEmpresasConCantidadProductos();
    }
}
