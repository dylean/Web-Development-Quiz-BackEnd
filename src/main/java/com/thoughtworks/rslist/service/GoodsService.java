package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.domain.Goods;
import com.thoughtworks.rslist.dto.GoodsDto;
import com.thoughtworks.rslist.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

    public ResponseEntity<List<GoodsDto>> getAllGoods() {
        List<GoodsDto> all = goodsRepository.findAll();
        return ResponseEntity.ok(all);
    }

    public ResponseEntity addGoods(Goods goods) {
        GoodsDto goodsDto = goodsRepository.findByGoodsName(goods.getGoodsName());
        if (goodsDto != null) {
            return ResponseEntity.badRequest().build();
        }
        GoodsDto build = GoodsDto.builder()
                .unit(goods.getUnit())
                .price(goods.getPrice())
                .goodsName(goods.getGoodsName())
                .goodsUrl(goods.getGoodsUrl())
                .build();
        goodsRepository.save(build);
        return ResponseEntity.created(null).build();
    }
}
