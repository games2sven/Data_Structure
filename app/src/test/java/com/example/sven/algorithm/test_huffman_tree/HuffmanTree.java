package com.example.sven.algorithm.test_huffman_tree;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class HuffmanTree {

    TreeNode root;

    //创建huffman树
    public TreeNode creatHuffmanTree(ArrayList<TreeNode> list){
        while( list.size() > 1){
            //第一步:将集合按照结点的权重大小从大到小排序
            Collections.sort(list);
            //由最小的两个节点开始，生成一个父节点
            TreeNode leftChild = list.get(list.size() - 1);
            TreeNode rightChild = list.get(list.size() - 2);
            TreeNode parent = new TreeNode("p",leftChild.weight+rightChild.weight);
            //子节点与父节点相互关联
            parent.leftChild = leftChild;
            leftChild.parent = parent;
            parent.rightChild = rightChild;
            rightChild.parent = parent;
            //将左,右子节点移出集合
            list.remove(leftChild);
            list.remove(rightChild);
            //将新生成的节点加入集合
            list.add(parent);
        }
        root = list.get(0);
        return list.get(0);
    }

    //以huffman树的根节点开始将huffman树显示出来
    public void showHuffMan(TreeNode root){
        LinkedList <TreeNode> list = new LinkedList();
        list.add(root);
        while(list.size() > 0){
            TreeNode treeNode = list.removeFirst();
            System.out.println(treeNode.data);

            if(treeNode.leftChild != null){
                list.add(treeNode.leftChild);
            }

            if(treeNode.rightChild != null){
                list.add(treeNode.rightChild);
            }
        }
    }



    @Test
    public void test(){
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        TreeNode node1 = new TreeNode("good",5);
        TreeNode node2 = new TreeNode("morning",15);
        TreeNode node3 = new TreeNode("afternoon",40);
        TreeNode node4 = new TreeNode("hello",30);
        TreeNode node5 = new TreeNode("hi",10);
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
//        Collections.sort(list);
//        for(TreeNode node:list){
//            System.out.println(node.weight);
//        }
        HuffmanTree huffmanTree = new HuffmanTree();
        TreeNode treeNode = huffmanTree.creatHuffmanTree(list);
        huffmanTree.showHuffMan(huffmanTree.root);
        huffmanTree.getCodec(node5);
    }

    //HuffMan编码 左0 右1
    public void getCodec(TreeNode treeNode){
        TreeNode currentNode = treeNode;
        Stack<String> stack = new Stack<>();
        while(currentNode != null && currentNode.parent != null){
            if(currentNode.parent.leftChild == currentNode){
                stack.push("0");
            }

            if(currentNode.parent.rightChild == currentNode){
                stack.push("1");
            }
            currentNode = currentNode.parent;
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }



    public static class TreeNode<T> implements Comparable<TreeNode<T>>{
        T data;
        int weight;
        TreeNode parent;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(T data,int weight){
            this.data = data;
            this.weight = weight;
            parent = null;
            leftChild = null;
            rightChild = null;
        }

        @Override
        public int compareTo(@NonNull TreeNode<T> o) {
            return o.weight - this.weight;
        }
    }


}
