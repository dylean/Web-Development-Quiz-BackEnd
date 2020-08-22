package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.domain.Goods;
import com.thoughtworks.rslist.dto.GoodsDto;
import com.thoughtworks.rslist.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

    public ResponseEntity<List<Goods>> getAllGoods() {
        List<GoodsDto> all = goodsRepository.findAll();
        List<Goods> goodsList = new ArrayList<>();
        for (GoodsDto cur : all)
            goodsList.add(Goods.builder()
                    .goodsName(cur.getGoodsName())
                    .price(cur.getPrice())
                    .unit(cur.getUnit())
                    .goodsUrl(cur.getGoodsUrl())
                    .build());
        return ResponseEntity.ok(goodsList);
    }
}
