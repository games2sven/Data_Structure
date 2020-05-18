package com.example.sven.algorithm.test_heapsort;

import org.junit.Test;

/**
 * 堆排序的特点：完全二叉树中任何一个非叶子节点的值均不大于或者不小于其左右子节点的值
 * 所以可以知道堆顶是整个堆中的最大值或者是最小值
 */
public class 堆排序 {

    @Test
    public void test(){
        int[] array=new int[]{2,3,4,5,6,7,1,8,9};
        heapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }

    void heapSort(int array[]){
        //循环一次，获取一次堆顶最大值，循环完毕数据就排好序了
        for(int i = array.length-1;i>0;i--){
            //建堆
            max_heapify(array,i);

            //将堆顶最大值存入到数组的最大索引处
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
        }
    }

    /**
     * 创建一个大顶堆
     * @param array
     * @param n  数组的长度-1
     */
    void max_heapify(int [] array,int n){
        int child;
        for(int i = (n-1)/2;i>= 0;i--){//非叶子节点
            //该非叶子节点的左子节点位置
            child = 2*i+1;
            //假设该节点的右子节点存在，则比较左右子节点的值的大小，得到其中的大的值
            if (child != n && array[child] < array[child + 1]) {
                child++;
            }
            //交换父节点与左右子节点中的最大值
            if (array[i] < array[child]) {
                int temp = array[i];
                array[i] = array[child];
                array[child] = temp;
            }
        }
    }
}
