package com.thoughtworks.rslist.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goods {
    @NotNull
    private String goodsName;
    @NotNull
    private String price;
    @NotNull
    private String unit;
    @NotNull
    private String goodsUrl;
}
