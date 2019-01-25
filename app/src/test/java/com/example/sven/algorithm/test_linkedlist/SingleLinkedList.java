package com.example.sven.algorithm.test_linkedlist;

import java.util.NoSuchElementException;

public class SingleLinkedList <T>{

    /**
     * 结点  单链表的元素结点
     * @param <T>
     */
    private static class Node<T>{
        T item;//单链表的数据
        Node<T> next;//单链表的下一个元素的指针
        public Node(T item,Node<T> next){
            this.item = item;
            this.next = next;
        }
    }

    //头结点
    Node<T> first;
    //尾结点
    Node<T> last;
    //大小
    int size;

    //手写单链表
    public SingleLinkedList(){

    }

    //添加元素
    public void add(T t){
        linkLast(t);
    }

    /**
     *添加数据在最后
     */
    public void linkLast(T t){
        Node<T> newNode = new Node<T>(t,null);
        Node<T> l = last;
        last = newNode;

        if(l == null){
            first = newNode;
        }else{
            l.next = newNode;
        }
        size++;
    }

    /**
     * 添加数据在index位置
     * @param index
     * @param t
     */
    public void add(int index,T t){
        if(index <0 || index >size){
            return;
        }

        if(index == size){
            linkLast(t);
        }else{
            Node<T> preTarget = node(index-1);
            Node<T> newNode = new Node<T>(t,preTarget.next);
            preTarget.next = newNode;
            size++;
        }
    }

    /**
     * 添加一整个单链表
     * @param c
     * @return
     */
    public boolean addAll(SingleLinkedList c){
        return addAll(size,c);
    }

    private boolean addAll(int index,SingleLinkedList c){
        checkPositionIndex(index);

        int numNew = c.size;
        if (numNew == 0)
            return false;

        Node<T> pred;
        pred = last;

        for(int i = 0;i<c.size;i++){
            T t = (T) c.get(i);
            Node<T> newNode = new Node<>(t,null);
            if(pred == null){
                first = newNode;
            }else{
                pred.next = newNode;
            }
            pred = newNode;
        }

        last = pred;
        size += numNew;
        return true;
    }


    //查找元素
    public T get(int index){
        if(index<0 || index>size){
            return null;
        }
        return node(index).item;
    }

    /**
     * 获取index位置上的结点
     */
    private Node<T> node(int index){
        //如果index在整个链表的前半部分
        Node<T> node = first;
        for(int i = 0;i<index;i++){
            node = node.next;
        }
        return node;
    }

    /**
     * 修改
     * @param index
     * @param t
     * @return
     */
    public T set(int index ,T t){
        checkElementIndex(index);
        return setNode(index,t);
    }

    private T setNode(int index,T t){
        Node<T> node = node(index);
        node.item = t;
        return t;
    }

    public T remove(){
        return removeFirst();
    }

    private T removeFirst(){
        final Node<T> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    /**
     * Unlinks non-null first node f.
     */
    private T unlinkFirst(Node<T> f) {
        T item = f.item;
        Node<T> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if(next == null){//没有数据了
            last = null;
        }
        size --;
        return item;
    }

    /**
     * 删除数据
     * @param index
     */
    public T remove(int index){
        checkElementIndex(index);
        return unlinkNode(index);
    }

    private T unlinkNode(int index){
        Node<T> preTarget = node(index-1);
        Node<T> target = preTarget.next;
        preTarget.next = target.next;
        target.next = null;
        size -- ;
        return target.item;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public String toString() {
        if (size == 0)
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i =0;i<size;i++) {
            T e = get(i);
            sb.append(e);
            if (i == size-1)
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
        return sb.toString();
    }



}
