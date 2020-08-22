package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.domain.Order;
import com.thoughtworks.rslist.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrders() {
        return orderService.getOrder();
    }
}
