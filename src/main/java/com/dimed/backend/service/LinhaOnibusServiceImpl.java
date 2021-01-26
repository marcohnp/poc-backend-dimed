package com.dimed.backend.service;

import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.repository.LinhaOnibusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
public class LinhaOnibusServiceImpl implements LinhaOnibusService {

    private LinhaOnibusRepository linhaOnibusRepository;

    @Autowired
    private ApiExternaService serviceAPI;

    @Autowired
    public LinhaOnibusServiceImpl(LinhaOnibusRepository linhaOnibusRepository) {
        this.linhaOnibusRepository = linhaOnibusRepository;
    }

    @Override
    public Collection<LinhaOnibus> findAll() {
        List<LinhaOnibus> list = serviceAPI.createListLinhaOnibus();
        return list;
    }

    @Override
    public LinhaOnibus findById(Long id) {
        return linhaOnibusRepository.findById(id);
    }

    @Override
    public void save(LinhaOnibus linhaOnibus) {
        linhaOnibusRepository.save(linhaOnibus);
    }

    @Override
    public void delete(LinhaOnibus linhaOnibus) throws DataAccessException {
        linhaOnibusRepository.delete(linhaOnibus);
    }

    @Override
    public Collection<LinhaOnibus> findByName(String name) {
        Collection<LinhaOnibus> list = serviceAPI.getListLinhaOnibusByName(name);
        return list;
    }

    @Override
    public Collection<LinhaOnibus> findByCoord(double lat, double lng, double raio) throws IOException, InterruptedException {
        Collection<LinhaOnibus> list = serviceAPI.linhasPorRaio(lat, lng, raio);
        return list;
    }

}
