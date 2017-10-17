package org.dby.study.algorithms.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class BubbleSort {
    public static double[] sort(double[] numbers, boolean asc) {
        if (numbers == null) {
            return numbers;
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1] == asc) {
                    double temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        return numbers;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int length = 50000;
        double[] arr = new double[length];
        for (int i = 0; i < length ; i++) {
            arr[i] = Math.random() * length;
        }
        double[] arr2 = sort(arr, true);

        if(length<100){
            System.out.println(Arrays.toString(arr2));
        }

        System.out.println(System.currentTimeMillis() - start);

    }
}
