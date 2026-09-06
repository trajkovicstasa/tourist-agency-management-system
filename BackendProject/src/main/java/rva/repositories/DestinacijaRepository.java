	package rva.repositories;
	
	import java.util.List;

	// treba nam lista jer ce metoda vracati vise destinacija
	
	import org.springframework.data.jpa.repository.JpaRepository;
	
	import rva.model.Destinacija;
	
	public interface DestinacijaRepository extends JpaRepository<Destinacija, Long> {
		
		List<Destinacija> findByMestoContainingIgnoreCase(String mesto);
		// custom metoda
		// fingBy - pronadji po
		// Mesto - polje mesto
		// Containing - sadrzi tekst
		// IgnoreCase - ne razlikuje velika i mala slova
		
	
	}
