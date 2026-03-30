package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.model.TuristickaAgencija;
import rva.repositories.TuristickaAgencijaRepository;
import rva.services.TuristickaAgencijaService;

@Component
public class TuristickaAgencijaServiceImpl implements TuristickaAgencijaService {

	@Autowired
	private TuristickaAgencijaRepository repo;
	
	@Override
	public List<TuristickaAgencija> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
	}

	@Override
	public TuristickaAgencija create(TuristickaAgencija body) {
		return repo.save(body);
	}

	@Override
	public Optional<TuristickaAgencija> update(TuristickaAgencija body, long id) {
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
	public List<TuristickaAgencija> getTuristickaAgencijasByNaziv(String naziv) {
		return repo.findByNazivLike(naziv);
	}

}
