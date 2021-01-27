package com.dimed.backend.repository.springdatajpa;

import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.model.PontoTaxi;
import com.dimed.backend.repository.LinhaOnibusRepository;
import com.dimed.backend.repository.PontoTaxiRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataPontoTaxiRepository extends PontoTaxiRepository, Repository<PontoTaxi,Long> {

}
