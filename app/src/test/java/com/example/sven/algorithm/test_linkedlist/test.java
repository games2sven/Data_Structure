package com.example.sven.algorithm.test_linkedlist;

import org.junit.Test;

public class test {

    @Test
    public void test(){
//        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
//        singleLinkedList.add(5);
//        singleLinkedList.add(6);
//        singleLinkedList.add(2);
//        singleLinkedList.add(0);
//
//        singleLinkedList.add(2,10);
//        singleLinkedList.remove(3);
//        for(int i = 0;i<singleLinkedList.size;i++){
//            System.out.print(i+":"+singleLinkedList.get(i)+"  ");
//        }
        SingleLinkedList<Mahjong> list = new SingleLinkedList<Mahjong>();
        list.add(new Mahjong(3,1));
        list.add(new Mahjong(2,3));
        list.add(new Mahjong(3,7));
        list.add(new Mahjong(1,1));
        list.add(new Mahjong(3,8));
        list.add(new Mahjong(2,2));
        list.add(new Mahjong(3,2));
        list.add(new Mahjong(1,3));
        list.add(new Mahjong(3,9));
        list.add(new Mahjong(3,5));
        list.add(new Mahjong(2,6));
        list.add(new Mahjong(3,1));
        list.add(new Mahjong(2,4));
        System.out.println(list);
        radixSort(list);
        System.out.println(list);

    }

    public static void radixSort(SingleLinkedList<Mahjong> list){
        //先对点数进行分组（1-9）
        SingleLinkedList [] rankList = new SingleLinkedList[9];
        for(int i = 0;i<rankList.length;i++){
            rankList[i] = new SingleLinkedList();
        }

        //把数据一个个的放入到对应的数组中
        while(list.size>0){
            //取一个
            Mahjong m = list.remove();
            //点数1-9放入到0-8的组中去
            rankList[m.rank - 1].add(m);
        }
        //已经把数据全部按照点数放入到对应的9个组中去了
        //将9组数据合并成一组数据
        for(int i = 0;i<rankList.length;i++){
            list.addAll(rankList[i]);
        }

        //先花色进行分组
        SingleLinkedList [] suitList = new SingleLinkedList[3];
        for(int i = 0 ;i<suitList.length;i++){
            suitList[i] = new SingleLinkedList();
        }
        //把数据按照花色一个个放到对应的组中
        while(list.size > 0){
            Mahjong m = list.remove();
            //放到对应的组中
            suitList[m.suit - 1].add(m);
        }
        //把三个花色的组合并成一个组
        for(int i = 0;i<suitList.length;i++){
            list.addAll(suitList[i]);
        }
    }


}
