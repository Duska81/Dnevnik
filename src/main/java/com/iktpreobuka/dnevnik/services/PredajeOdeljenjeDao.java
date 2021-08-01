package com.iktpreobuka.dnevnik.services;

import java.util.List;

import com.iktpreobuka.dnevnik.entities.PredajeOdeljenjeEntity;

public interface PredajeOdeljenjeDao {
	
	//public List<DrziNastavuEntity> findDrziNastavuByPredajeOdeljnje(Integer predajeId, Integer odeljenjeId);

	List<PredajeOdeljenjeEntity> findPredajeOdeljenjeByOdeljenje(Integer id);

	List<PredajeOdeljenjeEntity> findPredajeOdeljenjeByPredaje(Integer id);

}