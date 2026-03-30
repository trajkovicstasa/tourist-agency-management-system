package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.model.Aranzman;
import rva.model.Hotel;
import rva.model.TuristickaAgencija;
import rva.repositories.AranzmanRepository;
import rva.services.AranzmanService;

@Component
public class AranzmanServiceImpl implements AranzmanService {
	
	@Autowired
	private AranzmanRepository repo;

	@Override
	public List<Aranzman> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
	}

	@Override
	public Aranzman create(Aranzman body) {
		return repo.save(body);
	}

	@Override
	public Optional<Aranzman> update(Aranzman body, long id) {
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
	public List<Aranzman> getAranzmansByPlacenoEquals(boolean placeno) {
		return repo.findByPlacenoEquals(placeno);
	}

	@Override
	public List<Aranzman> getAranzmansByHotel(Hotel hotel) {
		return repo.findByHotel(hotel);
	}

	@Override
	public List<Aranzman> getAranzmansByAgencija(TuristickaAgencija agencija) {
		return repo.findByAgencija(agencija);
	}

}
