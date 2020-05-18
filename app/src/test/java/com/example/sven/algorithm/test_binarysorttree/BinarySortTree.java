package com.example.sven.algorithm.test_binarysorttree;

import java.util.NoSuchElementException;

/**
 * 二叉排序树
 */
public class BinarySortTree {

    TreeNode root;

    /**
     * 新增结点
     */
    public TreeNode put(int data) {
        if (root == null) {
            TreeNode node = new TreeNode(data);
            root = node;
            return node;
        } else {
            TreeNode node = root;
            TreeNode parent = null;
            //新增结点应该放在哪里(放在确定的parent的下面)
            while (node != null) {
                parent = node;
                if (data < node.data) {
                    node = node.leftChild;
                } else if (data > node.data) {
                    node = node.rightChild;
                } else {
                    return node;
                }
            }
            TreeNode newNode = new TreeNode(data);
            if (data > parent.data) {
                parent.rightChild = newNode;
            } else {
                parent.leftChild = newNode;
            }
            newNode.parent = parent;
            return newNode;
        }
    }

    /**
     * 查找一个结点
     *
     * @param data
     * @return
     */
    public TreeNode search(int data) {
        if (root == null) {
            return null;
        }
        TreeNode node = root;
        while (node != null) {
            if (node.data == data) {
                return node;
            } else if (data > node.data) {
                node = node.rightChild;
            } else if (data < node.data) {
                node = node.leftChild;
            }
        }
        return null;
    }

    /**
     * 删除结点，要删除的结点在树上是一定存在的才删除
     *
     * @param node
     */
    public void delNode(TreeNode node) {
        if (node == null) {
            throw new NoSuchElementException();
        } else {
            //先得到结点的父亲，方便之后操作
            TreeNode parent = node.parent;
            //1,结点是叶子
            if (node.leftChild == null && node.rightChild == null) {
                if (parent == null) {//就是根结点
                    root = null;
                } else if (parent.leftChild == node) {
                    parent.leftChild = null;
                } else if (parent.rightChild == node) {
                    parent.rightChild = null;
                }
            } else if (node.leftChild != null && node.rightChild == null) {//只有左孩子
                if (parent == null) {//就是根结点
                    root = node.leftChild;
                    node.leftChild.parent = null;
                } else {
                    if (parent.leftChild == node) {//是父亲的左孩子
                        parent.leftChild = node.leftChild;
                        node.leftChild.parent = parent;
                    } else if (parent.rightChild == node) {//是父亲的右孩子
                        parent.rightChild = node.leftChild;
                        node.leftChild.parent = parent;
                    }
                }
            } else if (node.rightChild != null && node.leftChild == null) {//只有右孩子
                if (parent == null) {//就是根结点
                    root = node.rightChild;
                    node.rightChild.parent = null;
                } else {
                    if (parent.leftChild == node) {//是父亲的左孩子
                        parent.leftChild = node.rightChild;
                        node.rightChild.parent = parent;
                    } else if (parent.rightChild == node) {//是父亲的右孩子
                        parent.rightChild = node.rightChild;
                        node.rightChild.parent = parent;
                    }
                }
            } else {//有左右两个孩子
                if (node.rightChild.leftChild == null) {//如果结点的右孩子的左孩子为空，则直接把结点的右孩子补上去
                    if (parent == null) {//是根结点
                        root = node.rightChild;
                        node.leftChild.parent = node.rightChild;
                        node.rightChild.parent = null;
                        node.rightChild.leftChild = node.leftChild;
                    } else {
                        if (node == parent.leftChild) {//是父亲的左孩子
                            parent.leftChild = node.rightChild;
                        } else if (node == parent.rightChild) {
                            parent.rightChild = node.rightChild;
                        }
                        node.rightChild.parent = parent;
                        node.rightChild.leftChild = node.leftChild;
                        node.leftChild.parent = node.rightChild;
                    }
                } else {//如果结点的右孩子的左孩子不为空，则需要把结点的右孩子的左孩子的最小的一个
                    if (parent == null) {//是根结点
                        TreeNode curNode = getMinLeftTreeNode(node.rightChild);
                        root = curNode;
                        curNode.parent.leftChild = curNode.rightChild;
                        curNode.parent = null;
                        curNode.leftChild = node.leftChild;
                        curNode.rightChild = node.rightChild;
                        node.leftChild.parent = curNode;
                        node.rightChild.parent = curNode;
                    } else {
                        TreeNode curNode = getMinLeftTreeNode(node.rightChild);
                        curNode.parent.leftChild = curNode.rightChild;
                        if (node == parent.leftChild) {
                            parent.leftChild = curNode;
                        } else if (node == parent.rightChild) {
                            parent.rightChild = curNode;
                        }
                        curNode.leftChild = node.leftChild;
                        curNode.rightChild = node.rightChild;
                        curNode.parent = parent;
                        node.leftChild.parent = curNode;
                        node.rightChild.parent = curNode;
                    }
                }
            }
        }
    }

    /**
     * 二叉树的中序遍历 LDR
     *
     * @param root
     */
    public void midOrderTraveler(TreeNode root) {
        if (root == null) {
            return;
        }
        midOrderTraveler(root.leftChild);
        System.out.print(root.data + " ");
        midOrderTraveler(root.rightChild);
    }


    //获取左子树上的最小值  (一直往左子树上找，找到没有左孩子为止就是最下面的那个)
    public TreeNode getMinLeftTreeNode(TreeNode node) {
        TreeNode curRoot = node;
        if (curRoot == null) {
            return null;
        } else {
            while (curRoot.leftChild != null) {
                curRoot = curRoot.leftChild;
            }
        }
        return curRoot;
    }

    public class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parent;

        public TreeNode(int data) {
            this.data = data;
        }
    }
}
