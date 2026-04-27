package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.model.Destinacija;
import rva.model.Hotel;
import rva.repositories.HotelRepository;
import rva.services.HotelService;

@Component
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository repo;
	
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
