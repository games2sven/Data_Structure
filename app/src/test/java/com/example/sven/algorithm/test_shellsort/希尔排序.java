package com.example.sven.algorithm.test_shellsort;

import org.junit.Test;

/**
 * 将待排序数组按照步长gap进行分组，然后将每组的元素利用插入排序的方法进行排序
 * 每次将步长折半减小，直到gap=1时，再使用插入排序，完成排序
 */
public class 希尔排序 {

    @Test
    public void test(){
        int[] array=new int[]{2,3,4,5,6,7,1,8,9};

        shellSort(array,4);
        //2 3 1 5 6 7 4 8 9
        shellSort(array,1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }

    private void shellSort(int[] array, int step) {
        for(int k =0;k<step;k++){
            //直接插入排序
            for(int i = k+step;i<array.length;i=i+step){
                int j = i;
                int target = array[j];
                while(j>step - 1 && target<array[j-step]){
                    array[j] = array[j - step];
                    j = j - step;
                }
                array[j] = target;
            }
        }
    }

}
