package rva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.TuristickaAgencija;

public interface TuristickaAgencijaRepository extends JpaRepository<TuristickaAgencija, Long> {
	
	List<TuristickaAgencija> findByNazivLike(String naziv);

}
