package org.dby.study.algorithms.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class BubbleSort {
    public static void sort(double[] numbers, boolean asc) {
        if (numbers == null) {
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1] == asc) {
                    double temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
//            System.out.println(Arrays.toString(numbers));
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int length = 50000;
        double[] numbers = new double[length];
        for (int i = 0; i < numbers.length ; i++) {
            numbers[i] = Math.random() * numbers.length;
        }

        numbers = new double[]{5, 3, 6, 1, 7, 9, 8, 2, 4};

        sort(numbers, true);

        if(numbers.length<100){
            System.out.println(Arrays.toString(numbers));
        }

        System.out.println(System.currentTimeMillis() - start);

    }
}
