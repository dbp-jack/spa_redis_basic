package com.example.redis;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepositrory extends CrudRepository<Item, Long> { }
