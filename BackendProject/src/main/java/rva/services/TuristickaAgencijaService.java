package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.model.TuristickaAgencija;

@Service
public interface TuristickaAgencijaService extends CrudService<TuristickaAgencija> {
	
	List<TuristickaAgencija> getTuristickaAgencijasByNaziv(String naziv);

}
