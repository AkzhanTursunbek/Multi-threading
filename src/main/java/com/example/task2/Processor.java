package com.example.task2;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class Processor {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void create(PrintWriter output) throws IOException {

        File directoryPath = new File("C:\\Users\\Asus FX705D\\Downloads");
        FilenameFilter textFilefilter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".txt")) {
                return true;
            } else {
                return false;
            }
        };

        File filesList[] = directoryPath.listFiles(textFilefilter);
        System.out.println("List of the text files in the specified directory:");
        for(File file : filesList) {
            System.out.println("File name: "+file.getName());
            System.out.println("File path: "+file.getAbsolutePath());
            System.out.println("Size :"+file.getTotalSpace());
            System.out.println(" ");
        }

        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Create</title></head>");
        output.println("<body><h1>Hello THere! This is page of creation! <h1></body>");

        output.println("</html>");
        output.flush();


    }
    public void delete(PrintWriter output) throws IOException {

        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Delete page</title></head>");
        output.println("<body><h1>Files are deleted<h1></body>");
        output.println("</html>");
        output.flush();
    }

    public void params(PrintWriter output) throws IOException {

        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Params</title></head>");
        output.println("<body><h1>Hello There! This is page of Params <h1></body>");
        output.println("</html>");
        output.flush();
    }

    public void process () throws IOException, InterruptedException {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        if (request.getRequestLine().contains("create")) {
            create(output);
        } else if (request.getRequestLine().contains("delete")) {
            delete(output);
        } else if(request.getRequestLine().contains("params")){
            params(output);
        }else {
            Thread.sleep(2000);
        }

        socket.close();
    }
}