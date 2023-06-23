package com.InventoryManagement.server;

import com.InventoryManagement.dm.InventoryItem;

public class Request {

	public enumActions actionType;
    public InventoryItem item;
    public enumAlgorithmType algoType;
    public String key;
    
//    public Request(String key) {
//    	this.key = key;
//    }

    // for Search action
    public Request(String keyWord, enumActions actionType, enumAlgorithmType algoType) {
        this.actionType = actionType;
        this.algoType = algoType;
        this.key = keyWord;
    }
    
    // for Remove action
    public Request(enumActions actionType,String keyWord) {
    	this.actionType = actionType;
    	this.key = keyWord;
    }

    // for Add action
    public Request(enumActions actionType, InventoryItem item) {
        this.actionType = actionType;
        this.item = item;
    }
    

    public void setKey(String key) {
    	this.key = key;
    }

    public String getKey() {
    	return key;
    }

    public enumActions getActionType() {
        return actionType;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }


    public enumAlgorithmType getAlgoType() {
        return algoType;
    }

    public void setActionType(enumActions actionType) {
        this.actionType = actionType;
    }


    public void setAlgoType(enumAlgorithmType algoType) {
        this.algoType = algoType;
    }

}
