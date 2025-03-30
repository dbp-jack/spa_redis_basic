package com.example.redis;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTemplateTest {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	// redis문자형이 아니라 java의 문자형이다.
	@Test
	public void stringOpsTest(){
		// 문자열 조작을 위한 클래스
		ValueOperations<String, String> ops
			// 지금 레디스템플릿 설정된 타입을 바탕으로 레디스 문자열 조작을 할거다.
			// Redis 문자열을 조작할거다.
			= stringRedisTemplate.opsForValue();
		ops.set("simplekey", "simplevalue");
		System.out.println(ops.get("simplekey"));

		// 집합을 조작하기 위한 클래스
		SetOperations<String, String> setOps
			= stringRedisTemplate.opsForSet();
		setOps.add("hobbies","games");
		setOps.add("hobbies",
			"coding", "alcohol","gaems");
		System.out.println(setOps.size("hobbies"));

		stringRedisTemplate.expire("hobbies", 10, TimeUnit.SECONDS); // 10초뒤 타임아웃
		stringRedisTemplate.delete("simplekey"); // 그냥 삭제
	} // 메서드 형태로 다루고 있다.


	@Autowired
	private RedisTemplate<String, ItemDto> itemRedisTemplate;

	@Test
	public void itemRedisTemplateTest(){
		ValueOperations<String, ItemDto> ops
			= itemRedisTemplate.opsForValue();
		ops.set("my:keyboard", ItemDto.builder()
			.name("Mechanical Keyboard")
			.price(255000)
			.description("OMG")
			.build());
		System.out.println(ops.get("my:keyboard"));

		ops.set("my:mouse", ItemDto.builder()
			.name("mouse mice")
			.price(10000)
			.description("OMG")
			.build());
		System.out.println("my:mouse");
	}
}
