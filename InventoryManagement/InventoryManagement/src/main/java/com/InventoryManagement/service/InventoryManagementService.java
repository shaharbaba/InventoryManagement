package com.InventoryManagement.service;

import java.io.IOException;
import java.util.ArrayList;

import com.InventoryManagement.algorithm.IAlgoStringSearching;
import com.InventoryManagement.dao.MyDMFileImpl;
import com.InventoryManagement.dm.InventoryItem;

// can be added set price
public class InventoryManagementService {

	IAlgoStringSearching algo;
	MyDMFileImpl dataSource;

	public InventoryManagementService(IAlgoStringSearching algoType) {

		this.algo = algoType;
		this.dataSource = new MyDMFileImpl("src/main/resources/datasource.txt");
	}

	public boolean addItem(InventoryItem item) throws ClassNotFoundException, IOException {
		if (item == null) {
			return false;
		}
		if (!item.isValid()) {
			return false;
		}
		boolean suc = dataSource.add(item);
		if (!suc) {
			return false;
		}
		return true;

	}

	public boolean removeItem(InventoryItem item) throws IOException {
		if (item == null) {
			return false;
		}
		boolean suc = dataSource.delete(item);
		if (!suc) {
			return false;
		}
		return true;
	}

	public boolean removeItem(String key) throws IOException{
		if (key == null) {
			return false;
		}
		boolean suc = dataSource.delete(key);
		if(suc == true)
			return true;
		return false;
	}

	
	public ArrayList<InventoryItem> serachItem(String key) throws IOException {
		if (key == null || key.isEmpty()) {
			return null;
		}
		ArrayList<InventoryItem> arr = dataSource.getAll();
		ArrayList<InventoryItem> resList = new ArrayList<InventoryItem>();

		if(key.contains("*"))
			return arr;
		
		for (InventoryItem item : arr) {
			String itemName, descriptionValue, keyValue ;
			
			// Cleaning unnecessary quotation marks from - name
			char startChar = item.getName().toCharArray()[0];
			char endChar = item.getName().toCharArray()[item.getName().length()-1];
			if(startChar == 34 && endChar == 34) {
				itemName = item.getName().substring(1, item.getName().length()-1);
				
			} else {
				itemName = item.getName();
			}
			
			// Cleaning unnecessary quotation marks from - description
			startChar = item.getDescription().toCharArray()[0];
			endChar = item.getDescription().toCharArray()[item.getDescription().length()-1];
			if(startChar == 34 && endChar == 34) {
				descriptionValue = item.getDescription().substring(1, item.getDescription().length()-1);
				
			} else {
				descriptionValue = item.getDescription();
			}
			
			// Cleaning unnecessary quotation marks from - key
			startChar = key.toCharArray()[0];
			endChar = key.toCharArray()[key.length()-1];
			if(startChar == 34 && endChar == 34) {
				keyValue = key.substring(1, key.length()-1);
				
			} else {
				keyValue = key;
			}
			
			boolean SearchRes = algo.search(itemName, keyValue);
			boolean DescRes = algo.search(descriptionValue, keyValue);
			if (SearchRes || DescRes) {
				resList.add(item);
			}
		}

		return resList;
	}

	public int itemPrice(InventoryItem item) throws IOException {
		if (item == null) {
			return 0;
		}
		ArrayList<InventoryItem> arr = dataSource.getAll();
		for (InventoryItem tempItem : arr) {
			boolean SearchRes = algo.search(tempItem.getName(), item.getName());
			boolean DescRes = algo.search(tempItem.getDescription(), item.getName());
			if (SearchRes || DescRes) {
				return (int) item.getPrice();
			}
		}

		return 0;
	}

	public IAlgoStringSearching getAlgo() {
		return algo;
	}

	public void setAlgo(IAlgoStringSearching algo) {
		this.algo = algo;
	}


}
