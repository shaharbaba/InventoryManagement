package com.InventoryManagement.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.JsonObject;

public class testSearch {

	public static void main(String[] args) {
		String actionType = "Search";
		String key = "book";
		String algoType = "KMP";

		try {
			Socket socket = new Socket("localhost", 34567);

			JsonObject requestValue = new JsonObject();
			requestValue.addProperty("actionType", actionType);
			requestValue.addProperty("algoType", algoType);
			requestValue.addProperty("key", key);

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner in = new Scanner(socket.getInputStream());
			String request = "{\"actionType\":\"" + actionType + ",\"algoType\":\"" + algoType + "\",\"key\":\"" + key + "\"}";
			System.out.println(request);

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
