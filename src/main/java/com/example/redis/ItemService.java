package com.example.redis;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.example.redis.domain.Item;
import com.example.redis.domain.ItemDto;
import com.example.redis.domain.ItemOrder;
import com.example.redis.repository.ItemRepository;
import com.example.redis.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final ZSetOperations<String, ItemDto> rankOps;

    public ItemService(
            ItemRepository itemRepository,
            OrderRepository orderRepository,
            RedisTemplate<String, ItemDto> rankTemplate
    ) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.rankOps = rankTemplate.opsForZSet();
    }

    public void purchase(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        orderRepository.save(ItemOrder.builder()
                .item(item)
                .count(1)
                .build());
        // ItemDto dto = ItemDto.fromEntity(item); // 먼저 확인을 왜 안할까요
        rankOps.incrementScore(     // ZZAD쓰는 경우, 게임순위 게임클리어한만큼 넣어야 하니깐
            "soldRanks",
            ItemDto.fromEntity(item),
            1);    // dto가 만약 없다면 알아서 자동으로 증가해준다.
    }   // 아이템 구매 성공
//가장 많이 팔린 상품의 결과를 돌려받는 것을 한번 실험해보자.
    public List<ItemDto> getMostSold(){
        Set<ItemDto> ranks = rankOps.reverseRange("soldRanks", 0, 9);
        if(ranks ==null) return Collections.emptyList();
        return ranks.stream().toList();
    }
}
