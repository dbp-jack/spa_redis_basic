package com.example.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.example.redis.domain.ItemDto;

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

	@Bean
	public RedisTemplate<String, Integer> articleTemplate(
		RedisConnectionFactory redisConnectionFactory
	){
		RedisTemplate<String, Integer> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(RedisSerializer.string());
		template.setValueSerializer(new GenericToStringSerializer<>(Integer.class));	//
		return template;
	}

	@Bean
	public RedisSerializer<Object> springSessionDefaultRedisSerializer(){
		return RedisSerializer.json();
	}


	// -------------------- sorted set
	@Bean
	public RedisTemplate<String, ItemDto> rankTemplate(
		RedisConnectionFactory redisConnectionFactory
	){
		RedisTemplate<String, ItemDto> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(RedisSerializer.string());
		template.setValueSerializer(RedisSerializer.json());
		return template;
	}
}
// 시리얼라이저 스프링시큐리티 컨텍스트 세션에 저장되게 되는데 json을 쓴다면 다시 자바로 돌아오는 과정에서 시큐리티 컨텍스트가 생성자가 없어가지고
// 에러가 일어날 가능성이 좀 있다. 시큐리티 넣어가지고 자바로 해서 진행을 하시게 되면 큰 문제없이 동작하게 된다.
// 자료 다시 참고해서 진행하기 - 그냥 json을 쓰기는 힘들거다.
