package com.concurrent.study.study15;

import java.util.function.ToLongBiFunction;

/**
 * @author 王文涛
 * @date 2025/3/20
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        System.out.println(
                "串行运行时间" + analyseRuntime(SumFunction::getSumSequence, 1, 1000000000, 10) + " ms"
        );

        System.out.println(
                "并行运行时间" + analyseRuntime(SumFunction::getSumParallel, 1, 1000000000, 10) + " ms"
        );

    }


    /**
     * 分析平均运行时间
     *
     * @param func  运行函数
     * @param times 运行次数
     */
    public static double analyseRuntime(ToLongBiFunction<Long, Long> func, long begin, long end, int times) {

        double total = 0;

        for (int i = 0; i < times; i++) {
            long s = System.currentTimeMillis();
            long l = func.applyAsLong(begin, end);
            System.out.println("计算结果：" + l);
            long e = System.currentTimeMillis();
            total += e - s;
        }

        return total / times;
    }

}
