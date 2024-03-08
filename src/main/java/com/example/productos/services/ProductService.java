package com.example.productos.services;

import com.example.productos.models.Producto;
import com.example.productos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    public List<Producto> lisProducts(){
        return repository.findAll();
    }
    public void createProduct(Producto producto){
        repository.save(producto);
    }
    public void deleteProduct(Long id){
        repository.deleteById(id);
    }
    public Producto getProductById(Long id){
        return repository.findById(id).orElse(null);
    }
}
