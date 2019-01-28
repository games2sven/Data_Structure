package com.example.sven.algorithm.test_avl_tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.TreeMap;

public class AVLTree <T extends Comparable<T>>{
    TreeNode<T> root;//跟节点
    int size = 0;//树的元素大小

    //平衡因子
    private static final int LH = 1;
    private static final int RH = -1;
    private static final int EH = 0;

    //对一个节点左旋
    public void left_rotate(TreeNode<T> x){
        if(x != null){
            //先取到右节点
            TreeNode<T> y = x.right;
            //
            x.right = y.left;
            if(y.left != null){
                y.left.parent = x;
            }
            y.parent = x.parent;
            if(x.parent == null){
                root = y;
            }else{
                if(x.parent.left == x){
                    x.parent.left = y;
                }else if(x.parent.right == x){
                    x.parent.right = y;
                }
            }
            y.left = x;
            x.parent = y;
        }
    }

    //对一个节点右旋
    public void right_rotate(TreeNode<T> y){
        if(y != null){
            TreeNode<T> y1 = y.left;
            y.left = y1.right;
            if(y1.right != null){
                y1.right.parent = y;
            }

            y1.parent = y.parent;
            if(y.parent == null){
                root = y1;
            }else{
                if(y.parent.left == y){
                    y.parent.left = y1;
                }else if(y.parent.right == y){
                    y.parent.right = y1;
                }
            }

            y1.right = y;
            y.parent = y1;
        }
    }

    /**
     * 往平衡树上插入元素
     * @param element
     * @return
     */
    public boolean inserElement(T element){
        TreeNode<T> t = root;
        if(t == null){
            root =new TreeNode<T>(element);
            size = 1;
            root.balance = 0;
            return true;
        }else{
            //开始找到要插入的位置
            TreeNode<T> parent ;
            int cmp;
            do{
                parent = t;
                cmp = element.compareTo(t.element);
                if(cmp<0){
                    t = t.left;
                }else if(cmp>0){
                    t = t.right;
                }else{
                    return false;
                }
            }while (t!= null);
            //找到位置插入了，开始插入数据
            TreeNode<T> child = new TreeNode<T>(element);
            if(cmp<0){
                parent.left = child;
            }else{
                parent.right = child;
            }
            //节点已经放到了树上，现在开始检查是否打破了平衡（回溯查找）(变更平衡因子)
            while(parent != null){
                cmp = element.compareTo(parent.element);
                if(cmp<0){
                    parent.balance++;
                }else{
                    parent.balance--;
                }
                if(parent.balance == 0){//如果插入后还是平衡树，不用调整
                    break;
                }
                if(Math.abs(parent.balance) == 2){//只要平衡因子出现绝对值为2说明平衡已经被打破，需要调整
                    fixAfterInsertion(parent);
                    break;
                }else{
                    parent = parent.parent;
                }
            }
        }
        size++;
        return true;
    }

    //插入数值后修复平衡树
    public void fixAfterInsertion(TreeNode<T> node){
         if(node.balance == 2){//左子树太长
             rightBalance(node);
         }

         if(node.balance == -2){//右子树太长
             leftBalance(node);
         }
    }

    //左平衡（左边树太长）
    public void leftBalance(TreeNode<T> node){
        //分两种情况，一种是左左，一种是左右
        TreeNode<T> tl = node.left;
        switch (tl.balance){
            case LH:
                right_rotate(node);
                node.balance = EH;
                tl.balance = EH;
                break;
            case RH:
                TreeNode<T> tlr = tl.right;
                switch (tlr.balance){
                    case LH:
                        node.balance = RH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    case RH:
                        node.balance = EH;
                        tl.balance = LH;
                        tlr.balance = EH;
                        break;
                }
                left_rotate(tl);
                right_rotate(node);
                break;
        }
    }

    //右平衡（右边树太长）
    public void rightBalance(TreeNode<T> node){
        //分两种情况，一种是右左，一种是右右
        TreeNode<T> tr = node.right;
        switch (tr.balance){
            case LH://右左
                TreeNode<T> trl = tr.left;
                switch (trl.balance){
                    case LH:
                        tr.balance = RH;
                        node.balance = EH;
                        trl.balance = EH;
                        break;
                    case RH:
                        node.balance = LH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                }
                right_rotate(node.right);
                left_rotate(node);
                break;
            case RH://右右
                left_rotate(node);
                //旋转之后要手动更改node的平衡因子
                node.balance = EH;
                tr.balance = EH;
                break;
        }

    }


    @Test
    public void test(){
        Integer[] nums={5,8,2,0,1,-2};
        AVLTree<Integer> tree=new AVLTree<>();
        for(int i=0;i<nums.length;i++){
            tree.inserElement(nums[i]);
        }
        showAVL((TreeNode) tree.root);
    }


    public void showAVL(TreeNode<T> root){
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.offer(root);//队列放入
        while (!list.isEmpty()) {
            TreeNode node = list.pop();//队列的取出
            System.out.println(node.element);
            if (node.left != null) {
                list.offer(node.left);
            }
            if (node.right != null) {
                list.offer(node.right);
            }
        }
    }

    public class TreeNode<T extends Comparable<T>>{
        T element;
        int balance = 0;//平衡因子
        TreeNode<T> left;
        TreeNode<T> right;
        TreeNode<T> parent;

        public TreeNode(T element){
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        public TreeNode<T> getRight() {
            return right;
        }

        public void setRight(TreeNode<T> right) {
            this.right = right;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        public void setParent(TreeNode<T> parent) {
            this.parent = parent;
        }
    }

}
