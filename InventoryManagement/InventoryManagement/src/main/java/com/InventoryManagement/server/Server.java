package com.InventoryManagement.server;

import com.InventoryManagement.service.Controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements PropertyChangeListener, Runnable {

    public Boolean isRunning = false;
    int port;
    ServerSocket serversocket;
    Socket clientSocket;
    ExecutorService executer = null;
    Controller controller;

    public Server(int port) {

        this.port = port;

    }

    @Override
    public void run() {
        try {
            controller = new Controller();
            executer = Executors.newFixedThreadPool(3);
            serversocket = new ServerSocket(port);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (isRunning) {
            try {

                clientSocket = serversocket.accept();
                executer.execute(new HandleRequest(clientSocket, controller));

            } catch (Exception e) {
                System.out.println("The server has stopped listening.");
                System.exit(port);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String action = (String) evt.getNewValue();

        switch (action) {
            case "start":

                if (!isRunning) {

                    isRunning = true;
                    new Thread(this).start();
                    break;
                } else
                    System.out.println("The server is already Active.\n");
                break;

            case "stop":
                if (!isRunning)
                    System.out.println("The server is already Inactive.\n");
                else {
                    try {
                        isRunning = false;

                        this.serversocket.close();
                    } catch (Exception e) {
                        System.out.println("Server stopped.");
                    }
                }
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }
}
