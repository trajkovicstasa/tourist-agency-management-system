package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.model.Destinacija;

@Service
public interface DestinacijaService extends CrudService<Destinacija> {
	/*   
	 extends CrudService<Destinacija>
	 - ovim delom ovaj interfejs dobija sve metode u okviru CrudService interfejsa
	 */

	List<Destinacija> getDestinacijasByMesto(String mesto);
	// ovo je dodatna metoda
	// trazi destinacije po mestu
}
