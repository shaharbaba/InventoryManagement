package com.InventoryManagement.server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.InventoryManagement.service.Controller;

import java.io.*;
import java.net.Socket;
//import java.util.Scanner;

import com.InventoryManagement.dm.*;



public class HandleRequest implements Runnable{
    Socket socket;
    Controller controller;

    HandleRequest(Socket clientsocket,Controller controller){
        this.controller = controller;
        this.socket = clientsocket;
    }

    public void run() {

        Request request;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            StringBuilder jsonData = new StringBuilder();
            String line;
			System.out.println("You are here1");
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
            	if(line.equals("null"))
            		break;
                jsonData.append(line);
            }
			System.out.println("You are here2");
            //request = json.fromJson(jsonData,Request.class);
//            String jsonString = jsonData.toString();
//            System.out.println("Received JSON data: " + jsonData.toString());

//            request.setActionType();
            
            Gson gson = new Gson();
            JsonElement jsonElement = gson.fromJson(jsonData.toString().trim(), JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
//            jsonObject = jsonElement.getAsJsonObject();
//            if(jsonElement!= null) {
//            } 
            
            System.out.println("Received JSON data: " + jsonObject.get("actionType"));
            if(jsonObject.get("actionType").getAsString().equals("Add")) {
            	request=new Request(enumActions.valueOf(jsonObject.get("actionType").getAsString()),InventoryItem.valueOf(jsonObject.get("item").getAsString()));            	
            } else if(jsonObject.get("actionType").getAsString().equals("Remove")) {
            	request=new Request(enumActions.valueOf(jsonObject.get("actionType").getAsString()),jsonObject.get("key").toString());            	
            } else if(jsonObject.get("actionType").getAsString().equals("Search")) {
            	request=new Request(jsonObject.get("key").toString(),enumActions.valueOf(jsonObject.get("actionType").getAsString()),enumAlgorithmType.valueOf(jsonObject.get("algoType").getAsString()));
            } else {
            	request = null;
                System.out.println("Action not found");
            }
            System.out.println(request);

            if(request != null) {
	            Response response = controller.PreformAction(request);
	            if(response.success) {            	
	            	System.out.println("Action was done successfully.");
	            	if(jsonObject.get("actionType").getAsString().equals("Search")){
	            		System.out.println("Founded "+ response.arr.size() + " items.");
	            		
	            		// convert ArrayList<InventoryItem> to Json
	            		writer.write(gson.toJson(response.arr));
	            		writer.newLine();
	                    writer.flush();
	            	}
	            }else {
	            	System.out.println("Something went wrong.\n");
	            	System.out.println(response.errorMessage);
	            }
	            
	            writer.close();
	            reader.close();
	            socket.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
