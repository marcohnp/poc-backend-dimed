package com.dimed.backend.repository.springdatajpa;

import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.repository.LinhaOnibusRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataLinhaOnibusRepository extends LinhaOnibusRepository, Repository<LinhaOnibus,Long> {

}
