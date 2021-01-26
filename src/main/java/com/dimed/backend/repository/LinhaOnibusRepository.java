package com.dimed.backend.repository;

import com.dimed.backend.model.LinhaOnibus;

import java.util.Collection;

public interface LinhaOnibusRepository {

    Collection<LinhaOnibus> findAll();
    LinhaOnibus findById(Long id);
    void save(LinhaOnibus linhaOnibus);
}
