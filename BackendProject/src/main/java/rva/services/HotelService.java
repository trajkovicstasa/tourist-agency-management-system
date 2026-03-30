package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.model.Destinacija;
import rva.model.Hotel;

@Service
public interface HotelService extends CrudService<Hotel> {

	List<Hotel> getHotelsByNaziv(String naziv);
	List<Hotel> getHotelsByDestinacija(Destinacija destinacija);
}
