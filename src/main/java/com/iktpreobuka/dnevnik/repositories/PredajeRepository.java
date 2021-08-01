package com.iktpreobuka.dnevnik.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.PredajeEntity;
import com.iktpreobuka.dnevnik.entities.PredmetEntity;

public interface PredajeRepository extends CrudRepository<PredajeEntity, Integer> {
	Optional<PredmetEntity> findById(PredajeEntity predaje);
	
}
