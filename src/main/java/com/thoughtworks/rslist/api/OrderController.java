package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.domain.Order;
import com.thoughtworks.rslist.dto.OrderDto;
import com.thoughtworks.rslist.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return orderService.getOrder();
    }

    @PostMapping("/order/{goodsId}")
    public ResponseEntity addToOrder(@PathVariable int goodsId){
        return orderService.addToOrder(goodsId);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity delete(@PathVariable int orderId){
        return orderService.deleteGoodsFromOrder(orderId);
    }
}
