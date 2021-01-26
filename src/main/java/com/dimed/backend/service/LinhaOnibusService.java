package com.dimed.backend.service;

import com.dimed.backend.model.LinhaOnibus;
import org.springframework.dao.DataAccessException;

import java.io.IOException;
import java.util.Collection;

public interface LinhaOnibusService {

    Collection<LinhaOnibus> findAll();
    LinhaOnibus findById(Long id);
    void save(LinhaOnibus linhaOnibus);
    void delete(LinhaOnibus linhaOnibus) throws DataAccessException;
    Collection<LinhaOnibus> findByName(String name);
    Collection<LinhaOnibus> findByCoord(double lat, double lng, double raio) throws IOException, InterruptedException;

}
