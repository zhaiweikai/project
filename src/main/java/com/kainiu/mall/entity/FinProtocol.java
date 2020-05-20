package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 协议实体类
 * 李龙涛
 * 2019-12-18
 */
@Data
public class FinProtocol implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;//协议id
    private String proDefinition;//协议名称
    private String  proDddress;//协议路径
    private String proText;//协议内容文本
    private LocalDateTime createTime;//创建时间
}
