package com.dimed.backend.repository;

import com.dimed.backend.model.Itinerario;
import com.dimed.backend.model.LinhaOnibus;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface ItinerarioRepository {

    Collection<Itinerario> findAll();
    void delete(Itinerario itinerario) throws DataAccessException;
    Itinerario findById(Long id);
    Itinerario save(Itinerario itinerario);
}
