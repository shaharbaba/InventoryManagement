module InventoryManagementClient {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires gson;
	
	opens application to javafx.graphics, javafx.fxml;
}
