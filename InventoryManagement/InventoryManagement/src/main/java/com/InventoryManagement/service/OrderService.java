//package com.InventoryManagement.service;
//
//import java.io.IOException;
//import java.util.List;
//
//import com.InventoryManagement.dao.IDao;
//import com.InventoryManagement.dm.InventoryItem;
//
//public class OrderService {
//    
//    private IDao<InventoryItem> dao;
//
//    public OrderService(IDao<InventoryItem> dao) {
//        this.dao = dao;
//    }
//
//    // Place order and update the quantity
//    public boolean placeOrder(List<InventoryItem> orderItems) throws IOException {
//        boolean orderFulfilled = true;
//
//        for (InventoryItem item : orderItems) {
//            int orderedQuantity = item.getQuantity();
//            int availableQuantity = dao.search(item.getName()).getQuantity();
//
//            if (orderedQuantity > availableQuantity) {
//                orderFulfilled = false;
//                break;
//            }
//        }
//
//        if (orderFulfilled) {
//            for (InventoryItem item : orderItems) {
//                int orderedQuantity = item.getQuantity();
//                InventoryItem inventoryItem = dao.search(item.getName());
//
//                inventoryItem.setQuantity(inventoryItem.getQuantity() - orderedQuantity);
//                dao.update(inventoryItem);
//            }
//
//            System.out.println("Order placed successfully!");
//            return true;
//        } else {
//            System.out.println("Sorry, we are not able to fulfill your order at this time.");
//            return false;
//        }
//    }
//}
//
