package com.dimed.backend.service;

import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.repository.LinhaOnibusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LinhaOnibusServiceImpl implements LinhaOnibusService {

    private final LinhaOnibusRepository linhaOnibusRepository;

    @Autowired
    private ApiExternaService serviceAPI;

    @Autowired
    public LinhaOnibusServiceImpl(LinhaOnibusRepository linhaOnibusRepository) {
        this.linhaOnibusRepository = linhaOnibusRepository;
    }

    @Override
    public Collection<LinhaOnibus> findAll() {
        return serviceAPI.createListLinhaOnibus();
    }

    @Override
    public LinhaOnibus findById(Long id) {
        return linhaOnibusRepository.findById(id);
    }

    @Override
    public LinhaOnibus save(LinhaOnibus linhaOnibus) {
        return linhaOnibusRepository.save(linhaOnibus);
    }

    @Override
    public void delete(LinhaOnibus linhaOnibus) throws DataAccessException {
        linhaOnibusRepository.delete(linhaOnibus);
    }

    @Override
    public Collection<LinhaOnibus> findByName(String name) {
        return serviceAPI.getListLinhaOnibusByName(name);
    }

    @Override
    public Collection<LinhaOnibus> findByCoord(double lat, double lng, double raio) {
        return serviceAPI.linhasPorRaio(lat, lng, raio);
    }

}
