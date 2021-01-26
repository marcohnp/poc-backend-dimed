package com.dimed.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class LinhaOnibus implements Serializable {

    @Id
    private Long id;
    private String codigo;
    private String nome;

    public LinhaOnibus(){
    }

    public LinhaOnibus(Long id, String codigo, String nome) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinhaOnibus)) return false;
        LinhaOnibus that = (LinhaOnibus) o;
        return id.equals(that.id) && codigo.equals(that.codigo) && nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nome);
    }
}

