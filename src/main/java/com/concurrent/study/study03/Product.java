package com.concurrent.study.study03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description 产品
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * 产品名
     */
    private String name;

    private static Random random = new Random();

    /**
     * 使用产品
     */
    public void use() {
        try {
            System.out.println("消费产品" + name + "中...");
            Thread.sleep(random.nextInt(500, 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("使用产品：" + name);
    }

    public static Product product() {

        ProductType[] products = ProductType.values();

        try {
            System.out.println("生产产品中...");
            Thread.sleep(random.nextInt(1000, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Product(products[random.nextInt(products.length)].getName());
    }
}
