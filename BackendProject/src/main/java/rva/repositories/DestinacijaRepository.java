package rva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Destinacija;

public interface DestinacijaRepository extends JpaRepository<Destinacija, Long> {
	
	List<Destinacija> findByMestoLike(String mesto);

}
