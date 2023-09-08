package com.example.springjasper.models;

import java.math.BigDecimal;

public class CompanyReportModel {
    private Integer codigo;
    private String razao;
    private String balanco;
    private String email;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getBalanco() {
        return balanco;
    }

    public void setBalanco(String balanco) {
        this.balanco = balanco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
