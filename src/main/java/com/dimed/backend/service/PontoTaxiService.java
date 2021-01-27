package com.dimed.backend.service;

import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.model.PontoTaxi;
import org.springframework.dao.DataAccessException;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public interface PontoTaxiService {

    Collection<PontoTaxi> findAll();
    PontoTaxi findById(Long id);
    PontoTaxi save(PontoTaxi pontoTaxi);
    void delete(PontoTaxi pontoTaxi) throws DataAccessException;
    Collection<String> readPontoTaxiTxt() throws IOException;
    Collection<String>  writePontoTaxiTxt(String txt) throws IOException;


}
