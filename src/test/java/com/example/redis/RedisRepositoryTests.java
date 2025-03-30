package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisRepositoryTests {
	@Autowired
	private ItemRepositrory itemRepositrory;

	@Test
	public void createTest() {
		// 객체를 만들고
		Item item = Item.builder()
			.id(1L)
			.name("keyboard")
			.description("Very Expensive Keyboard")
			.price(100000)
			.build();
		// save를 호출한다.
		itemRepositrory.save(item);
	}
	@Test
	public void readOnTest(){
		Item item = itemRepositrory.findById(1L)
			.orElseThrow();
		System.out.println(item.getDescription());
	}

	@Test
	public void updateTest(){
		Item item = itemRepositrory.findById(1L)
			.orElseThrow();
		item.setDescription("On Sale!!");
		item = itemRepositrory.save(item);
		System.out.println(item.getDescription());
	}
	@Test
	public void deleteTest(){
		itemRepositrory.deleteById(1L);
	}
	@Autowired
	private RedisTemplate<String, ItemDto> itmeRedisTemplate;

	@Test
	public void itemRedisTemplatetTest() {
		ValueOperations<String, ItemDto> ops
			= itmeRedisTemplate.opsForValue();
		ops.set("my:keyboard", ItemDto.builder()
			.name("Mechanical Keyboard")
			.price(250000)
			.description("OMG")
			.build());
		System.out.println(ops.get("my:keyboard"));

		ops.set("my:mouse", ItemDto.builder()
			.name("mouse Keyboard")
			.price(12222)
			.description("OMG")
			.build());
		System.out.println(ops.get("my:mouse"));
	} // 문자열로 넣은게 맞고, 인텔리제이에서 json형태로 보여주는거다.
}
