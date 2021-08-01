package com.iktpreobuka.dnevnik.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.UcenikEntity;

public interface UcenikRepository extends CrudRepository<UcenikEntity, Integer> {

	public List<UcenikEntity> findUcenikByodeljenje(Integer id) ;

	public Iterable<UcenikEntity> findAllById(Integer id);



	public boolean existsById(UcenikEntity ucenikId);

	public UcenikEntity findByUsername(String username);

	//public Optional<UcenikEntity> findById(String id);

	//public boolean exists(Integer ucenikId);

	//public UcenikEntity findOne(Integer ucenikId);

	

	
	//UcenikEntity findOne(Integer ucenik);

}
