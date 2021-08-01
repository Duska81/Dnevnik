package com.iktpreobuka.dnevnik.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.dto.OcenaDTO;

import com.iktpreobuka.dnevnik.entities.OcenaEntity;

import com.iktpreobuka.dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.dnevnik.repositories.OcenaRepository;

import com.iktpreobuka.dnevnik.repositories.PredmetRepository;

@RestController
@RequestMapping(path = "/Dnevnik/ocena")

public class OcenaController {
	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OcenaRepository ocenaRepository;

	@Autowired
	PredmetRepository predmetRepository;

	@Autowired
	NastavnikRepository nastavnikRepository;

//sve ocene
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllOcena() {
		log.info("lista ocena: " );
		return new ResponseEntity<Iterable<OcenaEntity>>(ocenaRepository.findAll(), HttpStatus.OK);

	}

//nadji ocenu po id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getOcenaById(@PathVariable String id) {

		return new ResponseEntity<OcenaEntity>(ocenaRepository.findById(Integer.parseInt(id)).get(), HttpStatus.OK);

	}

//obrisi ocenu po id
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteOcena(@PathVariable String id) {

		OcenaEntity ocena = ocenaRepository.findById(Integer.parseInt(id)).get();

		ocenaRepository.delete(ocena);
		return new ResponseEntity<OcenaEntity>(ocena, HttpStatus.OK);
	}
	//@Secured("ROLE_NASTAVNIK")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewOcena(@Valid @RequestBody OcenaDTO novaOcena) {

		OcenaEntity ocena = new OcenaEntity();

		ocena.setVrednostOcene(novaOcena.getVrednostOcene());
		ocena.setDatum(novaOcena.getDatum());
		ocena.setPolugodiste(novaOcena.getPolugodiste());
		ocena.setPredajeOdeljenje(novaOcena.getIdPredajeOdeljenje());
		ocena.setUcenik(novaOcena.getIdUcenik());
		log.info("nova ocena je uneta");
		return new ResponseEntity<OcenaEntity>(ocenaRepository.save(ocena), HttpStatus.OK);

	}
}
