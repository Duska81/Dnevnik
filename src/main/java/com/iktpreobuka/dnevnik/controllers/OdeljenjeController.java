package com.iktpreobuka.dnevnik.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.controllers.util.RESTError;
import com.iktpreobuka.dnevnik.dto.OdeljenjeDTO;
import com.iktpreobuka.dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.dnevnik.entities.RazredEntity;
import com.iktpreobuka.dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.dnevnik.repositories.RazredRepository;
import com.iktpreobuka.dnevnik.repositories.UcenikRepository;

@RestController
@RequestMapping(path = "/Dnevnik/odeljenje")

public class OdeljenjeController {

	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	@Autowired
	private RazredRepository razredRepository;

	// unesi novo odeljenje
	@RequestMapping(method = RequestMethod.POST, value = "/{id}")

	public ResponseEntity<?> addNewOdeljenjeRazred(@RequestBody OdeljenjeDTO novoOdeljenje) {

		RazredEntity razred = razredRepository.findById(novoOdeljenje.getIdRazred()).get();

		OdeljenjeEntity odeljenje = new OdeljenjeEntity();

		odeljenje.setBrojOdeljenja(novoOdeljenje.getBrojOdeljenja());
		odeljenje.setRazred(razred);

		odeljenjeRepository.save(odeljenje);
		log.info("novo odeljenje je uneto");
		return new ResponseEntity<>(odeljenje, HttpStatus.OK);
	}

	// izmeni odeljenje po id
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateRoditelj(@Valid @RequestBody OdeljenjeDTO updateOdeljenje, @PathVariable String id) {

		RazredEntity razred = razredRepository.findById(updateOdeljenje.getIdRazred()).get();

		OdeljenjeEntity odeljenje = odeljenjeRepository.findById(Integer.parseInt(id)).get();

		odeljenje.setBrojOdeljenja(updateOdeljenje.getBrojOdeljenja());
		odeljenje.setRazred(razred);
		
		log.info(" odeljenje je izmenjeno");
		return new ResponseEntity<OdeljenjeEntity>(odeljenjeRepository.save(odeljenje), HttpStatus.OK);

	}

	// nadji sva odeljenja
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllOdeljenja() {

		return new ResponseEntity<Iterable<OdeljenjeEntity>>(odeljenjeRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getOdeljenjeById(@PathVariable String id) {
		try {
			if (odeljenjeRepository.findById(Integer.parseInt(id)).isPresent()) {
				return new ResponseEntity<OdeljenjeEntity>(odeljenjeRepository.findById(Integer.parseInt(id)).get(),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError(1, "Odeljenje nije pronadjeno"),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// odeljenje na osnou id ucenika
	@RequestMapping(method = RequestMethod.GET, value = "/ucenik/{id}")
	public ResponseEntity<?> findOdeljenjeByUcenik(@PathVariable String id) {

		return new ResponseEntity<OdeljenjeEntity>(ucenikRepository.findById(Integer.parseInt(id)).get().getOdeljenje(),
				HttpStatus.OK);
	}

}