package com.iktpreobuka.dnevnik.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.PredajeOdeljenjeEntity;

public interface PredajeOdeljenjeRepository extends CrudRepository<PredajeOdeljenjeEntity, Integer>{

	List<PredajeOdeljenjeEntity> findByOdeljenje(Integer id);

	List<PredajeOdeljenjeEntity> findByPredaje(Integer id);
	Optional<PredajeOdeljenjeEntity> findById(Integer id);

}
