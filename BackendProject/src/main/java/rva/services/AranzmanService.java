package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.model.Aranzman;
import rva.model.Hotel;
import rva.model.TuristickaAgencija;

@Service
public interface AranzmanService extends CrudService<Aranzman> {

	List<Aranzman> getAranzmansByPlacenoEquals(boolean placeno);
	List<Aranzman> getAranzmansByHotel(Hotel hotel);
	List<Aranzman> getAranzmansByAgencija(TuristickaAgencija agencija);
	
}
