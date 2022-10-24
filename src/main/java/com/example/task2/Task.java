package com.example.task2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Runnable {
    private final String name;

    public Task(String s) {
        name = s;
    }

    public void run(){
        try {
            for (int j = 0; j <= 5; j++)
            {
                if (j == 0)
                {
                    Date dt = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh : mm : ss");

                    System.out.println("Initialization time for the task name: "+ name + " = " + sdf.format(dt));

                }
                else
                {
                    Date dt = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh : mm : ss");


                    System.out.println("Time of execution for the task name: " + name + " = " +sdf.format(dt));

                }


                Thread.sleep(1000);
            }

            System.out.println(name + " is complete.");
        }

        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
}
