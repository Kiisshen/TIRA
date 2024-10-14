package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;

public class StackImplementation<E> implements StackInterface<E> {
    private static final int DEFAULT_STACK_SIZE = 20;
    public Object[] array;
    public int tail;
    public int size;

    public StackImplementation(){
        this(DEFAULT_STACK_SIZE);
    }

    public StackImplementation(int size){
        array = new Object[size];
        this.size = size;
        tail = 0;
    }

    public void reallocate(){
        int newSize = size * 2;
        Object[] newArray = new Object[newSize];
        for (int i = 0; i < tail; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        size = newSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tail; i++) {
            sb.append(array[i]);
            if (i < tail - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean isEmpty(){
        return tail == 0;
    }
    @Override
    public E peek() throws IllegalStateException {
        if (tail == 0) {
            throw new IllegalStateException("Stack is empty, cannot peek");
        }
        return (E)array[tail-1];
    }
    @Override
    public void clear(){
        for(int i = 0; i < tail; i++){
            array[i] = null;
        }
        tail = 0;
    }
    @Override
    public int capacity(){
        return size;
    }
    @Override
    public int size(){
        return tail;
    }
    @Override
    public void push(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot push null element");
        }
        if(element != null && tail < capacity()){
            array[tail] = element;
            tail++;
        }
        else if(tail == capacity()){
            reallocate();
            push(element);
        }
    }
    @Override
    public E pop() throws IllegalStateException {
        if (tail == 0) {
            throw new IllegalStateException("Stack is empty, cannot pop");
        }
        E elem = (E)array[tail-1];
        array[tail-1] = null;
        tail--;
        return elem;
    }
}
