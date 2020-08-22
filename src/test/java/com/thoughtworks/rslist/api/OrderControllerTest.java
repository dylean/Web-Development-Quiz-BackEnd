package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.GoodsDto;
import com.thoughtworks.rslist.dto.OrderDto;
import com.thoughtworks.rslist.repository.GoodsRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        goodsRepository.deleteAll();
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

    @Test
    void shouldAddToOrder() throws Exception {
        GoodsDto goodsDto = goodsRepository.save(GoodsDto.builder()
                .goodsName("王老吉")
                .price("2.5")
                .unit("罐")
                .goodsUrl("https://img11.360buyimg.com/n1/jfs/t4705/83/2924377281/70031/aed9bbd3/58f5629dN79b4406c.jpg")
                .build());
        mockMvc.perform(post("/order/"+ goodsDto.getId()))
                .andExpect(status().isCreated());

        assertEquals(4, orderRepository.findByGoodsName("王老吉").getCount());

        GoodsDto goodsDto2 = goodsRepository.save(GoodsDto.builder()
                .goodsName("王老吉2")
                .price("2.5")
                .unit("罐")
                .goodsUrl("https://img11.360buyimg.com/n1/jfs/t4705/83/2924377281/70031/aed9bbd3/58f5629dN79b4406c.jpg")
                .build());
        mockMvc.perform(post("/order/"+ goodsDto2.getId()))
                .andExpect(status().isCreated());

        assertEquals(1, orderRepository.findByGoodsName("王老吉2").getCount());
    }

    @Test
    void shouldDeleteOrder() throws Exception{
        OrderDto save = orderRepository.save(OrderDto.builder()
                .count(3)
                .goodsName("可乐2")
                .price("2.5")
                .unit("罐")
                .build());

        mockMvc.perform(delete("/order/"+ save.getId()))
                .andExpect(status().isCreated());

    }
}