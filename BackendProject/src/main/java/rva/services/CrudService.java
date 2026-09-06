package rva.services;

import java.util.List;
import java.util.Optional;

/*
 OPTIONAL - KAKO NE BI DOBIJALI NULLPOINTEREXCEPTION
 */
public interface CrudService<T> {
	
	// <T> - znaci ne znam koji ce objekat jos biti ovde imamo genericki interfejs

	List<T> getAll(); // vraca sve objekte
	
	boolean existsById(long id); // proverava da li postoji objekat sa datim ID-jem 
	
	Optional<T> findById(long id); // pronalazi objekat po ID-u, al ne vraca direktno objekat nego optional posto ne zna dal takav objekat postoji
	
	T create (T body); // dodaje novi objekat u bazu
	
	Optional<T> update(T body, long id); // menja postojeci objekat, vraca optional za slucaj da objekat sa tim idjem ne postoji
	
	void delete(long id); // brise objekat
}
