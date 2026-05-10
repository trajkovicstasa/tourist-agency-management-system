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

import rva.model.Destinacija;
import rva.model.Hotel;
import rva.services.DestinacijaService;
import rva.services.HotelService;

@RestController
public class HotelController {
	
	@Autowired
	private HotelService service;
	@Autowired
	private DestinacijaService destinacijaService;
	
	@GetMapping("/hotels")
	public ResponseEntity<?> getHotels(@RequestParam(required = false) String naziv, @RequestParam(required = false) Long id){
		if(naziv != null && id == null) {
			List<Hotel> hotels = service.getHotelsByNaziv(naziv);
			if(hotels.isEmpty()) return ResponseEntity.status(404)
					.body(String .format("Ne postoji hotel sa trazenim nazivom", naziv));
			return ResponseEntity.ok(hotels);
				
		}else if(naziv == null && id != null) {
			Optional<Hotel> hotel = service.findById(id);
			if(hotel.isEmpty()) return ResponseEntity.status(404)
					.body(String.format("Hotel with ID: %s does not exist", id));
			return ResponseEntity.ok(hotel);
			
		}else if(naziv != null && id != null){
			return ResponseEntity.status(400).body("Only one query parameter can be used!");
		}
		
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/hotels/destinacija")
	public ResponseEntity<?> getHotelByDestinacija(@RequestParam Long destinacijaId){
		Optional<Destinacija> destinacija = destinacijaService.findById(destinacijaId);
		if(destinacija.isEmpty()) {
			return ResponseEntity.status(404)
					.body(String.format("Destinacija with an ID: %s does not exist",
							destinacijaId));
		}
		
		List<Hotel> hotels = service.getHotelsByDestinacija(destinacija.get());
		if(hotels.isEmpty()) {
			return ResponseEntity.status(404)
					.body(String.format("Hotel with destinacija ID: %s does not exist.", destinacijaId));
		}
		
		return ResponseEntity.ok(hotels);
	}
	
	
	@PostMapping("/hotels")
	public ResponseEntity<?> createHotel(@RequestBody Hotel hotel){
		Hotel savedHotel = service.create(hotel);
		URI uri = URI.create(String.format("/hotels?id=%s", savedHotel.getId()));
		return ResponseEntity.created(uri).body(savedHotel);
	}
	
	@PutMapping("/hotels")
	public ResponseEntity<?> updateHotel(@RequestBody Hotel hotel,
			@RequestParam Long id) {
		Optional<Hotel> updatedHotel = service.update(hotel, id);
		if(updatedHotel.isEmpty())return ResponseEntity.status(400)
				.body(String.format("Resource with requested ID: %s does not exist", id));
		return ResponseEntity.ok(updatedHotel);
	}
	
	@DeleteMapping("/hotels")
	public ResponseEntity<?> deleteHotel(@RequestParam Long id){
		if(!service.existsById(id))return ResponseEntity.status(400)
				.body(String.format("Resource with requested ID: %s does not exist", id));
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
