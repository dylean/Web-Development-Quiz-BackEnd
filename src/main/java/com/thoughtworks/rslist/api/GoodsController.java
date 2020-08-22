package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.domain.Goods;
import com.thoughtworks.rslist.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @GetMapping("/goods")
    public ResponseEntity<List<Goods>> getAllGoods(){
        return goodsService.getAllGoods();
    }
}
