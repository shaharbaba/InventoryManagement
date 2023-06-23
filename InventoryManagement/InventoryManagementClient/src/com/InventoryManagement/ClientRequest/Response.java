package com.InventoryManagement.ClientRequest;

import java.util.ArrayList;

import com.InventoryManagement.dm.InventoryItem;

public class Response {
	public boolean success = true;
    public ArrayList<InventoryItem> arr;
    public String errorMessage;

    public Response() {
    	
    }
}
