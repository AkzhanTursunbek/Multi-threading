package com.example.task2;

import java.util.LinkedList;
import java.util.Queue;

public class Sender<T> {

    private final Queue<T> queue;

    public Sender(){
        this.queue = new LinkedList<>();
    }


    public synchronized void add(T elem)
    {
        queue.add(elem);
        notify();
    }

    public synchronized T pop() throws InterruptedException{
        while (queue.isEmpty()){
            wait();
        }
        return this.queue.poll();
    }

    public synchronized int size(){
        return queue.size();
    }
}
