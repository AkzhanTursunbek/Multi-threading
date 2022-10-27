package com.example.task2;

public class WorkerThread extends Thread {

    private final int id;
    private final Processor processor;

    public WorkerThread(int id, Processor processor){
        this.id = id;
        this.processor=processor;
    }

    @Override
    public void run () {
        System.out.println(
                "WorkerThread " + id
                        + " is running");
        try {

            processor.process();

        } catch ( Exception e ) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
