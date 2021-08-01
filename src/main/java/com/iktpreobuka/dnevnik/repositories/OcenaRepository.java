package com.iktpreobuka.dnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.OcenaEntity;

public interface OcenaRepository extends CrudRepository<OcenaEntity, Integer> {

	List<OcenaEntity> findOcenaByUcenik(int parseInt);


}
