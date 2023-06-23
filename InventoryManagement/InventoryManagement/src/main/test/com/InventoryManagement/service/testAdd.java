package com.InventoryManagement.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.JsonObject;

public class testAdd {

	public static void main(String[] args) {
		String actionType = "Add";
		String item = "{\"name\":\"" + "test book2" + "\",\"description\":\"" + "This is a test book2"
				+ "\",\"quantity\":\"" + 5 + "\",\"price\":\"" + "50.5" + "\"}";

		try {
			Socket socket = new Socket("localhost", 34567);

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner in = new Scanner(socket.getInputStream());

//	            String request = 
//
//	            		"{\"actionType\":\"" + actionType + "\",\"item\":" + item + "\"}";
//	            		System.out.println(request);

			JsonObject itemForRequest = new JsonObject();
			itemForRequest.addProperty("name", "test book");
			itemForRequest.addProperty("description", "This is a test book");
			itemForRequest.addProperty("quantity", 5);
			itemForRequest.addProperty("price", 50.5);
			System.out.print(item);

			JsonObject requestValue = new JsonObject();
			requestValue.addProperty("actionType", actionType);
			requestValue.addProperty("item", itemForRequest.toString());

			out.println(requestValue);

			// Close socket and I/O streams
			socket.close();
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
