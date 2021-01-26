package com.dimed.backend.service;

import com.dimed.backend.model.Itinerario;
import com.dimed.backend.model.LinhaOnibus;
import org.springframework.dao.DataAccessException;

import java.io.IOException;
import java.util.Collection;

public interface ItinerarioService {

    Collection<Itinerario> findAll();
    void save(Itinerario itinerario);
    void delete(Itinerario itinerario) throws DataAccessException;
    Itinerario findById(Long id);
    Itinerario findItinerario(String id) throws IOException;

}
