package com.dimed.backend.repository;

import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.model.PontoTaxi;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface PontoTaxiRepository {

    Collection<PontoTaxi> findAll();
    PontoTaxi findById(Long id);
    PontoTaxi save(PontoTaxi pontoTaxi);
    void delete(PontoTaxi pontoTaxi) throws DataAccessException;
}
