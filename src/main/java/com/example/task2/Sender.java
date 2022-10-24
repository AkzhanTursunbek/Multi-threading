package com.example.task2;

public class Sender {
    public synchronized void send(String msg)
    {
        System.out.println("Sending\t" + msg);
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            System.out.println("Thread interrupted.");
        }
        System.out.println("\n" + msg + "Sent");
    }
}