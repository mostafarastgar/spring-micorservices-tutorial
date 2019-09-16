package com.eclipse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestService {
	@GetMapping("/testrest")
	public String test() {
		return "bye sdfa...";
	}
}
