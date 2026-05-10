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

import rva.model.Aranzman;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AranzmanControllerIntegrationTest {
	
	static RestTemplate template = new RestTemplate();
	static String apiUrl = "http://localhost:8080/aranzmans";
	static long largestId = 0;

	@Test
	@Order(1)
	void getAllAranzmans() {
		ResponseEntity<List<Aranzman>> response = 
				template.exchange(apiUrl, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Aranzman>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(0, response.getBody().size());
	}

	@Test
	@Order(2)
	void getAllAranzmanById() {
		int id = 3;
		
		ResponseEntity<Aranzman> response = 
				template.exchange(apiUrl + "?id=", HttpMethod.GET, null, 
				Aranzman.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(id, response.getBody().getId());
		
	}
	
	@Test
	@Order(3)
	void getAranzmanByHotel() {
		int foreignKey = 3;
		
		ResponseEntity<List<Aranzman>> response = 
				template.exchange(apiUrl + "/hotel?hotelId=" + foreignKey,
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Aranzman>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		for(Aranzman a: response.getBody()) {
			assertEquals(foreignKey, a.getHotel().getId());
		}
		
	}
	
	@Test
	@Order(4)
	void getAranzmanByTuristickaAgencija() {
		int foreignKey = 3;
		
		ResponseEntity<List<Aranzman>> response = 
				template.exchange(apiUrl + "/turistickaagencija?turistickaAgencijaId=" + foreignKey,
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Aranzman>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		for(Aranzman a: response.getBody()) {
			assertEquals(foreignKey, a.getTuristickaAgencija().getId());
		}
		
	}
	
	@Test
	@Order(5)
	void getAranzmanByUkupnaCena() {
		double ukupnaCena = 1200.00;
		
		ResponseEntity<List<Aranzman>> response = 
				template.exchange(apiUrl + "?ukupnaCena=" + ukupnaCena,
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Aranzman>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		for(Aranzman a: response.getBody()) {
			assertEquals(ukupnaCena, a.getUkupnaCena());
		}
		
	}
	
	@Test
	@Order(6)
	void createAranzman() {
		Aranzman aranzman = new Aranzman();
		
		aranzman.setUkupnaCena(5000.00);
		aranzman.setPlaceno(false);
		
		HttpEntity<Aranzman> entity = new HttpEntity<Aranzman>(aranzman);
		
		ResponseEntity<Aranzman> response = 
		template.exchange(apiUrl, HttpMethod.POST, entity, Aranzman.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals(aranzman.getUkupnaCena(), response.getBody().getUkupnaCena());
		assertEquals(aranzman.isPlaceno(), response.getBody().isPlaceno());
		
		if(largestId < response.getBody().getId()) largestId = response.getBody().getId();
	}
	
	@Test
	@Order(7)
	void updateAranzman() {
		Aranzman aranzman = new Aranzman();
		
		aranzman.setUkupnaCena(5000.00);;
		aranzman.setPlaceno(false);;
		
		HttpEntity<Aranzman> entity = new HttpEntity<Aranzman>(aranzman);
		
		ResponseEntity<Aranzman> response = 
		template.exchange(apiUrl + "?id=" + largestId, HttpMethod.PUT, entity, Aranzman.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals(aranzman.getUkupnaCena(), response.getBody().getUkupnaCena());
		assertEquals(aranzman.isPlaceno(), response.getBody().isPlaceno());
		
		
	}
	
	@Test
	@Order(8)
	void deleteAranzman() {
		ResponseEntity<?> response = 
				template.exchange(apiUrl + "?id=" + largestId, HttpMethod.DELETE,
						null, Object.class);
		
		assertEquals(204, response.getStatusCode().value());
		assertNull(response.getBody());
		
		
	}
}
