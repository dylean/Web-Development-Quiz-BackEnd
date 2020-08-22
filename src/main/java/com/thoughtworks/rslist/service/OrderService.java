package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.domain.Order;
import com.thoughtworks.rslist.dto.GoodsDto;
import com.thoughtworks.rslist.dto.OrderDto;
import com.thoughtworks.rslist.repository.GoodsRepository;
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
    @Autowired
    GoodsRepository goodsRepository;

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

    public ResponseEntity addToOrder(int goodsId) {
        GoodsDto goodsDto = goodsRepository.findById(goodsId).get();
        OrderDto orderDto = orderRepository.findByGoodsName(goodsDto.getGoodsName());
        if (orderDto == null) {
            orderRepository.save(OrderDto.builder()
                    .unit(goodsDto.getUnit())
                    .price(goodsDto.getPrice())
                    .goodsName(goodsDto.getGoodsName())
                    .count(1)
                    .build());
        } else {
            orderDto.setCount(orderDto.getCount() + 1);
            orderRepository.save(orderDto);
        }
        return ResponseEntity.created(null).build();
    }

    public ResponseEntity deleteGoodsFromOrder(int orderId) {
        orderRepository.deleteById(orderId);
        return ResponseEntity.created(null).build();
    }
}
