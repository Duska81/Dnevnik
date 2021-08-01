package com.iktpreobuka.dnevnik.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.dto.PredmetDTO;
import com.iktpreobuka.dnevnik.entities.PredmetEntity;

import com.iktpreobuka.dnevnik.repositories.PredmetRepository;

@RestController
@RequestMapping(path = "/Dnevnik/predmet")

public class PredmetController {

	@Autowired
	private PredmetRepository predmetRepository;

	// unesi novi predmet
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewPredmet(@Valid @RequestBody PredmetDTO noviPredmet, BindingResult result) {

		PredmetEntity predmet = new PredmetEntity();

		predmet.setNazivPredmeta(noviPredmet.getNazivPredmeta());
		predmet.setFond(noviPredmet.getFond());

		predmetRepository.save(predmet);
		return new ResponseEntity<>(predmet, HttpStatus.OK);
	}

	// izmeni predmet po id
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updatePredmet(@Valid @RequestBody PredmetDTO updatePredmet, BindingResult result,
			@PathVariable String id) {

		PredmetEntity predmet = predmetRepository.findById(Integer.parseInt(id)).get();

		predmet.setNazivPredmeta(updatePredmet.getNazivPredmeta());
		predmet.setFond(updatePredmet.getFond());
		return new ResponseEntity<PredmetEntity>(predmetRepository.save(predmet), HttpStatus.OK);
	}

	// svi predmeti
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllPredmeti() {

		return new ResponseEntity<Iterable<PredmetEntity>>(predmetRepository.findAll(), HttpStatus.OK);

	}

	// nadji predmet po id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getPredmetById(@PathVariable String id) {

		return new ResponseEntity<PredmetEntity>(predmetRepository.findById(Integer.parseInt(id)).get(), HttpStatus.OK);

	}

	// obrisi predmet
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deletePredmetById(@PathVariable String id) {

		PredmetEntity predmet = predmetRepository.findById(Integer.parseInt(id)).get();

		predmetRepository.delete(predmet);
		return new ResponseEntity<PredmetEntity>(predmet, HttpStatus.OK);

	}

}
