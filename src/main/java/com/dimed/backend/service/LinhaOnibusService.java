package com.dimed.backend.service;

import com.dimed.backend.exception.exceptions.LinhasOnibusNotFound;
import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.repository.LinhaOnibusRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class LinhaOnibusService {

    private final LinhaOnibusRepository linhaOnibusRepository;

    private final ApiExternaService serviceAPI;

    public List<LinhaOnibus> findAll() {
        List<LinhaOnibus> linhaOnibus = serviceAPI.createListLinhaOnibus();
        if (linhaOnibus == null) throw new LinhasOnibusNotFound();
        linhaOnibus.forEach(this::save);
        return linhaOnibus;
    }

    public LinhaOnibus findById(Long id) {
        return linhaOnibusRepository.findById(id);
    }

    public List<LinhaOnibus> findByName(String name) {
        List<LinhaOnibus> linhaOnibusByName = serviceAPI.getListLinhaOnibusByName(name);
        if (linhaOnibusByName == null) {
            throw new LinhasOnibusNotFound();
        }
        return linhaOnibusByName;
    }

    public Collection<LinhaOnibus> findByCoord(double lat, double lng, double raio) {
        return serviceAPI.linhasPorRaio(lat, lng, raio);
    }

    public LinhaOnibus save(LinhaOnibus linhaOnibus) {
        return linhaOnibusRepository.save(linhaOnibus);
    }

    public void delete(LinhaOnibus linhaOnibus) throws DataAccessException {
        linhaOnibusRepository.delete(linhaOnibus);
    }

}
