package rva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.model.Aranzman;
import rva.model.Hotel;
import rva.model.TuristickaAgencija;

// uvozimo model klase za ovaj repo


// oznacava da je ovo Repository komponenta
// Spring je automatski prepoznaje i moze da je koristi u servisima
@Repository
public interface AranzmanRepository extends JpaRepository<Aranzman, Long> {
/*
	- ovo je AranzmanRepository interfejs
	- radi sa entitetom Aranzman
	- primarni kljuc Aranzmana je long
	
	- Zbog JpaRepository, odmah dobijaš metode:
		findAll()
		findById(Long id)
		save(Aranzman aranzman)
		deleteById(Long id)
		existsById(Long id)
*/ 
	List<Aranzman> findByPlacenoEquals(boolean placeno);
	// automatski upit po polju placeno
	// true vraca sve placene aranzmane, false sve neplacene
	List<Aranzman> findByHotel(Hotel hotel);
	List<Aranzman> findByAgencija(TuristickaAgencija agencija);
}
