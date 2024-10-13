package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;

public class StackImplementation<E> implements StackInterface<E> {
    public E[] array;
    public int tail;

    @Override
    public boolean isEmpty(){
        return false;
    }
    @Override
    public E peek() throws IllegalStateException {
        return null;
    }
    @Override
    public void clear(){

    }
    @Override
    public int capacity(){
        return 0;
    }
    @Override
    public int size(){
        return 0;
    }
    @Override
    public void push(E element) throws OutOfMemoryError, NullPointerException {
        
    }
    @Override
    public E pop() throws IllegalStateException {
        return null;
    }
}
