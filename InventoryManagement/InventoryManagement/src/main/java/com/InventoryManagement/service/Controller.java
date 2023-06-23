package com.InventoryManagement.service;

import java.io.IOException;
import java.util.ArrayList;

import com.InventoryManagement.server.Request;
import com.InventoryManagement.server.Response;
import com.InventoryManagement.algorithm.KMPStringSearching;
import com.InventoryManagement.algorithm.NaiveStringSearching;
import com.InventoryManagement.dm.InventoryItem;

public class Controller {

	InventoryManagementService searchService;

	public Controller() {
		searchService = new InventoryManagementService(new KMPStringSearching());
	}

	public Response PreformAction(Request request) throws IOException, ClassNotFoundException {
		Response response = new Response();

		switch (request.getActionType()) {
		case Add: {
			response = HandleAdd(request);
			break;
		}
		case Remove: {
			response = HandleRemove(request);
			break;
		}
		case Search: {
			assert response != null;
			response.arr = HandleSearch(request);
		}

		}
		return response;
	}

	private Response HandleAdd(Request request) throws ClassNotFoundException, IOException {
		boolean add;
		add = searchService.addItem(request.getItem());
		Response response = new Response();
		if (!add) {
			response.setErrorMes("Error, Failed to Add item");
			response.setSuccess(false);
		}
		return response;

	}

	private Response HandleRemove(Request request) throws IOException {
		boolean remove;
		Response response = new Response();
//        remove = searchService.removeItem(request.getItem());
		remove = searchService.removeItem(request.getKey().toLowerCase());
		if (!remove) {
			response.setErrorMes("Failed to remove item");
			response.setSuccess(false);
		} else {
			response.setErrorMes("Item was removed successfully!");
			response.setSuccess(true);
		}
		return response;
	}

	private ArrayList<InventoryItem> HandleSearch(Request request) throws IOException {

		ArrayList<InventoryItem> resList = new ArrayList<InventoryItem>();
		// Response response = new Response();
		switch (request.getAlgoType()) {
		case KMP:
			System.out.println("KMP");
			searchService.setAlgo(new KMPStringSearching());
			break;
		case Naive:
			System.out.println("Naive");
			searchService.setAlgo(new NaiveStringSearching());
			break;
		}
		resList = searchService.serachItem(request.getKey());

		return resList;
	}

}
