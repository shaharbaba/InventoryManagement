package com.InventoryManagement.ClientRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;


public class ClientRequest {

	public ClientRequest() {

    }


public Response send(Request request) {
//    Gson json = new Gson();
	Gson json = new GsonBuilder().serializeNulls().create();
    Response response = new Response();
    Socket socket = null;
    String inputData = null;
    String requestData = null;
    try {
        //json.serializeNulls();
    requestData = json.toJson(request);

    }
    catch (Exception ex) {

        System.out.println("Failed to convert Request to JSON String - ");
        ex.printStackTrace();
    }



    try {
        socket = new Socket("localhost", 34567);

        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            try (Scanner sc = new Scanner(new InputStreamReader(socket.getInputStream()))) {

                pw.println(requestData);
                pw.flush();
                inputData = sc.nextLine();
            }

        }
    }
    catch (UnknownHostException e) {
        e.printStackTrace();
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    finally {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    try{

        response = json.fromJson(inputData,Response.class);

    } catch (JsonSyntaxException e) {

        System.out.println("Failed to Convert Request To JSON - ");
        e.printStackTrace();
    }
    return response;
}


}