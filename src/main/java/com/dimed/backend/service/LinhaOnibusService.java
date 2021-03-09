package com.dimed.backend.service;

import com.dimed.backend.model.LinhaOnibus;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface LinhaOnibusService {

    Collection<LinhaOnibus> findAll();
    LinhaOnibus findById(Long id);
    LinhaOnibus save(LinhaOnibus linhaOnibus);
    void delete(LinhaOnibus linhaOnibus) throws DataAccessException;
    Collection<LinhaOnibus> findByName(String name);
    Collection<LinhaOnibus> findByCoord(double lat, double lng, double raio);

}
