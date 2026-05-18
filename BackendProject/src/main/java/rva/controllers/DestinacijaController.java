package rva.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import rva.model.Destinacija;

import rva.services.DestinacijaService;


@RestController
@CrossOrigin
public class DestinacijaController {
	
	@Autowired
	private DestinacijaService service;
	
	
	@GetMapping("/destinacijas")
	public ResponseEntity<?> getDestinacijas(@RequestParam(required = false) String mesto, @RequestParam(required = false) Long id){
		if(mesto != null && id == null) {
			List<Destinacija> destinacijas = service.getDestinacijasByMesto(mesto);
			if(destinacijas.isEmpty()) return ResponseEntity.status(404)
					.body(String .format("Ne postoji destinacija za trazeno mesto", mesto));
			return ResponseEntity.ok(destinacijas);
				
		}else if(mesto == null && id != null) {
			Optional<Destinacija> destinacija = service.findById(id);
			if(destinacija.isEmpty()) return ResponseEntity.status(404)
					.body(String.format("Destinacija with ID: %s does not exist", id));
			return ResponseEntity.ok(destinacija);
			
		}else if(mesto != null && id != null){
			return ResponseEntity.status(400).body("Only one query parameter can be used!");
		}
		
		return ResponseEntity.ok(service.getAll());
	}
	
	
	
	@PostMapping("/destinacijas")
	public ResponseEntity<?> createDestinacija(@RequestBody Destinacija destinacija){
		Destinacija savedDestinacija = service.create(destinacija);
		URI uri = URI.create(String.format("/destinacijas?id=%s", savedDestinacija.getId()));
		return ResponseEntity.created(uri).body(savedDestinacija);
	}
	
	@PutMapping("/destinacijas")
	public ResponseEntity<?> updateDestinacija(@RequestBody Destinacija destinacija,
			@RequestParam Long id) {
		Optional<Destinacija> updatedDestinacija = service.update(destinacija, id);
		if(updatedDestinacija.isEmpty())return ResponseEntity.status(400)
				.body(String.format("Resource with requested ID: %s does not exist", id));
		return ResponseEntity.ok(updatedDestinacija);
	}
	
	@DeleteMapping("/destinacijas")
	public ResponseEntity<?> deleteDestinacija(@RequestParam Long id){
		if(!service.existsById(id))return ResponseEntity.status(400)
				.body(String.format("Resource with requested ID: %s does not exist", id));
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
