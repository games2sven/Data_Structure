package com.example.sven.algorithm.test_tree;

public class BinaryTree {

    public Node<String> root;//根
    public BinaryTree(String data){
        root = new Node<String>(data,null,null);
    }

    public void createTree(){
        Node<String> nodeB = new Node<String>("B",null,null);
        Node<String> nodeC = new Node<String>("C",null,null);
        Node<String> nodeD = new Node<String>("D",null,null);
        Node<String> nodeE = new Node<String>("E",null,null);
        Node<String> nodeF = new Node<String>("F",null,null);
        Node<String> nodeG = new Node<String>("G",null,null);
        Node<String> nodeH = new Node<String>("H",null,null);
        Node<String> nodeI = new Node<String>("I",null,null);

        root.leftNode = nodeB;
        root.rightNode = nodeC;
        nodeB.leftNode = nodeD;
        nodeD.leftNode = nodeG;
        nodeD.rightNode = nodeH;
        nodeC.leftNode = nodeE;
        nodeC.rightNode = nodeF;
        nodeE.rightNode = nodeI;
    }


    /**
     * 中序访问树的所有节点
     */
    public void midOrderTraverse(Node root) {//逻辑
        if(root == null){
            return;
        }

        midOrderTraverse(root.leftNode);
        System.out.println("mid:"+root.data);
        midOrderTraverse(root.rightNode);
    }

    /**
     * 前序访问树的所有节点
     */
    public void preOrderTraverse(Node root) {//逻辑
        if(root == null){
            return;
        }
        System.out.println("pre:"+root.data);
        preOrderTraverse(root.leftNode);
        preOrderTraverse(root.rightNode);
    }

    /**
     * 后序访问树的所有节点
     */
    public void postOrderTraverse(Node root) {//逻辑
        if(root == null){
            return;
        }
        postOrderTraverse(root.leftNode);
        postOrderTraverse(root.rightNode);
        System.out.println("post:"+root.data);
    }


    public class Node<T>{
        T data;
        Node<T> leftNode;
        Node<T> rightNode;

        public Node(T data, Node<T> leftNode, Node<T> rightNode) {
            this.data = data;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }


}
