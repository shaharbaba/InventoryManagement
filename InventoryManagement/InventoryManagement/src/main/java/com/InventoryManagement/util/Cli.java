package com.InventoryManagement.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Cli implements Runnable{

	private Scanner input;
    private PrintWriter output;
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private String value = null;

    public Cli(InputStream in, OutputStream out) {
        input = new Scanner(in);
        output = new PrintWriter(out);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        this.changes.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        this.changes.removePropertyChangeListener(pcl);
    }

    @Override
    public void run() {
        String clientInput = null;

        while (true) {
            write("Enter start to start the server, or stop to stop the server");
            clientInput = input.nextLine().toLowerCase();

            if(clientInput.equals("start")){
                write("Starting the server");
                setValue(clientInput);
            }

            else if(clientInput.equals("stop")) {
                write("Stopping the server");
                setValue(clientInput);
            }
            else write("Invalid command");
        }
    }

    public void write(String string) {
        output.println(string);
        output.flush();
    }

    public void setValue(String newValue) {
        this.value = newValue;
        this.changes.firePropertyChange("value",null, newValue);
    }
    
    public String getValue() {
        return this.value;
    }



	
}
