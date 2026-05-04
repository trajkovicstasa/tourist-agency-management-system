package rva.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import rva.model.TuristickaAgencija;
import rva.services.TuristickaAgencijaService;

@RestController
public class TuristickaAgencijaController {
	
	@Autowired
	private TuristickaAgencijaService service;
	
	@GetMapping("/turistickaagencijas")
	public ResponseEntity<?> getTuristickaAgencijas(@RequestParam(required = false) String naziv, @RequestParam(required = false) Long id){
		if(naziv != null && id == null) {
			List<TuristickaAgencija> turistickaAgencijas = service.getTuristickaAgencijasByNaziv(naziv);
			if(turistickaAgencijas.isEmpty()) return ResponseEntity.status(404)
					.body(String .format("Ne postoji turisticka agencija sa trazenim nazivom", naziv));
			return ResponseEntity.ok(turistickaAgencijas);
				
		}else if(naziv == null && id != null) {
			Optional<TuristickaAgencija> turistickaAgencija = service.findById(id);
			if(turistickaAgencija.isEmpty()) return ResponseEntity.status(404)
					.body(String.format("Destinacija with ID: %s does not exist", id));
			return ResponseEntity.ok(turistickaAgencija);
			
		}else if(naziv != null && id != null){
			return ResponseEntity.status(400).body("Only one query parameter can be used!");
		}
		
		return ResponseEntity.ok(service.getAll());
	}
	
	@PostMapping("/turistickaagencijas")
	public ResponseEntity<?> createTuristickaAgencija(@RequestBody TuristickaAgencija turistickaAgencija){
		TuristickaAgencija savedTuristickaAgencija = service.create(turistickaAgencija);
		URI uri = URI.create(String.format("/turistickaagencijas?id=%s", savedTuristickaAgencija.getId()));
		return ResponseEntity.created(uri).body(savedTuristickaAgencija);
	}
	
	@PutMapping("/turistickaagencijas")
	public ResponseEntity<?> updateTuristickaAgencija(@RequestBody TuristickaAgencija turistickaAgencija,
			@RequestParam Long id) {
		Optional<TuristickaAgencija> updatedTuristickaAgencija = service.update(turistickaAgencija, id);
		if(updatedTuristickaAgencija.isEmpty())return ResponseEntity.status(400)
				.body(String.format("Resource with requested ID: %s does not exist", id));
		return ResponseEntity.ok(updatedTuristickaAgencija);
	}
	
	@DeleteMapping("/turistickaagencijas")
	public ResponseEntity<?> deleteHotel(@RequestParam Long id){
		if(!service.existsById(id))return ResponseEntity.status(400)
				.body(String.format("Resource with requested ID: %s does not exist", id));
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


}
