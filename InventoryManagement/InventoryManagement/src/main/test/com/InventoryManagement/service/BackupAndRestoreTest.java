package com.InventoryManagement.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Provider.Service;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;

import com.InventoryManagement.util.BackupAndRestore;

import org.junit.jupiter.api.Test;


public class BackupAndRestoreTest {

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
		Assertions.assertEquals(dataForBackupAndRestore, backupContent, "Backup content does not match");
	}

	@Test
	public void testRestore() throws IOException {
		String fromFilePath = "src/main/resources/datasource_backup.txt";

		BackupAndRestore backupAndRestore = new BackupAndRestore();
		Object restoredContent = backupAndRestore.restore(fromFilePath);

		Assertions.assertNotNull(restoredContent, "Restored content is null");
		Assertions.assertEquals(dataForBackupAndRestore, restoredContent.toString(), "Restored content does not match");
	}
}
