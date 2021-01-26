package com.dimed.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "itinerario")
public class Itinerario implements Serializable {

    @Id
    private Long idlinha;
    private String nome;
    private String codigo;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itinerario", fetch = FetchType.EAGER)
    private List<Coordendas> coordendas;

    public Itinerario(){
    }

    public Itinerario(Long idlinha, String nome, String codigo, List<Coordendas> coordendas) {
        this.idlinha = idlinha;
        this.nome = nome;
        this.codigo = codigo;
        this.coordendas = coordendas;
    }

    public Long getIdlinha() {
        return idlinha;
    }

    public void setIdlinha(Long idlinha) {
        this.idlinha = idlinha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Coordendas> getCoordendas() {
        return coordendas;
    }

    public void setCoordendas(List<Coordendas> coordendas) {
        this.coordendas = coordendas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Itinerario)) return false;
        Itinerario that = (Itinerario) o;
        return idlinha.equals(that.idlinha) && nome.equals(that.nome) && codigo.equals(that.codigo) && coordendas.equals(that.coordendas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idlinha, nome, codigo, coordendas);
    }
}
