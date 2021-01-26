package com.dimed.backend.service;

import com.dimed.backend.model.Itinerario;
import com.dimed.backend.model.LinhaOnibus;

import java.io.IOException;
import java.util.Collection;

public interface ItinerarioService {

    Collection<Itinerario> findAll();
    void save(Itinerario itinerario);
    Itinerario findById(Long id);
    Itinerario findItinerario(String id) throws IOException;

}
