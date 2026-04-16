package com.mizore.entity; // 请根据你的实际包名调整

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 服装商品实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clothes implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String style;

    private Long categoryId;

    private BigDecimal price;

    private String image;

    private String description;

    private Integer stock;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}