package com.example.sven.algorithm;

import android.provider.Settings;

import com.example.sven.algorithm.test_tree.BinaryTree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static int arrays[] = new int[]{1,2,3,4,5,6,7,8,9};

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //三种方法完成两个值相互交换
    @Test
    public void testSwap(){
        int a = 5;
        int b = 6;
        //1,可读性最好的
        //int t =a;a=b;b=t;
        //2 节省内存的
//        a=a+b;
//        b = a-b;
//        a = a-b;
        //3 性能最优的
        a= a ^ b;
        b= a^b;
        a=a^b;
//        System.out.println("a=" + a + "--- b=" + b);

//        ArrayInsert(arrays,0,0);
//        for(int array :arrays){
//            System.out.print(array+" ");
//        }

        ArrayDel(arrays,0);
        for(int array :arrays){
            System.out.print(array+" ");
        }
    }

    //顺序表的插入
    public void ArrayInsert(int []array,int index,int data){
        //插入0在第一位
        //1,数组长度要增加一位
        int Size = array.length;
        array = Arrays.copyOf(array,Size+1);
        System.arraycopy(array,index,array,index+1,Size-index);
        array[index] = data;
    }

    //顺序表的删除
    public void ArrayDel(int []array,int index){
        int Size = array.length;
        int numMoved = Size - index - 1;
        if(numMoved > 0){
            System.arraycopy(array,index+1,array,index,numMoved);
        }
        arrays = Arrays.copyOf(arrays,Size-1);
    }

    @Test
    public void testSort(){
        int[] array=new int[]{3,2,5,8,1,9,4,6,7};
        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.println("\n");
//        bubbleSort(array);
        selectSort(array);
        for (int i : array) {
            System.out.print(i+" ");
        }
    }

    //顺序表的排序  （冒泡排序法）（试用场景：个位数的排序次数 比如 腾讯的斗牛五张牌排序）
    public static void bubbleSort(int [] array){ //第一轮确定最大的数，第二轮确定第二大的数，，依次下去
        //3 1 5 8 2 9 4 6 7
        for(int i=array.length-1;i>0;i--){
            for(int j = 0;j<i;j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    //顺序表的排序 （选择排序法）（试用场景：个位数的排序次数）
    public static void selectSort(int [] array){//两个两个比较，，选出最小的，然后拿最小的继续和后面做两两对比
        for(int i =0;i<array.length-1;i++){
            int index = i;
            for(int j = index+1;j<array.length;j++){
                if(array[j] <array[index]){
                    index = j;
                }
            }
            if(index != i){//如果当前就是最小的，就不需要交换
                int temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }
    }

}