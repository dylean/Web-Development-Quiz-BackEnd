package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.domain.Goods;
import com.thoughtworks.rslist.dto.GoodsDto;
import com.thoughtworks.rslist.repository.GoodsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class GoodsControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    GoodsRepository goodsRepository;

    @BeforeEach
    void setUp() {
        goodsRepository.deleteAll();
        goodsRepository.save(GoodsDto.builder()
                .goodsName("阔乐")
                .price("2.5")
                .unit("罐")
                .goodsUrl("https://img11.360buyimg.com/n1/jfs/t4705/83/2924377281/70031/aed9bbd3/58f5629dN79b4406c.jpg")
                .build());

        goodsRepository.save(GoodsDto.builder()
                .goodsName("雪碧")
                .price("2.5")
                .unit("罐")
                .goodsUrl("https://img11.360buyimg.com/n1/jfs/t4705/83/2924377281/70031/aed9bbd3/58f5629dN79b4406c.jpg")
                .build());

        goodsRepository.save(GoodsDto.builder()
                .goodsName("王老吉")
                .price("2.5")
                .unit("罐")
                .goodsUrl("https://img11.360buyimg.com/n1/jfs/t4705/83/2924377281/70031/aed9bbd3/58f5629dN79b4406c.jpg")
                .build());
    }

    @AfterEach
    void tearDown() {
        goodsRepository.deleteAll();
    }

    @Test
    void shouldGetAllGoods() throws Exception {
        mockMvc.perform(get("/goods"))
                .andExpect(jsonPath("$[0].goodsName", is("阔乐")))
                .andExpect(status().isOk());

        assertEquals(3, goodsRepository.findAll().size());
    }

    @Test
    void shouldAddGoodsIfGoodsExist() throws Exception {
        String json = new ObjectMapper().writeValueAsString(Goods.builder()
                .unit("罐")
                .price("2.5")
                .goodsName("阔乐")
                .goodsUrl("https://img11.360buyimg.com/n1/jfs/t4705/83/2924377281/70031/aed9bbd3/58f5629dN79b4406c.jpg")
                .build());
        mockMvc.perform(post("/goods").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldAddGoodsIfGoodsNotExist() throws Exception{
        String json = new ObjectMapper().writeValueAsString(Goods.builder()
                .unit("罐")
                .price("2.5")
                .goodsName("可乐")
                .goodsUrl("https://img11.360buyimg.com/n1/jfs/t4705/83/2924377281/70031/aed9bbd3/58f5629dN79b4406c.jpg")
                .build());
        mockMvc.perform(post("/goods").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        assertEquals(4, goodsRepository.findAll().size());
    }
}