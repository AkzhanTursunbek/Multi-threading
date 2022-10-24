package com.example.task2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public abstract class WebServer{
    public static void main(String[] args) {
        final int MAX_T = 4;
        // Port number for http request
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 8080;
        // The maximum queue length for incoming connection
        int queueLength = args.length > 2 ? Integer.parseInt(args[2]) : 50;;




        try (ServerSocket serverSocket = new ServerSocket(port, queueLength)) {
            System.out.println("Web Server is starting up, listening at port " + port + ".");
            System.out.println("You can access http://localhost:" + port + " now.");

            while (true) {
                // Make the server socket wait for the next client request
                Socket socket = serverSocket.accept();

                Sender send = new Sender();
                ThreadedSend S1 =
                        new ThreadedSend( " Hi " , send );
                ThreadedSend S2 =
                        new ThreadedSend( " Bye " , send );


                System.out.println("Got connection!");

                // To read input from the client
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

                try {
                    // Get request
                    HttpRequest request = HttpRequest.parse(input);

                    // Process request
                    Processor proc = new Processor(socket, request);
                    proc.process();

                } catch ( IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }


                // ordinary multi thread server
//                for (int i = 0; i < MAX_T; i++) {
//                    WorkerThread object = new WorkerThread();
//                    object.start();
//                }


                Runnable r1 = new Task("task 1");
                Runnable r2 = new Task("task 2");
                Runnable r3 = new Task("task 3");
                Runnable r4 = new Task("task 4");
                Runnable r5 = new Task("task 5");

                ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

                // passes the Task objects to the pool to execute (Step 3)
                pool.execute(r1);
                pool.execute(r2);
                pool.execute(r3);
                pool.execute(r4);
                pool.execute(r5);

                // pool shutdown ( Step 4)
                pool.shutdown();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            System.out.println("Server has been shutdown!");
        }
    }


}