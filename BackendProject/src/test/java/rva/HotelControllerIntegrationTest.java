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

import rva.model.Hotel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HotelControllerIntegrationTest {
	
	static RestTemplate template = new RestTemplate();
	static String apiUrl = "http://localhost:8080/hotels";
	static long largestId = 0;
	

	@Test
	@Order(1)
	void getAllHotels() {
		ResponseEntity<List<Hotel>> response = 
				template.exchange(apiUrl, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Hotel>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(0, response.getBody().size());
		
	}
	
	@Test
	@Order(2)
	void getAllHotelById() {
		int id = 3;
		
		ResponseEntity<Hotel> response = 
				template.exchange(apiUrl + "?id=", HttpMethod.GET, null, 
				Hotel.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertNotEquals(id, response.getBody().getId());
		
	}
	
	@Test
	@Order(3)
	void getHotelByDestinacija() {
		int foreignKey = 3;
		
		ResponseEntity<List<Hotel>> response = 
				template.exchange(apiUrl + "/destinacija?destinacijaId=" + foreignKey,
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Hotel>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		for(Hotel h: response.getBody()) {
			assertEquals(foreignKey, h.getDestinacija().getId());
		}
		
	}
	
	@Test
	@Order(4)
	void getHotelByNaziv() {
		String naziv = "Hotel Palma";
		
		ResponseEntity<List<Hotel>> response = 
				template.exchange(apiUrl + "?naziv=" + naziv,
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Hotel>>() {});
		
		assertEquals(200, response.getStatusCode().value());
		for(Hotel h: response.getBody()) {
			assertEquals(naziv, h.getNaziv());
		}
		
	}

	@Test
	@Order(5)
	void createHotel() {
		Hotel hotel = new Hotel();
		
		hotel.setBrojZvezdica(5);
		hotel.setOpis("ALL-INCLUSIVE Resort");
		
		HttpEntity<Hotel> entity = new HttpEntity<Hotel>(hotel);
		
		ResponseEntity<Hotel> response = 
		template.exchange(apiUrl, HttpMethod.POST, entity, Hotel.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals(hotel.getBrojZvezdica(), response.getBody().getBrojZvezdica());
		assertEquals(hotel.getOpis(), response.getBody().getOpis());
		
		if(largestId < response.getBody().getId()) largestId = response.getBody().getId();
	}
	
	@Test
	@Order(6)
	void updateHotel() {
		Hotel hotel = new Hotel();
		
		hotel.setBrojZvezdica(5);
		hotel.setOpis("ALL-INCLUSIVE Resort");
		
		HttpEntity<Hotel> entity = new HttpEntity<Hotel>(hotel);
		
		ResponseEntity<Hotel> response = 
		template.exchange(apiUrl + "?id=" + largestId, HttpMethod.PUT, entity, Hotel.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals(hotel.getBrojZvezdica(), response.getBody().getBrojZvezdica());
		assertEquals(hotel.getOpis(), response.getBody().getOpis());
		
		
	}
	
	@Test
	@Order(7)
	void deleteHotel() {
		ResponseEntity<?> response = 
				template.exchange(apiUrl + "?id=" + largestId, HttpMethod.DELETE,
						null, Object.class);
		
		assertEquals(204, response.getStatusCode().value());
		assertNull(response.getBody());
		
		
	}
}
