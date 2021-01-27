package com.dimed.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
public class PontoTaxi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDoPonto;
    private Double latitude;
    private Double longitude;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant dataHoraCadastro = Instant.now();

    public PontoTaxi(){
    }

    public PontoTaxi(Long id, String nomeDoPonto, Double latitude, Double longitude, Instant dataHoraCadastro) {
        this.id = id;
        this.nomeDoPonto = nomeDoPonto;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDoPonto() {
        return nomeDoPonto;
    }

    public void setNomeDoPonto(String nomeDoPonto) {
        this.nomeDoPonto = nomeDoPonto;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Instant getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Instant dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PontoTaxi)) return false;
        PontoTaxi pontoTaxi = (PontoTaxi) o;
        return id.equals(pontoTaxi.id) && nomeDoPonto.equals(pontoTaxi.nomeDoPonto) && latitude.equals(pontoTaxi.latitude) && longitude.equals(pontoTaxi.longitude) && dataHoraCadastro.equals(pontoTaxi.dataHoraCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeDoPonto, latitude, longitude, dataHoraCadastro);
    }
}
