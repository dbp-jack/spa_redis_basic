package com.example.redis.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("order")
public class ItemOrder {
	@Id
	private String id;
	private String item;
	private Integer count;
	private Long totalPrice;
	private String status;
}
