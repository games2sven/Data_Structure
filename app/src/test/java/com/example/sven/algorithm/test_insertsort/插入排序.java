package com.example.sven.algorithm.test_insertsort;

import org.junit.Test;

//从第一个元素开始，左边的已经排好序，将右边的往左边插入。
public class 插入排序 {

    @Test
    public void test(){
        int[] array=new int[]{2,3,4,5,6,7,1,8,9};
        insertSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }

    //直接插入排序
    public void insertSort(int [] array){
        for (int i = 1;i<array.length;i++){
            int j = i;
            int target = array[j];
            while(j>0 && target<array[j-1]){
                array[j] = array[j-1];
                j--;
            }
            array[j] = target;
        }
    }
}
