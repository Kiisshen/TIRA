package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class LinkedListQueue<E> implements QueueInterface<E> {
    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;
  
        public Node(T data) {
           this.data = data;
        }
     }
     private static final int DEFAULT_STACK_SIZE = 20;
     private Node<E> head;
     private Node<E> tail;
     private int size;
     private int capacity; 

    public LinkedListQueue(){
        this(DEFAULT_STACK_SIZE);
    }

    public LinkedListQueue(int capacity){
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public String toString(){
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
    
        Node<E> current = head;
    
        for (int count = 0; count < size; count++) {
            sb.append(current.data);
    
            current = current.next;
    
            if (count < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int capacity(){
        return capacity;
    }

    @Override
    public void enqueue(E element) throws OutOfMemoryError, NullPointerException{
        if (element == null) {
            throw new NullPointerException("Cannot enqueue a null element.");
        }
        if (size == capacity) {
            reallocate();
        }
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
   }

   @Override
   public E dequeue() throws IllegalStateException{
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot dequeue.");
        }
        E data = head.data;
        head = head.next;
        if (head != null) {
            head.previous = null;
        } else {
            tail = null;
        }
        size--;
        return data;
   }

   @Override
   public E element() throws IllegalStateException{
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. No front element.");
        }
        return head.data;
   }

   @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0; 
    }

    @Override
    public void clear(){
        head = tail = null;
        size = 0;   
    }

    private void reallocate() {
        int newCapacity = capacity * 2;
        capacity = newCapacity;
    }
}
