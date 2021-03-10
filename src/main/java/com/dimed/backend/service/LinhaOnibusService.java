package com.dimed.backend.service;

import com.dimed.backend.exception.exceptions.LinhasOnibusNotFoundException;
import com.dimed.backend.exception.exceptions.LinhaOnibusExistException;
import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.repository.LinhaOnibusRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LinhaOnibusService {

    private final LinhaOnibusRepository linhaOnibusRepository;

    private final ApiExternaService serviceAPI;

    public List<LinhaOnibus> findAll() {
        List<LinhaOnibus> linhaOnibus = serviceAPI.createListLinhaOnibus();
        if (linhaOnibus == null) throw new LinhasOnibusNotFoundException();
        linhaOnibus.forEach(this::save);
        return linhaOnibus;
    }

    public LinhaOnibus findById(Long id) {
        return linhaOnibusRepository.findById(id);
    }

    public List<LinhaOnibus> findByName(String name) {
        List<LinhaOnibus> linhaOnibusByName = serviceAPI.getListLinhaOnibusByName(name);
        if (linhaOnibusByName == null) {
            throw new LinhasOnibusNotFoundException();
        }
        return linhaOnibusByName;
    }

    public List<LinhaOnibus> findByCoordinates(double lat, double lng, double radius) {
        return serviceAPI.linhasPorRaio(lat, lng, radius);
    }

    public LinhaOnibus save(LinhaOnibus linhaOnibus) {
        return linhaOnibusRepository.save(linhaOnibus);
    }

    public void validateSave(LinhaOnibus linhaOnibus) {
        if (existLinhaOnibus(linhaOnibus, linhaOnibusRepository.findAll())) throw new LinhaOnibusExistException();
        linhaOnibusRepository.save(linhaOnibus);
    }

    public void validateUpdate(LinhaOnibus linhaOnibus) {
        if (!existLinhaOnibus(linhaOnibus, linhaOnibusRepository.findAll())) throw new LinhasOnibusNotFoundException();
        linhaOnibusRepository.save(linhaOnibus);
    }

    public boolean existLinhaOnibus(LinhaOnibus novaLinha, List<LinhaOnibus> linhasExistentes) {
        return linhasExistentes.stream().anyMatch(linha -> linha.getId().equals(novaLinha.getId()));
    }

    public void delete(Long id) throws DataAccessException {
        LinhaOnibus linha = linhaOnibusRepository.findById(id);
        if (linha == null) throw new LinhasOnibusNotFoundException();
        linhaOnibusRepository.delete(linha);
    }

}
