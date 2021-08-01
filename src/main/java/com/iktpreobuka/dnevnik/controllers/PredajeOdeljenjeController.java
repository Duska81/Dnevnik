package com.iktpreobuka.dnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.entities.PredajeOdeljenjeEntity;
import com.iktpreobuka.dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.dnevnik.entities.PredajeEntity;
import com.iktpreobuka.dnevnik.repositories.PredajeOdeljenjeRepository;
import com.iktpreobuka.dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.dnevnik.repositories.PredajeRepository;

@RestController
@RequestMapping(path = "/Dnevnik/predajeOdeljenje")

public class PredajeOdeljenjeController {

	@Autowired
	private PredajeRepository predajeRepository;

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	@Autowired
	private PredajeOdeljenjeRepository predajeOdeljenjeRepository;

//unos novog predajeodeljenje
	@RequestMapping(method = RequestMethod.POST, value = "/predaje/{predajeId}/odeljenje/{odeljenjeId}")
	public ResponseEntity<?> addNewpredajeOdeljenje(@PathVariable Integer predajeId,
			@PathVariable Integer odeljenjeId) {

		PredajeOdeljenjeEntity predajeOdeljenje = new PredajeOdeljenjeEntity();

		PredajeEntity predaje = predajeRepository.findById(predajeId).get();
		OdeljenjeEntity odeljenje = odeljenjeRepository.findById(odeljenjeId).get();

		predajeOdeljenje.setOdeljenje(odeljenje);
		predajeOdeljenje.setPredaje(predaje);

		return new ResponseEntity<PredajeOdeljenjeEntity>(predajeOdeljenjeRepository.save(predajeOdeljenje),
				HttpStatus.OK);
	}

	// svi predajeOdeljenje
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllPredajeOdeljenje() {

		return new ResponseEntity<Iterable<PredajeOdeljenjeEntity>>(predajeOdeljenjeRepository.findAll(),
				HttpStatus.OK);
	}

	// predajeOdeljenje po id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getDrziNastavuById(@PathVariable String id) {

		return new ResponseEntity<PredajeOdeljenjeEntity>(
				predajeOdeljenjeRepository.findById(Integer.parseInt(id)).get(), HttpStatus.OK);

	}

}
