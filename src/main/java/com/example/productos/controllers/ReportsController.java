package com.example.productos.controllers;

import com.example.productos.models.EmpresaReporte;
import com.example.productos.models.Producto;
import com.example.productos.services.EmpresaService;
import com.example.productos.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportsController {
    @Autowired
    private ProductService service;
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/create")
    public void report(HttpServletResponse response, Model model) throws JRException, IOException {
        List<Producto> listProducts = service.lisProducts();

        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/primerReporte.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("usuario", "Jean Paul");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listProducts);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, params, dataSource);
        response.setContentType("application/x-pdf");
        response.addHeader("Content-disposition", "filename=ReporteCompra.pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @GetMapping("/create2")
    public void reportEmpresa(HttpServletResponse response, Model model) throws JRException, IOException {

        List<Object[]> list = empresaService.getEmpresaCantidad();
        List<EmpresaReporte> listEmpresa = new ArrayList<>();

        for (Object[] item : list) {
            Long id = Long.parseLong(item[0].toString());
            String nombre = (String) item[1];
            ;
            int cantidad = Integer.parseInt(item[2].toString());
            listEmpresa.add(new EmpresaReporte(id, nombre, cantidad));
        }
        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/reporte_cant.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("creador", "Jean Paul");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listEmpresa);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, params, dataSource);
        response.setContentType("application/x-pdf");
        response.addHeader("Content-disposition", "filename=ReporteEmpresa.pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
