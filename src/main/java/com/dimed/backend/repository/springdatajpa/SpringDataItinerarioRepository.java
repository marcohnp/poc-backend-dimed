package com.dimed.backend.repository.springdatajpa;

import com.dimed.backend.model.Itinerario;
import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.repository.ItinerarioRepository;
import com.dimed.backend.repository.LinhaOnibusRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;

public interface SpringDataItinerarioRepository extends ItinerarioRepository, Repository<Itinerario,Long> {


}
