package com.dimed.backend.service;

import com.dimed.backend.model.Itinerario;
import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.repository.ItinerarioRepository;
import com.dimed.backend.repository.LinhaOnibusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
public class ItinerarioServiceImpl implements ItinerarioService {


    private ItinerarioRepository itinerarioRepository;

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
    public void save(Itinerario itinerario) {
        itinerarioRepository.save(itinerario);
    }

    @Override
    public Itinerario findById(Long id) {

        return itinerarioRepository.findById(id);
    }

    @Override
    public Itinerario findItinerario(String id) throws IOException {
        Itinerario itinerario = serviceAPI.getItinerario(serviceAPI.getUri(id));
        return itinerario;
    }


}
