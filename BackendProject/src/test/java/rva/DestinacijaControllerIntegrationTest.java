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

import rva.model.Destinacija;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DestinacijaControllerIntegrationTest {
	
	static RestTemplate template = new RestTemplate();
	static String apiUrl = "http://localhost:8080/destinacijas";
	static long largestId = 0;

	@Test
	@Order(1)
	void getAllDestinacijas() {
		ResponseEntity<List<Destinacija>> response = 
				template.exchange(apiUrl, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Destinacija>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(0, response.getBody().size());
		
	}

	@Test
	@Order(2)
	void getAllDestinacijaById() {
		int id = 3;
		
		ResponseEntity<Destinacija> response = 
				template.exchange(apiUrl + "?id=", HttpMethod.GET, null, 
				Destinacija.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(id, response.getBody().getId());
		
	}
	
	@Test
	@Order(3)
	void getDestinacijaByMesto() {
		String mesto = "Tivat";
		
		ResponseEntity<List<Destinacija>> response = 
				template.exchange(apiUrl + "?mesto=" + mesto,
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Destinacija>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		for(Destinacija d: response.getBody()) {
			assertEquals(mesto, d.getMesto());
		}
		
	}
	
	
	
	@Test
	@Order(4)
	void createDestinacija() {
		Destinacija destinacija = new Destinacija();
		
		destinacija.setMesto("Dzakarta");;
		destinacija.setOpis("Poseta glavnom gradu Indonezije");
		
		HttpEntity<Destinacija> entity = new HttpEntity<Destinacija>(destinacija);
		
		ResponseEntity<Destinacija> response = 
		template.exchange(apiUrl, HttpMethod.POST, entity, Destinacija.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals(destinacija.getMesto(), response.getBody().getMesto());
		assertEquals(destinacija.getOpis(), response.getBody().getOpis());
		
		if(largestId < response.getBody().getId()) largestId = response.getBody().getId();
	}
	
	@Test
	@Order(5)
	void updateDestinacija() {
		Destinacija destinacija = new Destinacija();
		
		destinacija.setMesto("Stokholm");;
		destinacija.setOpis("Poseta glavnom gradu Svedske");
		
		HttpEntity<Destinacija> entity = new HttpEntity<Destinacija>(destinacija);
		
		ResponseEntity<Destinacija> response = 
		template.exchange(apiUrl + "?id=" + largestId, HttpMethod.PUT, entity, Destinacija.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals(destinacija.getMesto(), response.getBody().getMesto());
		assertEquals(destinacija.getOpis(), response.getBody().getOpis());
		
		
	}
	
	@Test
	@Order(6)
	void deleteDestinacija() {
		ResponseEntity<?> response = 
				template.exchange(apiUrl + "?id=" + largestId, HttpMethod.DELETE,
						null, Object.class);
		
		assertEquals(204, response.getStatusCode().value());
		assertNull(response.getBody());
		
		
	}
	
}
