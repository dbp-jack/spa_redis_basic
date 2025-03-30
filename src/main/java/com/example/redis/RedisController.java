package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@PostMapping("/set")
	public String setValue(@RequestParam String key, @RequestParam String value) {
		redisService.setValue(key, value);
		return "Saved!";
	}

	@GetMapping("/get")
	public String getValue(@RequestParam String key) {
		return redisService.getValue(key);
	}
}
