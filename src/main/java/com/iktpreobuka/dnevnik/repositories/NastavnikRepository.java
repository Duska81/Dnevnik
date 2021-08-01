package com.iktpreobuka.dnevnik.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.NastavnikEntity;


public interface NastavnikRepository extends CrudRepository<NastavnikEntity, Integer>{

	NastavnikEntity findByUsername(String username);

	//public NastavnikEntity findByUsername(String username);

	//public NastavnikEntity findById(NastavnikEntity nastavnik);

	//public boolean exists(Integer nastavnikId);

	//public NastavnikEntity findOne(Integer nastavnikId);

	
}
