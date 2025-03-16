package com.concurrent.exercise.exercise03;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author 王文涛
 * @date 2025/3/16
 * @description
 **/

@Data
@AllArgsConstructor
public class Product {

    private Long id;

    private String name;


    public String generateId() {
        return id + "-" + name + "-" + System.currentTimeMillis();
    }

    private static void check(Product product) {

        try {
            int num = ThreadLocalRandom.current().nextInt(100, 500);
            System.out.println("正在校验" + product.getName() + "的订单");
            TimeUnit.MICROSECONDS.sleep(num);
            System.out.println("校验" + product.getName() + "的订单成功");
        } catch (InterruptedException e) {
            System.out.println("校验" + product.getName() + "的订单失败");
        }

    }

    private static void pay(Product product) {

        try {
            int num = ThreadLocalRandom.current().nextInt(100, 500);
            System.out.println("正在支付" + product.getName() + "的订单");
            TimeUnit.MICROSECONDS.sleep(num);
            System.out.println("支付" + product.getName() + "的订单成功");
        } catch (InterruptedException e) {
            System.out.println("支付" + product.getName() + "的订单失败");
        }
    }

    private static String send(Product product) {
        try {
            int num = ThreadLocalRandom.current().nextInt(100, 500);
            System.out.println("正在生成" + product.getName() + "的发货号");
            TimeUnit.MICROSECONDS.sleep(num);
            return product.generateId();
        } catch (InterruptedException e) {
            System.out.println("生成" + product.getName() + "的发货号失败");
            return null;
        }
    }

    public static String process(Product product) {
        check(product);
        pay(product);
        return send(product);
    }
}
