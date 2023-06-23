package application;

import javafx.scene.control.Alert;

public class Validation {

	public Boolean handleAddProductButtonFlow(String txtName, String txtQuantity, String txtPrice,
			String txtDescription, Alert alert) {
		if (txtName.isEmpty()) {
			alert.setTitle("Name is empty!");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in the input field.");
			alert.showAndWait();
			return false;
		}
		if (txtQuantity.isEmpty()) {
			alert.setTitle("Quantity is empty!");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in the input field.");
			alert.showAndWait();
			return false;
		}
		if (txtPrice.isEmpty()) {
			alert.setTitle("Price is empty!");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in the input field.");
			alert.showAndWait();
			return false;
		}
		if (txtDescription.isEmpty()) {
			alert.setTitle("Description is empty!");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in the input field.");
			alert.showAndWait();
			return false;
		}
		return true;
	}

	public Boolean handleRemoveProductButtonFlow(String txtProductNameRemove, Alert alert) {
		if (txtProductNameRemove.isEmpty()) {
			alert.setTitle("Name is empty!");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in the input field.");
			alert.showAndWait();
			return false;
		}
		return true;
	}

}
