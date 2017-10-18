package org.dby.study.algorithms.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class QuickSort {
    public static int partition(double[] numbers, int low, int hight, boolean asc) {
        double key = numbers[low];
        while (low < hight) {
            //从后向前扫描
            while (numbers[hight] >= key == asc && hight > low) {
                hight--;
            }
            numbers[low] = numbers[hight];
            //从前向后扫描
            while (numbers[low] <= key == asc && hight > low) {
                low++;
            }
            numbers[hight] = numbers[low];
        }
        numbers[hight] = key;
       // System.out.println(Arrays.toString(numbers));
        return hight;
    }

    public static void sort(double[] numbers, int low, int hight, boolean asc) {
        if (low >= hight) {
            return;
        }
        int middle = partition(numbers, low, hight, asc);
        sort(numbers, low, middle - 1, asc);
        sort(numbers, middle + 1, hight, asc);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int length = 50000;
        double[] numbers = new double[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = Math.random() * numbers.length;
        }
        if (length < 100) {
            System.out.println(Arrays.toString(numbers));
        }

        // numbers = new double[]{5, 3, 6, 1, 7, 9, 8, 2, 4};

        sort(numbers, 0, numbers.length - 1, true);
        if (numbers.length < 100) {
            System.out.println(Arrays.toString(numbers));
        }

        System.out.println(System.currentTimeMillis() - start);

    }
}
