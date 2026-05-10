package rva;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import rva.model.TuristickaAgencija;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TuristickaAgencijaControllerIntegrationTest {
	
	static RestTemplate template = new RestTemplate();
	static String apiUrl = "http://localhost:8080/turistickaagencijas";
	static long largestId = 0;

	@Test
	@Order(1)
	void getAllTuristickaAgencijas() {
		ResponseEntity<List<TuristickaAgencija>> response = 
				template.exchange(apiUrl, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<TuristickaAgencija>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(0, response.getBody().size());
		
	}
	
	@Test
	@Order(2)
	void getAllTuristickaAgencijaById() {
		int id = 3;
		
		ResponseEntity<TuristickaAgencija> response = 
				template.exchange(apiUrl + "?id=", HttpMethod.GET, null, 
				TuristickaAgencija.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(id, response.getBody().getId());
		
	}
	
	@Test
	@Order(3)
	void getTuristickaAgencijaByNaziv() {
		String naziv = "GlobalTravel";
		
		ResponseEntity<List<TuristickaAgencija>> response = 
				template.exchange(apiUrl + "?naziv=" + naziv,
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<TuristickaAgencija>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		for(TuristickaAgencija ta: response.getBody()) {
			assertEquals(naziv, ta.getNaziv());
		}
		
	}
	
	@Test
	@Order(4)
	void getTuristickaAgencijaByAdresa() {
		String adresa = "Tolstojeva 10";
		
		ResponseEntity<List<TuristickaAgencija>> response = 
				template.exchange(apiUrl + "?adresa=" + adresa,
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<TuristickaAgencija>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		for(TuristickaAgencija ta: response.getBody()) {
			assertEquals(adresa, ta.getAdresa());
		}
		
	}
	
	@Test
	@Order(5)
	void getTuristickaAgencijaByKontakt() {
		String kontakt = "0645823654";
		
		ResponseEntity<List<TuristickaAgencija>> response = 
				template.exchange(apiUrl + "?kontakt=" + kontakt,
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<TuristickaAgencija>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		for(TuristickaAgencija ta: response.getBody()) {
			assertEquals(kontakt, ta.getKontakt());
		}
		
	}
	
	@Test
	@Order(6)
	void createTuristickaAgencija() {
		TuristickaAgencija agencija = new TuristickaAgencija();
		
		agencija.setNaziv("WorldTravel");;
		agencija.setAdresa("Gogoljeva 5");;
		
		HttpEntity<TuristickaAgencija> entity = new HttpEntity<TuristickaAgencija>(agencija);
		
		ResponseEntity<TuristickaAgencija> response = 
		template.exchange(apiUrl, HttpMethod.POST, entity, TuristickaAgencija.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals(agencija.getNaziv(), response.getBody().getNaziv());
		assertEquals(agencija.getAdresa(), response.getBody().getAdresa());
		
		if(largestId < response.getBody().getId()) largestId = response.getBody().getId();
	}
	
	@Test
	@Order(7)
	void updateHotel() {
		TuristickaAgencija agencija = new TuristickaAgencija();
		
		agencija.setNaziv("FairTravel");;
		agencija.setAdresa("Micurinova 42");;
		
		HttpEntity<TuristickaAgencija> entity = new HttpEntity<TuristickaAgencija>(agencija);
		
		ResponseEntity<TuristickaAgencija> response = 
		template.exchange(apiUrl + "?id=" + largestId, HttpMethod.PUT, entity, TuristickaAgencija.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals(agencija.getNaziv(), response.getBody().getNaziv());
		assertEquals(agencija.getAdresa(), response.getBody().getAdresa());
		
		
	}
	
	@Test
	@Order(8)
	void deleteTuristickaAgencija() {
		ResponseEntity<?> response = 
				template.exchange(apiUrl + "?id=" + largestId, HttpMethod.DELETE,
						null, Object.class);
		
		assertEquals(204, response.getStatusCode().value());
		assertNull(response.getBody());
		
		
	}
	
	
	
	

}
