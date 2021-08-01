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

import com.iktpreobuka.dnevnik.controllers.util.RESTError;
import com.iktpreobuka.dnevnik.dto.RazredImaPredmetDTO;
import com.iktpreobuka.dnevnik.entities.PredmetEntity;
import com.iktpreobuka.dnevnik.entities.RazredEntity;
import com.iktpreobuka.dnevnik.entities.RazredImaPredmetEntity;
import com.iktpreobuka.dnevnik.repositories.PredmetRepository;
import com.iktpreobuka.dnevnik.repositories.RazredImaPredmetRepository;
import com.iktpreobuka.dnevnik.repositories.RazredRepository;

@RestController
@RequestMapping(path = "/Dnevnik/razredImaPredmet")

public class RazredImaPredmetController {

	@Autowired
	private PredmetRepository predmetRepository;

	@Autowired
	private RazredRepository razredRepository;

	@Autowired
	private RazredImaPredmetRepository razredImaPredmetRepository;

	// izmeni razredImaPredmet po id
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateRazredImaPredmet(@Valid @RequestBody RazredImaPredmetDTO updateRazredImaPredmet,
			BindingResult result, @PathVariable String id) {

		RazredImaPredmetEntity razredImaPredmet = razredImaPredmetRepository.findById(Integer.parseInt(id)).get();

		PredmetEntity predmet = predmetRepository.findById(updateRazredImaPredmet.getIdPredmet()).get();

		RazredEntity razred = razredRepository.findById(updateRazredImaPredmet.getIdRazred()).get();

		razredImaPredmet.setPredmet(predmet);
		razredImaPredmet.setRazred(razred);

		razredImaPredmetRepository.save(razredImaPredmet);
		return new ResponseEntity<RazredImaPredmetEntity>(razredImaPredmet, HttpStatus.OK);
	}

	// obrisi razredImaPredmet po id
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteRazredImaPredmetById(@PathVariable String id) {

		RazredImaPredmetEntity razredImaPredmet = razredImaPredmetRepository.findById(Integer.parseInt(id)).get();

		razredImaPredmetRepository.delete(razredImaPredmet);
		return new ResponseEntity<RazredImaPredmetEntity>(razredImaPredmet, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllRazredImaPredmet() {
		try {
			return new ResponseEntity<Iterable<RazredImaPredmetEntity>>(razredImaPredmetRepository.findAll(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getRazredImaPredmetById(@PathVariable String id) {

		return new ResponseEntity<RazredImaPredmetEntity>(
				razredImaPredmetRepository.findById(Integer.parseInt(id)).get(), HttpStatus.OK);

	}

	// novi unos
	@RequestMapping(method = RequestMethod.POST, value = "/predmet/{predmetId}/razred/{razredId}")
	public ResponseEntity<?> addNewRazredImaPredmet(@PathVariable Integer predmetId, @PathVariable Integer razredId) {
		RazredImaPredmetEntity razredImaPredmet = new RazredImaPredmetEntity();

		PredmetEntity predmet = predmetRepository.findById(predmetId).get();
		RazredEntity razred = razredRepository.findById(razredId).get();
		razredImaPredmet.setPredmet(predmet);
		razredImaPredmet.setRazred(razred);

		return new ResponseEntity<RazredImaPredmetEntity>(razredImaPredmetRepository.save(razredImaPredmet),
				HttpStatus.OK);
	}
}
