package com.example.task2;


public class WorkerWorkerThreadPool<T> extends Thread {

    private final int id;
    private final Sender<T> queue;

    public WorkerWorkerThreadPool(int id,Sender<T> queue){
        this.id = id;
        this.queue = queue;
    }

    @Override
        public void run () {
            try {
                while (true) {
                    T elem = queue.pop();

                    Processor proc = (Processor) elem;
                    proc.process();


                    System.out.println(
                            "WorkerThread " + id
                                    + " is running" + elem);
                }
            } catch ( Exception e ) {
                // Throwing an exception
                System.out.println("Exception is caught");
            }
        }
}
