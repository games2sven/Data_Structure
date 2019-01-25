package com.example.sven.algorithm.test_binarysorttree;

import com.example.sven.algorithm.test_tree.BinaryTree;

import org.junit.Test;

public class TestBinarySortTree {

    @Test
    public void test() throws Exception{
        BinarySortTree tree = new BinarySortTree();

        int[] array=new int[]{5,2,7,3,4,1,6};
        for (int i : array) {
            tree.put(i);
        }
        tree.midOrderTraveler(tree.root);

        for(int i = 0;i<array.length-1;i++){
            BinarySortTree.TreeNode node = tree.search(array[i]);
            System.out.println("----"+node.data);
            tree.delNode(node);
            tree.midOrderTraveler(tree.root);
        }
    }



}
