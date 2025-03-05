package com.concurrent.study.study03;

import lombok.Getter;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description 产品类型
 **/
@Getter
public enum ProductType {

    /**
     * 苹果
     */
    Apple("苹果"),

    TV("电视"),

    Phone("手机");

    /**
     * 产品名称
     */
    private String name;


    ProductType(String name) {
        this.name = name;
    }

}
