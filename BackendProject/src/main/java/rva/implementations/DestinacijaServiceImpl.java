package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.model.Destinacija;
import rva.repositories.DestinacijaRepository;
import rva.services.DestinacijaService;

@Component
public class DestinacijaServiceImpl implements DestinacijaService {

	@Autowired
	private DestinacijaRepository repo;
	
	@Override
	public List<Destinacija> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
	}

	@Override
	public Destinacija create(Destinacija body) {
		return repo.save(body);
	}

	@Override
	public Optional<Destinacija> update(Destinacija body, long id) {
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
	public List<Destinacija> getDestinacijasByMesto(String mesto) {
		return repo.findByMestoContainingIgnoreCase(mesto);
	}

	@Override
	public Optional<Destinacija> findById(long id) {
		
		return repo.findById(id);
	}

}
