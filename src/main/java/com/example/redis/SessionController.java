package com.example.redis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController {
	// http://localhost:8080/set?q=password
	@GetMapping("/set")
	public String set(
		@RequestParam("q")
		String q,
		HttpSession session
	) {
		session.setAttribute("q", q);
		return "Saved: " + q;
	}

	@GetMapping("/get")
	public String get(
		HttpSession session
	) {
		return String.valueOf(session.getAttribute("q"));
	}
}
