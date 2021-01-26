package com.dimed.backend.repository;

import com.dimed.backend.model.LinhaOnibus;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface LinhaOnibusRepository {

    Collection<LinhaOnibus> findAll();
    LinhaOnibus findById(Long id);
    void save(LinhaOnibus linhaOnibus);
    void delete(LinhaOnibus linhaOnibus) throws DataAccessException;
}
