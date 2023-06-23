package com.InventoryManagement.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Timer;
import java.util.TimerTask;

public class BackupAndRestore {

	private Timer timer;

	public BackupAndRestore() {
		timer = new Timer();
	}

	public void backup(String fromFilePath, String toPathBackup, long delay, long period) {
		TimerTask backupTask = new TimerTask() {
			public void run() {
				try {
					Path source = Path.of(fromFilePath);
					Path destination = Path.of(toPathBackup);
					Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Backup successful!");
				} catch (IOException e) {
					System.out.println("Failed to perform backup: " + e.getMessage());
				}
			}
		};
		timer.scheduleAtFixedRate(backupTask, delay, period);
	}

	public Object restore(String fromFilePath) {
		try {
			byte[] fileContent = Files.readAllBytes(Path.of(fromFilePath));
			String content = new String(fileContent);
			return content;
		} catch (IOException e) {
			System.out.println("Failed to restore backup: " + e.getMessage());
			return null;
		}
	}
}
