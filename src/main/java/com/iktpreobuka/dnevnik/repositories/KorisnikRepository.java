package com.iktpreobuka.dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;


import com.iktpreobuka.dnevnik.entities.KorisnikEntity;

public interface KorisnikRepository extends CrudRepository<KorisnikEntity, Integer> {

}
