package com.InventoryManagement.dao;

import java.util.List;
import java.util.ArrayList;

import com.InventoryManagement.dm.InventoryItem;

import java.io.*;

@SuppressWarnings("hiding")
public interface IDao<InventoryItem> {
	
    void save(InventoryItem item) throws IOException;
    void saveAll(List<InventoryItem> Items) throws IOException;
    ArrayList<InventoryItem> getAll() throws IOException;
	boolean add(InventoryItem item) throws IOException, ClassNotFoundException;
	void update(InventoryItem Item) throws IOException;
	boolean delete(InventoryItem Item) throws IOException;

	List<InventoryItem> search(String keyword) throws IOException;
	

}