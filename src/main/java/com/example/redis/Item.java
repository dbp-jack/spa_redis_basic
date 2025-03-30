package com.example.redis;
import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Entity대신 RedisHash
@RedisHash("item")
public class Item implements Serializable {		// 직렬화 가능하다.
	@Id
	// Id String으로 쓰면 UUID가 자동으로 배정된다.
	private Long id;		// 엔티티 비교용
	private String name;
	private String description;
	private Integer price;
}
