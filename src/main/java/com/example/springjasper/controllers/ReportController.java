package com.example.springjasper.controllers;

import com.example.springjasper.models.CompanyReportModel;
import com.example.springjasper.models.ReportModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReportController {
    @GetMapping("/")
    public String hello() { return "Hello World!"; }

    @PostMapping("/report")
    public ResponseEntity<byte[]> createReport(@RequestBody List<ReportModel> reports) throws IOException, JRException {
        Map<String, Object> params = new HashMap<>();
        ClassPathResource file = new ClassPathResource("reports/base.jasper");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
        JasperPrint jasperPrint = JasperFillManager.fillReport(file.getInputStream(), params, dataSource);

        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfStream));
        pdfExporter.exportReport();

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_PDF).body(pdfStream.toByteArray());
    }

    @PostMapping("/report/companies")
    public ResponseEntity<byte[]> createCompaniesReport(@RequestBody List<CompanyReportModel> reports) throws IOException, JRException {
        Map<String, Object> params = new HashMap<>();
        ClassPathResource file = new ClassPathResource("reports/companies.jasper");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
        JasperPrint jasperPrint = JasperFillManager.fillReport(file.getInputStream(), params, dataSource);

        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfStream));
        pdfExporter.exportReport();

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_PDF).body(pdfStream.toByteArray());
    }
}
