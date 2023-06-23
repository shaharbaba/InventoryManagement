//package com.InventoryManagement.service;
//import java.io.IOException;
//import java.util.List;
//
//import com.InventoryManagement.dao.IDao;
//import com.InventoryManagement.dm.InventoryItem;
//
//public class InventoryService {
//    private IDao<InventoryItem> dao;
//
//    public InventoryService(IDao<InventoryItem> dao) {
//        this.dao = dao;
//    }
//
//    // Alert about low stock
//    public void alertLowStock() throws ClassNotFoundException, IOException {
//        List<InventoryItem> items = dao.getAll();
//        for (InventoryItem item : items) {
//            if (item.getQuantity() < 10) {
//                System.out.println("Low stock alert: item " + item.getId() + " has only " + item.getQuantity() + " left");
//            }
//        }
//    }
//    
//    // Updates the price of an existing inventory item
//    public void updateItemPrice(String itemId, double newPrice) throws IOException {
//        InventoryItem itemToUpdate = dao.search(itemId);
//        itemToUpdate.setPrice(newPrice);
//        dao.update(itemToUpdate);
//    }
//    
//    // Updates the quantity of an existing inventory item
//    public void updateItemQuantity(String itemId, int newQuantity) throws IOException {
//        InventoryItem itemToUpdate = dao.search(itemId);
//        itemToUpdate.setQuantity(newQuantity);
//        dao.update(itemToUpdate);
//    }
//
//}
//
//
