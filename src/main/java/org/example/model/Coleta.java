package org.example.model;

import java.sql.Date;

public class Coleta {

    private Long id;
    private String tipoResiduo;
    private double pesoKg;
    private Date dataColeta;
    private String statusColeta;
    private int pontosGerados;

    public Coleta() {
    }

    public Coleta(Long id, String tipoResiduo, double pesoKg, Date dataColeta, String statusColeta, int pontosGerados) {
        this.id = id;
        this.tipoResiduo = tipoResiduo;
        this.pesoKg = pesoKg;
        this.dataColeta = dataColeta;
        this.statusColeta = statusColeta;
        this.pontosGerados = pontosGerados;
    }


    public int calcularPontosEcology() {
        int fator = 10;
        if (this.tipoResiduo != null) {
            if (this.tipoResiduo.equalsIgnoreCase("ELETRONICO")) {
                fator = 50;
            } else if (this.tipoResiduo.equalsIgnoreCase("PLASTICO") || this.tipoResiduo.equalsIgnoreCase("METAL")) {
                fator = 20;
            }
        }
        this.pontosGerados = (int) (this.pesoKg * fator);
        return this.pontosGerados;
    }


    public boolean validarPesoMinimo(double pesoMinimo) {
        return this.pesoKg >= pesoMinimo;
    }


    public void processarDescarte() {
        if ("PENDENTE".equalsIgnoreCase(this.statusColeta)) {
            this.statusColeta = "PROCESSADO";
            this.calcularPontosEcology();
        }
    }

    public double estimarReducaoCO2() {
        return this.pesoKg * 1.5;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoResiduo() { return tipoResiduo; }
    public void setTipoResiduo(String tipoResiduo) { this.tipoResiduo = tipoResiduo; }

    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }

    public Date getDataColeta() { return dataColeta; }
    public void setDataColeta(Date dataColeta) { this.dataColeta = dataColeta; }

    public String getStatusColeta() { return statusColeta; }
    public void setStatusColeta(String statusColeta) { this.statusColeta = statusColeta; }

    public int getPontosGerados() { return pontosGerados; }
    public void setPontosGerados(int pontosGerados) { this.pontosGerados = pontosGerados; }

    @Override
    public String toString() {
        return "Coleta [id=" + id + "\ntipo=" + tipoResiduo + "\npeso=" + pesoKg + "kg, status=" + statusColeta + "\npontos=" + pontosGerados + "]";
    }
}