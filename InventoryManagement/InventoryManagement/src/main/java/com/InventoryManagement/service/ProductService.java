//package com.InventoryManagement.service;
//import java.io.IOException;
//import java.util.List;
//
//import com.InventoryManagement.dao.IDao;
//import com.InventoryManagement.dm.InventoryItem;
//
//public class ProductService {
//	
//    private IDao<InventoryItem> dao;
//    
//	public ProductService(IDao<InventoryItem> dao) {
//        this.dao = dao;
//    }
//	
//	// Updates the price of an existing inventory item
//    public void addProduct(InventoryItem InventoryItem) throws ClassNotFoundException, IOException {
//        dao.add(InventoryItem);
//    }
//    
//    // Get product by integer (id)
//    public InventoryItem getProductById(int id) throws ClassNotFoundException, IOException {
//        List<InventoryItem> InventoryItems = dao.getAll();
//        for (InventoryItem InventoryItem : InventoryItems) {
//            if (InventoryItem.getId() == id) {
//                return InventoryItem;
//            }
//        }
//        return null; // InventoryItem not found
//    }
//    
//    // Get product by string (name)
//    public InventoryItem searchProductByName(String name) throws IOException {
//        return dao.search(name);
//    }
//    
//    // Update product by searching the same Id
//    public void updateProduct(InventoryItem InventoryItem) throws IOException {
//        dao.update(InventoryItem);
//    }
//    
//    // Delete product
//    public void deleteProduct(InventoryItem InventoryItem) throws IOException {
//        dao.delete(InventoryItem);
//    }
//    
//}
//
