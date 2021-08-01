package com.iktpreobuka.dnevnik.services;

import java.util.List;

import com.iktpreobuka.dnevnik.entities.OcenaEntity;

public interface OcenaDao {

	public List<OcenaEntity> findOcenaByUcenik(Integer id);

}
