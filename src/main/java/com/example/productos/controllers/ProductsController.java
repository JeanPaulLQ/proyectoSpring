package com.example.productos.controllers;

import com.example.productos.models.Empresa;
import com.example.productos.models.Producto;
import com.example.productos.services.EmpresaService;
import com.example.productos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService service;
    @Autowired
    private EmpresaService empresaService;
    @GetMapping("/listProducts")
    public String listProducts(Model model){
        List<Producto> productList = service.lisProducts();
        model.addAttribute("productsList",productList);
        return "listProducts";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("empresas",empresaService.getAllEmpresas());
        return "registerProducts";
    }
    @PostMapping("/register")
    public String createProduct(@RequestParam("name")String name,
                                @RequestParam("precio") BigDecimal precio,
                                @RequestParam("empresa") String empresa ,Model model){
        Producto producto = new Producto();
        producto.nombre = name;
        producto.precio=precio;
        producto.idEmpresa = new Empresa();
        producto.idEmpresa.idEmpresa = Long.parseLong(empresa);
        service.createProduct(producto);
        return "redirect:/products/listProducts";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id,Model model){
        service.deleteProduct(id);
        List<Producto> productList = service.lisProducts();
        model.addAttribute("productsList",productList);
        return "listProducts";
    }
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        Producto product = service.getProductById(id);
        model.addAttribute("empresas",empresaService.getAllEmpresas());
        model.addAttribute("product",product);
        return "/editProduct";
    }
    @PostMapping("/edit")
    public String editProduct(@RequestParam("id")Long id,
                              @RequestParam("name")String name,
                              @RequestParam("precio") BigDecimal precio,
                              @RequestParam("empresa") String empresa ,
                              Model model){
        Producto producto = service.getProductById(id);
        producto.nombre = name;
        producto.precio=precio;
        producto.idEmpresa = new Empresa();
        producto.idEmpresa.idEmpresa = Long.parseLong(empresa);
        service.createProduct(producto);
        List<Producto> productList = service.lisProducts();
        model.addAttribute("productsList",productList);
        return "redirect:/products/listProducts";
    }
}
