package rva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.model.Aranzman;
import rva.model.Hotel;
import rva.model.TuristickaAgencija;

@Repository
public interface AranzmanRepository extends JpaRepository<Aranzman, Long> {

	List<Aranzman> findByPlacenoEquals(boolean placeno);
	List<Aranzman> findByHotel(Hotel hotel);
	List<Aranzman> findByAgencija(TuristickaAgencija agencija);
}
