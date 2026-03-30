package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.model.Destinacija;

@Service
public interface DestinacijaService extends CrudService<Destinacija> {

	List<Destinacija> getDestinacijasByMesto(String mesto);
}
