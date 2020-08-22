package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDto, Integer> {
    @Override
    List<OrderDto> findAll();
}
