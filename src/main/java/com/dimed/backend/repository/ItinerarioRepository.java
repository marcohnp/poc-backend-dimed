package com.dimed.backend.repository;

import com.dimed.backend.model.Itinerario;
import com.dimed.backend.model.LinhaOnibus;

import java.util.Collection;

public interface ItinerarioRepository {

    Collection<Itinerario> findAll();
    void save(Itinerario itinerario);
    Itinerario findById(Long id);
}
