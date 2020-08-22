package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.domain.Goods;
import com.thoughtworks.rslist.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @GetMapping("/goods")
    public ResponseEntity<List<Goods>> getAllGoods(){
        return goodsService.getAllGoods();
    }
    
    @PostMapping("/goods")
    public ResponseEntity add(@RequestBody Goods goods){
        return goodsService.addGoods(goods);
    }
}
