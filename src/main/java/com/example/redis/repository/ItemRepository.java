package com.example.redis.repository;

import java.util.List;

import com.example.redis.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
	// @Query("")
	// List<Item> top10MostSold();
}
