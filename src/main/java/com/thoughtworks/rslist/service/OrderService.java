package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.domain.Order;
import com.thoughtworks.rslist.dto.OrderDto;
import com.thoughtworks.rslist.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<List<Order>> getOrder() {
        List<OrderDto> all = orderRepository.findAll();
        List<Order> orders = new ArrayList<>();
        for (OrderDto orderDto : all) {
            orders.add(Order.builder()
                    .count(orderDto.getCount())
                    .goodsName(orderDto.getGoodsName())
                    .price(orderDto.getPrice())
                    .unit(orderDto.getUnit())
                    .build());
        }
        return ResponseEntity.ok(orders);
    }
}
