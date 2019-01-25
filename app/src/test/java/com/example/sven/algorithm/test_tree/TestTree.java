package com.example.sven.algorithm.test_tree;

import org.junit.Test;

public class TestTree {

    @Test
    public void test_iscorrect() throws Exception {
        BinaryTree tree = new BinaryTree("A");
        tree.createTree();
        tree.postOrderTraverse(tree.root);
        tree.midOrderTraverse(tree.root);
        tree.preOrderTraverse(tree.root);
    }


}
