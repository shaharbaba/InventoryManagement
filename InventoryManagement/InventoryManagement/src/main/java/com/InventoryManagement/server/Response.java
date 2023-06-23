package com.InventoryManagement.server;

import java.util.ArrayList;

import com.InventoryManagement.dm.InventoryItem;

public class Response {

	public boolean success = true;
    public ArrayList<InventoryItem> arr;
    public String errorMessage;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMes() {
        return errorMessage;
    }

    public void setErrorMes(String message) {
        this.errorMessage = message;
    }
}
