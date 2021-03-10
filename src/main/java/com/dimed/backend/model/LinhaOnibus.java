package com.dimed.backend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LinhaOnibus implements Serializable {

    @Id
    @NotNull(message = "Id cannot be null")
    private Long id;
    @NotEmpty(message = "Codigo cannot be empty")
    private String codigo;
    @NotEmpty(message = "Nome cannot be empty")
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

