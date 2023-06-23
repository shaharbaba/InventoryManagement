package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.InventoryManagement.dm.InventoryItem;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javafx.scene.control.Alert;

public class Backend {

	public Boolean sendAddAction(String txtName, String txtQuantity, String txtPrice, String txtDescription,
			Alert alert) {
		Random random = new Random();
		int id = random.nextInt(10000);

		System.out.println("Adding the product with id number - " + id);

		InventoryItem item = new InventoryItem(id, txtName.toString(), txtDescription.toString(),
				Integer.valueOf(txtQuantity.toString()), Double.valueOf(txtPrice.toString()));

		JsonObject itemForRequest = new JsonObject();
		itemForRequest.addProperty("id", item.getId());
		itemForRequest.addProperty("name", item.getName());
		itemForRequest.addProperty("description", item.getDescription());
		itemForRequest.addProperty("quantity", item.getQuantity());
		itemForRequest.addProperty("price", item.getPrice());

		JsonObject requestValue = new JsonObject();
		requestValue.addProperty("actionType", "Add");
		requestValue.addProperty("item", itemForRequest.toString());

		try {
			Socket socket = new Socket("localhost", 34567);

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner in = new Scanner(socket.getInputStream());

			out.println(requestValue);
			
			// Close socket and I/O streams
			socket.close();
			out.close();
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
			alert.setTitle("Server error");
			alert.setHeaderText(null);
			alert.setContentText("Something went wrong on the server. Check error stack.");
			alert.showAndWait();
			return false;
		}
		alert.setTitle("");
		alert.setHeaderText(null);
		alert.setContentText("The product was added successfully!");
		alert.showAndWait();
		return true;
	}

	public Boolean sendRemoveAction(String txtProductNameRemove, Alert alert) {

		JsonObject requestValue = new JsonObject();
		requestValue.addProperty("actionType", "Remove");
		requestValue.addProperty("key", txtProductNameRemove);
		try {
			Socket socket = new Socket("localhost", 34567);

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner in = new Scanner(socket.getInputStream());

			out.println(requestValue);

			// Close socket and I/O streams
			socket.close();
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			alert.setTitle("Server error");
			alert.setHeaderText(null);
			alert.setContentText("Something went wrong on the server. Check error stack.");
			alert.showAndWait();
			return false;
		}
		alert.setTitle("");
		alert.setHeaderText(null);
		alert.setContentText("The product was removed successfully!");
		alert.showAndWait();
		return true;
	}

	
	
//	public ArrayList<InventoryItem> sendSearchAction(String txtSearch, Alert alert) {
//		public Boolean sendSearchAction(String txtSearch, Alert alert) {
	public StringBuilder sendSearchAction(String txtSearch, Alert alert) {
		// You can choose to use KMP or Naive algorithm.
		String algoType = "KMP";
		
		JsonObject requestValue = new JsonObject();
		requestValue.addProperty("actionType", "Search");
		
		requestValue.addProperty("algoType", algoType);
//		if (txtSearch.isEmpty()){
//			requestValue.addProperty("key", "");
//		}else {
//			requestValue.addProperty("key", txtSearch);
//		}
		requestValue.addProperty("key", txtSearch);
		
		StringBuilder responseJson = new StringBuilder();
		
		try {
			Socket socket = new Socket("localhost", 34567);

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner in = new Scanner(socket.getInputStream());
			System.out.println("You are here 1");
			out.println(requestValue);
			out.println("null");
			out.flush();

			
			System.out.println("You are here 2");
            while (in.hasNextLine()) {
                System.out.println("You are here 3");

                responseJson.append(in.nextLine());
            }
            
            System.out.println("--------------------------");
            System.out.println(responseJson.toString());
            
			//			StringBuilder jsonData = new StringBuilder();
//            while (in.hasNextLine()) {
//                jsonData.append(in.nextLine());
//            }
//            System.out.println("this is the reasult from search action: \n"+jsonData.toString());
//            
//            JsonElement jsonElement = gson.fromJson(jsonData.toString().trim(), JsonElement.class);
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
            
            
			// Close socket and I/O streams
			socket.close();
			out.close();
			in.close();
			
			return responseJson;
		} catch (IOException e) {
			e.printStackTrace();
			alert.setTitle("Server error");
			alert.setHeaderText(null);
			alert.setContentText("Something went wrong on the server. Check error stack.");
			alert.showAndWait();
			//return {};
			
			alert.setTitle("");
			alert.setHeaderText(null);
			alert.setContentText("Something went wrong on the server. Check error stack.");
			alert.showAndWait();
		}
		return responseJson;
	}

}
