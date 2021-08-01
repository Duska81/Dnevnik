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


import com.iktpreobuka.dnevnik.dto.RazredDTO;
import com.iktpreobuka.dnevnik.entities.RazredEntity;
import com.iktpreobuka.dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.dnevnik.repositories.RazredRepository;

@RestController
@RequestMapping(path = "/Dnevnik/razred")

public class RazredController {

	@Autowired
	private RazredRepository razredRepository;

	@Autowired
	OdeljenjeRepository odeljenjeRepository;

	// unesi novi razred
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewRazred(@Valid @RequestBody RazredDTO noviRazred, BindingResult result) {

		RazredEntity razred = new RazredEntity();

		razred.setBrojRazreda(noviRazred.getBrojRazreda());
		razredRepository.save(razred);
		return new ResponseEntity<>(razred, HttpStatus.OK);

	}

	// izmeni razred po id
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateRazred(@Valid @RequestBody RazredDTO updateRazred, BindingResult result,
			@PathVariable String id) {

		RazredEntity razred = razredRepository.findById(Integer.parseInt(id)).get();

		razred.setBrojRazreda(updateRazred.getBrojRazreda());
		return new ResponseEntity<RazredEntity>(razredRepository.save(razred), HttpStatus.OK);
	}

	// obrisi razred po id
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteRazred(@PathVariable String id) {

		RazredEntity razred = razredRepository.findById(Integer.parseInt(id)).get();

		razredRepository.delete(razred);
		return new ResponseEntity<RazredEntity>(razred, HttpStatus.OK);

	}

	// svi razredi
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllRazred() {

		return new ResponseEntity<Iterable<RazredEntity>>(razredRepository.findAll(), HttpStatus.OK);

	}

	// razred po id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getRazredById(@PathVariable String id) {

		return new ResponseEntity<RazredEntity>(razredRepository.findById(Integer.parseInt(id)).get(), HttpStatus.OK);

	}

}