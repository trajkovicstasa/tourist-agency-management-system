package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.model.Destinacija;
import rva.model.Hotel;
import rva.repositories.HotelRepository;
import rva.services.HotelService;

// Ovo je komponenta aplikacije. Napravi njen objekat i upravljaj njime.
@Component// registruje klasu kao Spring Bean kako bi Spring mogao automatski da kreira njen objekat i koristi ga u aplikaciji
public class HotelServiceImpl implements HotelService {
//ako ova klasa ne implementira sve metode iz servisa javljace gresku
	@Autowired // Spring anotacija za Dependency injection tj ubrizgavanje zavisnosti
	// To znaci da Spring automatski pronalazi odgovarajuci objekat  i dodeljuje ga ovoj promenljivoj
	private HotelRepository repo;
	
	//Ova metoda prepisuje tj implementira metodu iz interfejsa
	// to je ista metoda samo sada ima konkretan kod
	@Override
	public List<Hotel> getAll() {
		return repo.findAll();
	}
 
	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
	}

	@Override
	public Hotel create(Hotel body) {
		return repo.save(body);
	}

	@Override
	public Optional<Hotel> update(Hotel body, long id) {
		if(existsById(id)) {
			body.setId(id);
			return Optional.of(repo.save(body));
		}
		return Optional.empty();
	}

	@Override
	public void delete(long id) {
		repo.deleteById(id);

	}

	@Override
	public List<Hotel> getHotelsByNaziv(String naziv) {
		return repo.findByNazivContainingIgnoreCase(naziv);
	}

	@Override
	public List<Hotel> getHotelsByDestinacija(Destinacija destinacija) {
		return repo.findByDestinacija(destinacija);
	}

	@Override
	public Optional<Hotel> findById(long id) {
		return repo.findById(id);
	}

}
