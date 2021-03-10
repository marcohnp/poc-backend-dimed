package com.dimed.backend.repository;

import com.dimed.backend.model.LinhaOnibus;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface LinhaOnibusRepository {

    List<LinhaOnibus> findAll();

    LinhaOnibus findById(Long id);

    LinhaOnibus save(LinhaOnibus linhaOnibus);

    void delete(LinhaOnibus linhaOnibus) throws DataAccessException;
}
