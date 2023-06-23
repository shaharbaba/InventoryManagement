package com.InventoryManagement.dm;

import com.InventoryManagement.server.enumActions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class InventoryItem {
	private int id;
	private String name;
	private String description;
	private int quantity;
	private double price;

	public InventoryItem(int id, String name, String description, int quantity, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

	public InventoryItem() {

	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setId(int id2) {
		this.id = id2;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof InventoryItem)) {
			return false;
		}

		InventoryItem c = (InventoryItem) o;

		return name.equals(c.name) && description.equals(c.description);
	}

	public boolean isValid() {
		if (this.name.isEmpty()) {
			return false;
		}
		return true;
	}

	public static InventoryItem valueOf(String jsonString) {
		JsonParser  parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(jsonString);

        InventoryItem item = new InventoryItem(1,
				jsonObject.get("name").toString(),
				jsonObject.get("description").toString(),
				Integer.valueOf(jsonObject.get("quantity").getAsString()),
				Double.parseDouble(jsonObject.get("price").getAsString()));
		return item;
		}
}
