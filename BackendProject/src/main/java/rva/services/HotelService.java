package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.model.Destinacija;
import rva.model.Hotel;

@Service
public interface HotelService extends CrudService<Hotel> {

	// dobija sve CRUD metode iz CrudService interfejsa
	List<Hotel> getHotelsByNaziv(String naziv); // trazi hotele po nazivu
	List<Hotel> getHotelsByDestinacija(Destinacija destinacija); // trazi sve hotele odredjene destinacije
}
