package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

//商品类型实体类
//2019-11-29
@Data
public class GoodsType implements Serializable {
    private static final long serialVersionUID = 1L;
    private String typeId;//商品类型id
    private String name;//类型名称
    private String remarks;//类型描述
    private String nums;//数量
    private LocalDateTime createTime;//创建时间
    private LocalDateTime lastTime;//修改时间

}
