package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.dto.GoodsDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoodsRepository extends JpaRepository<GoodsDto, Integer> {
    GoodsDto findByGoodsName(String name);

    @Override
    Optional<GoodsDto> findById(Integer integer);
}
