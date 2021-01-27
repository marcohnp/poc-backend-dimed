package com.dimed.backend.service;

import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.model.PontoTaxi;
import com.dimed.backend.repository.LinhaOnibusRepository;
import com.dimed.backend.repository.PontoTaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Service
public class PontoTaxiServiceImpl implements PontoTaxiService {

    private PontoTaxiRepository pontoTaxiRepository;

    @Autowired
    public PontoTaxiServiceImpl(PontoTaxiRepository pontoTaxiRepository) {
        this.pontoTaxiRepository = pontoTaxiRepository;
    }

    @Override
    public Collection<PontoTaxi> findAll() {
        return pontoTaxiRepository.findAll();
    }

    @Override
    public PontoTaxi findById(Long id) {
        return pontoTaxiRepository.findById(id);
    }

    @Override
    public PontoTaxi save(PontoTaxi pontoTaxi) {
        return pontoTaxiRepository.save(pontoTaxi);
    }

    @Override
    public void delete(PontoTaxi pontoTaxi) throws DataAccessException {
        pontoTaxiRepository.delete(pontoTaxi);
    }

    @Override
    public Collection<String> readPontoTaxiTxt() throws IOException {
        int count = 0;
        List<String> list = new ArrayList<String>();
        String fileName = "PontoTaxi.txt";
        BufferedReader buffRead = new BufferedReader(new FileReader(fileName));
        String read = "";
        while (true) {
            if (read != null) {
                count++;
            } else
                break;
            read = buffRead.readLine();
            if(read != "" && read != null) {
                list.add(read);
            }

        }

        return list;

    }

    @Override
    public Collection<String> writePontoTaxiTxt(String txt) throws IOException {
        int count = 0;
        List<String> list = new ArrayList<String>();
        String fileName = "PontoTaxi.txt";
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(fileName, true));
        BufferedReader buffRead = new BufferedReader(new FileReader(fileName));
        String read = buffRead.readLine();
        String write = txt;
        buffWrite.write("\n"+ write);
        while (true) {
            if (read != null) {
                count++;
            } else
                break;
            read = buffRead.readLine();
            if(read != "" && read != null) {
                list.add(read);
            }
        }

        buffRead.close();
        buffWrite.close();

        return list;

    }


}
