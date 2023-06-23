package com.InventoryManagement.ClientRequest;

import com.InventoryManagement.dm.InventoryItem;

public class Request {
    public enumActions actionType;
    public InventoryItem item;
    public enumAlgorithmType algoType;
    public String key;

    public Request() {
    	
    }

    public Request(enumActions actionType, InventoryItem item, String key,enumAlgorithmType algoType) {
        this.actionType = actionType;
        this.item = item;
        this.key = key;
        this.algoType = algoType;
    }
    
    public Request(enumActions actionType, String key,enumAlgorithmType algoType) {
        this.actionType = actionType;
        this.key = key;
        this.algoType = algoType;
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

    public String getKeyWord() {
        return key;
    }

    public enumAlgorithmType getAlgoType() {
        return algoType;
    }

    public void setActionType(enumActions actionType) {
        this.actionType = actionType;
    }

    public void setKeyWord(String keyWord) {
        this.key = keyWord;
    }

    public void setAlgoType(enumAlgorithmType algoType) {
        this.algoType = algoType;
    }
}
