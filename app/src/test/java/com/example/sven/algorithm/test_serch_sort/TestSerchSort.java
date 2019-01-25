package com.example.sven.algorithm.test_serch_sort;

import org.junit.Test;

public class TestSerchSort {

    @Test
    public void test_isCorrect(){
        int[] array=new int[]{1,2,3,4,5,6,7,8,9};
        System.out.println(binarySearch(array,0,array.length,5));

        int[] arrays=new int[]{1,7,4,9,3,2,6,5,8};
        quickSort(arrays,0,arrays.length-1);
        for (int i : arrays) {
            System.out.print(i+" ");
        }
    }

    /**
     * 二分查找  前提是数组必须是有序的
     */
     public static int binarySearch(int [] array,int fromIndex,int toIndex,int key){
        int low = fromIndex;
        int high = toIndex-1;//左闭右开原则
        while(low<=high){
            int mid = (low+high)/2;//取中间
            int midValue = array[mid];
            if(key>midValue){//去中点往右寻找
                low = mid+1;
            }else if(key<midValue){//去中点往左寻找
                high = mid-1;
            }else{
                return mid;
            }
        }
        return -(low+1);
     }

    /**
     * 快速排序  31 21 59 68 12 40  （先取一个数据出来，第一轮排序之后左边都小于这个数，右边都大于这个数，然后一直循环）
     */
      public static void quickSort(int [] array,int begin,int end){
          if(end-begin<=0) return;

          int low = begin;//0
          int high = end;//5
          int x = array[begin];
          //由于会分别从两头取数据
          boolean direction = true;
          L1:
          while(low<high){
              if(direction){//从右往左找
                  for(int i = high;i>low;i--){
                      if(array[i]<=x){
                          array[low++] = array[i];
                          high = i;
                          direction = !direction;
                          continue L1;
                      }
                  }
                  high = low;//如果一直上面的if从未进入，让两个指针重合
              }else{//从左往右找
                  for(int i=low;i<high;i++){
                      if(array[i]>=x){
                          array[high--]=array[i];
                          low = i;
                          direction = !direction;
                          continue L1;
                      }
                  }
                  low = high;
              }
          }
          array[low] =x;//第一轮确定X位置，左边的都小于X，右边的都大于X
          quickSort(array,begin,low-1);
          quickSort(array,low+1,end);
      }

      @Test
      public void test(){
//          int [] array = new int []{1,2,5,9,3,4,10,11};
//          merge(array,0,(array.length-1)/2+1,array.length-1);\

          int[] array = new int[]{2,1,6,4,3,9,8,10,7,5};
          mergeSort(array,0,array.length-1);
          for (int i : array) {
              System.out.print(i+" ");
          }
      }

    /**
     * 归并排序 思想：二叉树的后序遍历
     * @param array
     * @param left
     * @param right
     */

      public static void mergeSort(int[] array,int left,int right){
          if(left == right){
              return;
          }else{
              int mid = (left+right)/2;
              mergeSort(array,left,mid);
              mergeSort(array,mid+1,right);
              merge(array,left,mid+1,right);
          }
      }

    /**
     * 将一个左右两边分别排好序的数组合并成一个完整的排好序的数组
     */
    //1,2,5,9  ========  3,4 ,10 ,11
     public static void merge(int []array,int left,int mid ,int right){

         //1,将一个数组分成左右两个数组
         int leftSize = mid-left;
         int rightSize = right - mid + 1;
         int [] leftArray = new int[leftSize];
         int [] rightArray = new int[rightSize];
         //将数组的数组填充到左右两个数组中去
         for(int i = left;i<mid;i++){
             leftArray[i-left] = array[i];
         }
         for(int i = mid;i<=right;i++){
             rightArray[i-mid] =array[i];
         }
         //将两个数组排序后合并
         int i=0;
         int j=0;
         int k=left;
         while(i<leftSize && j<rightSize){
             if(leftArray[i]<rightArray[j]){
                 array[k] = leftArray[i];
                 k++;i++;
             }else{
                 array[k] = rightArray[j];
                 k++;j++;
             }
         }

         while(i<leftSize){
             array[k] = leftArray[i];
             k++;i++;
         }

         while(j<rightSize){
             array[k] = rightArray[j];
             k++;j++;
         }
     }
}
