package com.iktpreobuka.dnevnik.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.PredmetEntity;

public interface PredmetRepository extends CrudRepository<PredmetEntity, Integer>{

	//Optional<PredmetEntity> findById(PredmetEntity predmet);

	//boolean exists(Integer predmetId);

	//PredmetEntity findOne(Integer predmetId);
	
}
