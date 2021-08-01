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

import com.iktpreobuka.dnevnik.dto.RoditeljDTO;
import com.iktpreobuka.dnevnik.entities.RoditeljEntity;
import com.iktpreobuka.dnevnik.enumerations.EKorisnikRole;
import com.iktpreobuka.dnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.dnevnik.services.LogovanjeRoditelj;

@RestController
@RequestMapping(path = "/Dnevnik/roditelj")

public class RoditeljController {

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private RoditeljRepository roditeljRepository;

	@Autowired
	private LogovanjeRoditelj logovanjeRoditelj;

//unesi novog roditelja
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewRoditelj(@Valid @RequestBody RoditeljDTO noviRoditelj, BindingResult result) {

		RoditeljEntity roditelj = new RoditeljEntity();

		roditelj.setUsername(noviRoditelj.getUsername());
		roditelj.setPassword(noviRoditelj.getPassword());
		roditelj.setIme(noviRoditelj.getIme());
		roditelj.setPrezime(noviRoditelj.getPrezime());
		roditelj.setEmail(noviRoditelj.getEmail());
		roditelj.setKorisnikRole(EKorisnikRole.ROLE_RODITELJ);

		roditeljRepository.save(roditelj);
		return new ResponseEntity<>(roditelj, HttpStatus.OK);
	}

	// izmeni roditelja po id
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateRoditelj(@Valid @RequestBody RoditeljDTO updateRoditelj, BindingResult result,
			@PathVariable String id) {

		RoditeljEntity roditelj = roditeljRepository.findById(Integer.parseInt(id)).get();

		roditelj.setUsername(updateRoditelj.getUsername());
		roditelj.setPassword(updateRoditelj.getPassword());
		roditelj.setIme(updateRoditelj.getIme());
		roditelj.setPrezime(updateRoditelj.getPrezime());
		roditelj.setEmail(updateRoditelj.getEmail());
		return new ResponseEntity<RoditeljEntity>(roditeljRepository.save(roditelj), HttpStatus.OK);

	}

//svi roditelji
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllRoditelj() {
		try {
			return new ResponseEntity<Iterable<RoditeljEntity>>(roditeljRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//nadji roditelja po id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getRoditeljById(@PathVariable String id) {
		try {
			if (roditeljRepository.findById(Integer.parseInt(id)).isPresent()) {
				return new ResponseEntity<RoditeljEntity>(roditeljRepository.findById(Integer.parseInt(id)).get(),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError(1, "Roditelj nije pronadjen"), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//obrisi roditelja po id
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> obrisiRoditeljaById(@PathVariable String id) {
		try {
			if (roditeljRepository.existsById(Integer.parseInt(id))) {
				RoditeljEntity roditelj = roditeljRepository.findById(Integer.parseInt(id)).get();

				if (!roditelj.getUcenik().isEmpty()) {
					return new ResponseEntity<RESTError>(
							new RESTError(2, "Nije dozvoljeno brisanje roditelja sa prosledjenim id !"),
							HttpStatus.BAD_REQUEST);

				} else {
					roditeljRepository.delete(roditelj);
					return new ResponseEntity<RoditeljEntity>(roditelj, HttpStatus.OK);
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Roditelj sa prosledjenim id nije pronadjen"),
					HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Dogodila se greška"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<String> login(@RequestBody RoditeljDTO roditeljDTO) {
		RoditeljEntity roditeljEntity = logovanjeRoditelj.findByUsername(roditeljDTO.getUsername());

		return new ResponseEntity<>(roditeljEntity.getUsername(), HttpStatus.OK);

	}

}