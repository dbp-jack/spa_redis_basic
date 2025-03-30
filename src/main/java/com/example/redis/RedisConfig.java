package com.example.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, ItemDto> itemRedisTemplate(
		RedisConnectionFactory connectionFactory	// yml파일 바탕

	){
		RedisTemplate<String, ItemDto> template
			= new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setKeySerializer(RedisSerializer.string()); // 문자열 직렬화
		template.setValueSerializer(RedisSerializer.json()); // 데이터 직렬화
		return template;
	}
}
