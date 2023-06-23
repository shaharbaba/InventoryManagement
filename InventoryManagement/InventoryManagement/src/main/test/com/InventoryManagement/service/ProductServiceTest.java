package com.InventoryManagement.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.InventoryManagement.dm.InventoryItem;
import com.InventoryManagement.util.BackupAndRestore;


class ProductServiceTest {
	
	ArrayList<InventoryItem> inventoryItems;
    com.InventoryManagement.dao.MyDMFileImpl daoFile = new com.InventoryManagement.dao.MyDMFileImpl("src/main/resources/datasourcetest.txt");


    @Test
    void addItem() throws ClassNotFoundException, IOException {
    	InventoryItem item = new InventoryItem();
    	item.setId(1);
        item.setName("Iphone");
        item.setDescription("New Iphone 12 pro MAX. Coming with a new case!");
        item.setPrice(500);
        item.setQuantity(4);
        boolean suc = daoFile.add(item);
        assertEquals(true,suc,"Failed to add item");
    }
    
    @Test
    void getData() throws IOException {
    	inventoryItems = daoFile.getAll();
        assertEquals(3,inventoryItems.size(),"Get invalid items");
    }
    
    
   @Test
   void removeItem() throws IOException {
	   InventoryItem item = new InventoryItem();
	   item.setId(1);
       item.setName("Iphone");
       item.setDescription("New Iphone 12 pro MAX. Coming with a new case!");
       item.setPrice(55);
       item.setQuantity(3);
       boolean suc = daoFile.delete(item);
       assertEquals(true,suc,"Failed to remove item");
    }
   
   
   
   String dataForBackupAndRestore = "[{\"id\":2,\"name\":\"Test book\",\"description\":\"This is a test book\",\"quantity\":2,\"price\":80.0},{\"id\":1,\"name\":\"Iphone\",\"description\":\"New Iphone 12 pro MAX. Coming with a new case!\",\"quantity\":4,\"price\":500.0}]";
	
	@Test
	public void testBackup() throws IOException, InterruptedException {
		String fromFilePath = "src/main/resources/datasourcetest.txt";
		String toPathBackup = "src/main/resources/datasource_backup.txt";
		long delay = 0; // start the backup withut delay
		long period = TimeUnit.SECONDS.toMillis(10); // every 10 seconds

		BackupAndRestore backupAndRestore = new BackupAndRestore();
		backupAndRestore.backup(fromFilePath, toPathBackup, delay, period);

		TimeUnit.SECONDS.sleep(15);

		// testing if the backup succeeded
		String backupContent = Files.readString(Path.of(toPathBackup));
		assertEquals(dataForBackupAndRestore, backupContent, "Backup content does not match");
	}

	@Test
	public void testRestore() throws IOException {
		String fromFilePath = "src/main/resources/datasource_backup.txt";

		BackupAndRestore backupAndRestore = new BackupAndRestore();
		Object restoredContent = backupAndRestore.restore(fromFilePath);

		// printing the restore content
		System.out.println(restoredContent);
		
		assertNotNull(restoredContent, "Restored content is null");
		assertEquals(dataForBackupAndRestore, restoredContent.toString(), "Restored content does not match");
	}

	
}