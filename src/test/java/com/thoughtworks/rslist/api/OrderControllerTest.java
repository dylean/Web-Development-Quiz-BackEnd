package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.OrderDto;
import com.thoughtworks.rslist.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        orderRepository.save(OrderDto.builder()
                .count(3)
                .goodsName("可乐")
                .price("2.5")
                .unit("罐")
                .build());
        orderRepository.save(OrderDto.builder()
                .count(3)
                .goodsName("雪碧")
                .price("2.5")
                .unit("罐")
                .build());
        orderRepository.save(OrderDto.builder()
                .count(3)
                .goodsName("王老吉")
                .price("2.5")
                .unit("罐")
                .build());
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    void testMysql() {
        OrderDto build = OrderDto.builder()
                .goodsName("王老吉")
                .count(3)
                .price("2.5")
                .unit("罐")
                .build();
        orderRepository.save(build);

    }

    @Test
    void shouldGetOrder() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(jsonPath("$[0].goodsName", is("可乐")))
                .andExpect(status().isOk());

        assertEquals(3, orderRepository.findAll().size());
    }
}