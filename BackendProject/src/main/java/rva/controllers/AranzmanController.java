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

import rva.model.Aranzman;
import rva.model.Hotel;
import rva.model.TuristickaAgencija;
import rva.services.AranzmanService;
import rva.services.HotelService;
import rva.services.TuristickaAgencijaService;

@RestController
@CrossOrigin
public class AranzmanController {

	@Autowired
	private AranzmanService service;
	@Autowired
	private HotelService hotelService;
	@Autowired
	private TuristickaAgencijaService turistickaAgencijaService;
	
	// 1. Spring registruje http zahtev i prenosi ga odgovarajucem bean-u sa anotacijom @RestController
	// 2. U kontroleru postoji anotacija koja prihvata specifican zahtev po metodi i resursu!
	// 3. Zahtev se prosledjuje metodi cija se anotacija poklapa sa tim parametrima
	// 4. Izvrsva se logika te metode
	// 5. Kontroler vraca http odgovor koji se sastoji od headera, tela i statusnog koda
	
	@GetMapping("/aranzmans")
	public ResponseEntity<?> getAranzmans(@RequestParam(required = false) Boolean placeno,@RequestParam(required = false) Long id){
		
		if(placeno != null && id == null) {
			List<Aranzman> aranzmans = service.getAranzmansByPlacenoEquals(placeno);
			if(aranzmans.isEmpty()) return ResponseEntity.status(404)
					.body(String .format("Nijedan aranzman nije placen!", placeno));
			return ResponseEntity.ok(aranzmans);
				
		}else if(placeno == null && id != null) {
			Optional<Aranzman> aranzman = service.findById(id);
			if(aranzman.isEmpty()) return ResponseEntity.status(404)
					.body(String.format("Aranzman with ID: %s does not exist", id));
			return ResponseEntity.ok(aranzman);
			
		}else if(placeno != null && id != null){
			return ResponseEntity.status(400).body("Only one query parameter can be used!");
		}
		
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/aranzmans/hotel")
	public ResponseEntity<?> getAranzmanByHotel(@RequestParam Long hotelId){
		Optional<Hotel> hotel = hotelService.findById(hotelId);
		if(hotel.isEmpty()) {
			return ResponseEntity.status(404)
					.body(String.format("Hotel with an ID: %s does not exist",
							hotelId));
		}
		
		List<Aranzman> aranzmans = service.getAranzmansByHotel(hotel.get());
		if(aranzmans.isEmpty()) {
			return ResponseEntity.status(404)
					.body(String.format("Aranzman with hotel ID: %s does not exist.", hotelId));
		}
		
		return ResponseEntity.ok(aranzmans);
	}
	
	@GetMapping("/aranzmans/turistickaagencija")
	public ResponseEntity<?> getAranzmanByTuristickaAgencija(@RequestParam Long turistickaAgencijaId){
		Optional<TuristickaAgencija> turistickaAgencija = turistickaAgencijaService.findById(turistickaAgencijaId);
		if(turistickaAgencija.isEmpty()) {
			return ResponseEntity.status(404)
					.body(String.format("Turisticka agencija with an ID: %s does not exist",
							turistickaAgencijaId));
		}
		
		List<Aranzman> aranzmans = service.getAranzmansByAgencija(turistickaAgencija.get());
		if(aranzmans.isEmpty()) {
			return ResponseEntity.status(404)
					.body(String.format("Aranzman with turisticka agencija ID: %s does not exist.", turistickaAgencijaId));
		}
		
		return ResponseEntity.ok(aranzmans);
	}
	
	@PostMapping("/aranzmans")
	public ResponseEntity<?> createAranzman(@RequestBody Aranzman aranzman){
		Aranzman savedAranzman = service.create(aranzman);
		URI uri = URI.create(String.format("/aranzmans?id=%s", savedAranzman.getId()));
		return ResponseEntity.created(uri).body(savedAranzman);
	}
	
	@PutMapping("/aranzmans")
	public ResponseEntity<?> updateAranzman(@RequestBody Aranzman aranzman,
			@RequestParam Long id) {
		Optional<Aranzman> updatedAranzman = service.update(aranzman, id);
		if(updatedAranzman.isEmpty())return ResponseEntity.status(400)
				.body(String.format("Resource with requested ID: %s does not exist", id));
		return ResponseEntity.ok(updatedAranzman);
	}
	
	@DeleteMapping("/aranzmans")
	public ResponseEntity<?> deleteAranzman(@RequestParam Long id){
		if(!service.existsById(id))return ResponseEntity.status(400)
				.body(String.format("Resource with requested ID: %s does not exist", id));
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
