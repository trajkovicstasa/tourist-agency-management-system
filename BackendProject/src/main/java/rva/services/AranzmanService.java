package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.model.Aranzman;
import rva.model.Hotel;
import rva.model.TuristickaAgencija;

@Service
public interface AranzmanService extends CrudService<Aranzman> {

	List<Aranzman> getAranzmansByPlacenoEquals(boolean placeno); // vraca ili sve placene ili sve neplacene aranzmane
	List<Aranzman> getAranzmansByHotel(Hotel hotel); // vraca sve aranzmane jednog hotela
	List<Aranzman> getAranzmansByAgencija(TuristickaAgencija agencija); // vraca sve aranzmane jedne turisticke agencije
	
}
