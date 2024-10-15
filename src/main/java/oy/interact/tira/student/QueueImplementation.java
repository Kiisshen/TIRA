package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class QueueImplementation<E> implements QueueInterface<E> {
    
    private static final int DEFAULT_STACK_SIZE = 20;
    private E[] array;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public QueueImplementation() {
        this(DEFAULT_STACK_SIZE);
    }

    public QueueImplementation(int capacity) {
        this.capacity = capacity;
        this.array = (E[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[(head + i) % capacity]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot enqueue a null element.");
        }
        if (size == capacity) {
            reallocate();
        }

        array[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
    }

    @Override
    public E dequeue() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot dequeue.");
        }

        E data = array[head];
        array[head] = null;
        head = (head + 1) % capacity;
        size--;
        return data;
    }

    @Override
    public E element() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. No front element.");
        }
        return array[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    private void reallocate() {
        int newCapacity = capacity * 2;
        E[] newArray = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = array[(head + i) % capacity];
        }

        array = newArray;
        head = 0;
        tail = size;
        capacity = newCapacity;
    }
}
