package com.iktpreobuka.dnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.controllers.util.RESTError;
import com.iktpreobuka.dnevnik.dto.PredajeDTO;
import com.iktpreobuka.dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.dnevnik.entities.PredajeEntity;
import com.iktpreobuka.dnevnik.entities.PredmetEntity;
import com.iktpreobuka.dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.dnevnik.repositories.PredajeRepository;
import com.iktpreobuka.dnevnik.repositories.PredmetRepository;

@RestController
@RequestMapping(path = "/Dnevnik/predaje")

public class PredajeController {

	@Autowired
	private PredajeRepository predajeRepository;

	@Autowired
	private PredmetRepository predmetRepository;

	@Autowired
	private NastavnikRepository nastavnikRepository;

	// unos novog predaje
	@RequestMapping(method = RequestMethod.POST, value = "/predmet/{predmetId}/nastavnik/{nastavnikId}")
	public ResponseEntity<?> addNewPredaje(@RequestBody PredajeDTO noviPredaje, @PathVariable Integer predmetId,
			@PathVariable Integer nastavnikId) {
		PredajeEntity predaje = new PredajeEntity();

		PredmetEntity predmet = predmetRepository.findById(predmetId).get();
		NastavnikEntity nastavnik = nastavnikRepository.findById(nastavnikId).get();

		predaje.setPredmet(predmet);
		predaje.setNastavnik(nastavnik);

		return new ResponseEntity<PredajeEntity>(predajeRepository.save(predaje), HttpStatus.OK);
	}

	// obrisi predaje po id
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deletePredajeById(@PathVariable String id) {
		try {
			if (predajeRepository.existsById(Integer.parseInt(id))) {
				PredajeEntity predaje = predajeRepository.findById(Integer.parseInt(id)).get();

				predajeRepository.delete(predaje);
				return new ResponseEntity<PredajeEntity>(predaje, HttpStatus.OK);
			}

			return new ResponseEntity<RESTError>(new RESTError(1, "Predaje sa prosledjenim id nije pronadjen"),
					HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Dogodila se greška"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// nadji sve predaje
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllPredaje() {
		try {
			return new ResponseEntity<Iterable<PredajeEntity>>(predajeRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// nadji predaje po id-ju
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getPredajeById(@PathVariable String id) {
		try {
			if (predajeRepository.findById(Integer.parseInt(id)).isPresent()) {
				return new ResponseEntity<PredajeEntity>(predajeRepository.findById(Integer.parseInt(id)).get(),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError(1, "Predaje id nije pronadjen"),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
