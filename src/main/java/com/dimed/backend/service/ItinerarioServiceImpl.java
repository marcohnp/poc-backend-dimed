package com.dimed.backend.service;

import com.dimed.backend.integration.ItinerarioIntegration;
import com.dimed.backend.model.Itinerario;
import com.dimed.backend.repository.ItinerarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@AllArgsConstructor
@Service
public class ItinerarioServiceImpl implements ItinerarioService {


    private final ItinerarioRepository itinerarioRepository;

    @Autowired
    private ApiExternaService serviceAPI;

    @Autowired
    public ItinerarioServiceImpl(ItinerarioRepository itinerarioRepository) {
        this.itinerarioRepository = itinerarioRepository;
    }

    @Override
    public Collection<Itinerario> findAll() {
        return itinerarioRepository.findAll();
    }

    @Override
    public Itinerario save(Itinerario itinerario) {
       return itinerarioRepository.save(itinerario);
    }

    @Override
    public void delete(Itinerario itinerario) throws DataAccessException {
        itinerarioRepository.delete(itinerario);
    }

    @Override
    public Itinerario findById(Long id) {
        return itinerarioRepository.findById(id);
    }

    @Override
    public Itinerario findItinerario(String id) {
        return serviceAPI.getItinerario(ItinerarioIntegration.catchUriItinerario(id));
    }


}
