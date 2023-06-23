package com.InventoryManagement.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.InventoryManagement.dm.InventoryItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MyDMFileImpl implements IDao<InventoryItem> {
    
    private String pathFile;
    private final Gson gson = new Gson();

    public MyDMFileImpl(String file) {
		this.pathFile = file;
	}

	public void save(InventoryItem item) throws IOException {
        List<InventoryItem> items = getAll();
        items.add(item);
        saveAll(items);
    }

    public void saveAll(List<InventoryItem> items) throws IOException {
        String json = gson.toJson(items);
        try (Writer writer = new FileWriter(pathFile)) {
            writer.write(json);
        }
    }

    public ArrayList<InventoryItem> getAll() throws IOException {
        try (Reader reader = new FileReader(pathFile)) {
        	ArrayList<InventoryItem> items = gson.fromJson(reader, new TypeToken<ArrayList<InventoryItem>>(){}.getType());
            if (items == null) {
                items = new ArrayList<>();
            }
            return items;
        }
    }

    public boolean add(InventoryItem item) throws IOException, ClassNotFoundException {
        List<InventoryItem> items = getAll();
        items.add(item);
        try {
        saveAll(items);
        }
        catch (IOException e){
        	e.printStackTrace();
        	return false;
        }
        return true;
        
    }

    public void update(InventoryItem item) throws IOException {
        List<InventoryItem> items = getAll();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                items.set(i, item);
                break;
            }
        }
        saveAll(items);
    }

    public boolean delete(InventoryItem item) throws IOException {
        List<InventoryItem> items = getAll();
        items.remove(item);
        try {
            saveAll(items);
            }
            catch (IOException e){
            	e.printStackTrace();
            	return false;
            }
            return true;
    }
    
    public boolean delete(String keyword) throws IOException {
    	List<InventoryItem> items = getAll();
    	String newKeyword = keyword.replaceAll("\"", "");
    	for(InventoryItem item : items) {
    		if (item.getDescription().toLowerCase().contains(newKeyword) || item.getName().toLowerCase().contains(newKeyword)) {
    			items.remove(item);
    			try {
    	            saveAll(items);
    	            }
    	            catch (IOException e){
    	            	e.printStackTrace();
    	            	return false;
    	            }
    	            return true;
    		}
    	}
    	return false;
        
    }

    
    public List<InventoryItem> search(String keyword) throws IOException {
        List<InventoryItem> items = getAll();
        List<InventoryItem> result = new ArrayList<>();
        for (InventoryItem item : items) {
            if (item.getDescription().contains(keyword) || item.getName().contains(keyword)) {
                result.add(item);
            }
        }
        return result;
    }

}





//package com.InventoryManagement.dao;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.google.gson.*;
//
//import com.InventoryManagement.algorithm.IAlgoStringSearching;
//import com.InventoryManagement.dm.InventoryItem;
//
//public class MyDMFileImpl implements IDao<InventoryItem> {
//    private String pathFile;
//    private IAlgoStringSearching searchAlgo;
//
//    public MyDMFileImpl(String pathFile, IAlgoStringSearching searchAlgo) {
//        this.pathFile = pathFile;
//        this.searchAlgo = searchAlgo;
//    }
//
//	  @Override
//	  public List<InventoryItem> getAll() throws IOException {
//	      List<InventoryItem> inventoryItems = new ArrayList<>();
//	      try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {
//	          String line;
//	          while ((line = reader.readLine()) != null) {
//	              String[] fields = line.split(",");
//	              int id = Integer.parseInt(fields[0]);
//	              String name = fields[1];
//	              String description = fields[2];
//	              int quantity = Integer.parseInt(fields[3]);
//	              double price = Double.parseDouble(fields[4]);
//	              inventoryItems.add(new InventoryItem(id, name, description, quantity, price));
//	          }
//	      }
//	      return inventoryItems;
//	  }
//
//    @Override
//    public void save(InventoryItem item) {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile, true));
//            bw.write(item.toString() + "\n");
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void update(InventoryItem item) throws IOException {
//        List<InventoryItem> items = getAll();
//        for (int i = 0; i < items.size(); i++) {
//            if (items.get(i).getId() == item.getId()) {
//                items.set(i, item);
//                break;
//            }
//        }
//        saveAll(items);
//    }
//
//	@Override
//	  public void delete(InventoryItem inventoryItem) throws IOException {
//	      List<InventoryItem> inventoryItems = getAll();
//	      inventoryItems.remove(inventoryItem);
//	      saveAll(inventoryItems);
//	  }
//	
//	@Override
//    public InventoryItem search(String keyword) throws IOException {
//        List<InventoryItem> items = getAll();
//        for (InventoryItem item : items) {
//            if (searchAlgo.search(item.getName(), keyword)) {
//                return item;
//            }
//        }
//        return null;
//    }
//
//	@Override
//    public void add(InventoryItem item) throws IOException {
//        List<InventoryItem> items = getAll();
//        int id = 1;
//        if (items.size() > 0) {
//            id = items.get(items.size() - 1).getId() + 1;
//        }
//        item.setId(id);
//        items.add(item);
//        saveAll(items);
//    }
//
//	@Override
//    public void saveAll(List<InventoryItem> items) {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile, false));
//            for (InventoryItem item : items) {
//                bw.write(item.toString() + "\n");
//            }
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
