package com.iktpreobuka.dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.RoditeljEntity;

public interface RoditeljRepository extends CrudRepository<RoditeljEntity, Integer> {

	public RoditeljEntity findByUsername(String username);
	//RoditeljEntity findOne(Integer id); 
	 
	}
