package com.InventoryManagement.util;

import com.InventoryManagement.server.Server;

public class ServerDriver {
	
	public static void main(String[] args) 
	{
		Cli cli = new Cli(System.in, System.out);
		Server server = new Server(34567);
//		new Thread(server).start();
		cli.addPropertyChangeListener(server);
        new Thread(cli).start();
	}
}