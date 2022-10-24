package com.example.task2;

public class ThreadedSend extends Thread
{
    private final String msg;
    final Sender  sender;

    // Receives a message object and a string
    // message to be sent
    ThreadedSend(String m,  Sender obj)
    {
        msg = m;
        sender = obj;
    }

    public void run() {
        // Only one thread can send a message
        // at a time.
        synchronized(sender)
        {
            // synchronizing the send object
            sender.send(msg);
        }
    }
}
