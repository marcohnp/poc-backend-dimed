package com.dimed.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LinhaOnibus implements Serializable {

    @Id
    private Long id;
    private String codigo;
    private String nome;


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

