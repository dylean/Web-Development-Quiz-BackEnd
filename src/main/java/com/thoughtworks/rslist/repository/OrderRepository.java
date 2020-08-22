package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDto, Integer> {
    @Override
    List<OrderDto> findAll();

    OrderDto findByGoodsName(String goodsName);
}
