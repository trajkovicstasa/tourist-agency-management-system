package rva.primer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/zbir")
	public String zbir() {
		return String.valueOf(Math.random() + Math.random());
	}
}

