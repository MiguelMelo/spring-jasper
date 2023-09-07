package com.example.springjasper.controllers;

import com.example.springjasper.models.ReportModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReportController {
    @GetMapping("/")
    public String hello() { return "Hello World!"; };

    @PostMapping("/report")
    public ResponseEntity<byte[]> createReport(@RequestBody List<ReportModel> reports) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<>();
        File file = ResourceUtils.getFile("classpath:reports/base.jasper");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
        JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), params, dataSource);

        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfStream));
        pdfExporter.exportReport();

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_PDF).body(pdfStream.toByteArray());
    }
}
