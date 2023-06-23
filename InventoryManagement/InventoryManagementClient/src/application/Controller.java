package application;

import java.io.IOException;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.InventoryManagement.ClientRequest.*;
import com.InventoryManagement.dm.InventoryItem;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller implements Initializable {

	private Validation validation = new Validation();
	private Backend backend = new Backend();

	@FXML
	private TableView<InventoryItem> searchTable;

	@FXML
	private TableColumn<?, ?> productNameColumn;

	@FXML
	private TableColumn<?, ?> quantityColumn;

	@FXML
	private TableColumn<?, ?> descriptionColumn;

	@FXML
	private TableColumn<?, ?> PriceColumn;

	@FXML
	private Button btnAddProductView;

	@FXML
	private Button btnRemoveProductView;

	@FXML
	private Button btnSearchView;

	@FXML
	private Button btnSearchAction;

	@FXML
	private Button btnRemoveProductAction;

	@FXML
	private Button btnAddAction;

	@FXML
	private Label lbPage;

	@FXML
	private Label lbSearchValue;

	@FXML
	private GridPane pnAddProduct;

	@FXML
	private GridPane pnRemoveProduct;

	@FXML
	private GridPane pnSearch;

	@FXML
	private TextField txtDescription;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtPrice;

	@FXML
	private TextField txtQuantity;

	@FXML
	private TextField txtProductNameRemove;

	@FXML
	private TextField txtSearch;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pnSearch.toFront();
	}

	Alert alert = new Alert(Alert.AlertType.WARNING);

	@FXML
	private void handleClicks(ActionEvent event) throws IOException {
		if (event.getSource() == btnSearchView) {
			lbPage.setText("Search");
			pnSearch.toFront();
		} else if (event.getSource() == btnAddProductView) {
			lbPage.setText("Add Product");
			pnAddProduct.toFront();
		} else if (event.getSource() == btnRemoveProductView) {
			lbPage.setText("Remove Product");
			pnRemoveProduct.toFront();
		}
	}

	@FXML
	private void handleSearch(ActionEvent event) {
//		ClientRequest request = new ClientRequest();
//		Response res = new Response();
//		Request req = new Request(enumActions.Search, lbSearchValue.toString(), enumAlgorithmType.KMP);
//
//		res = request.send(req);
//
//		System.out.print(res.toString());

		Alert alert = new Alert(Alert.AlertType.WARNING);
//		ArrayList<InventoryItem> backendResponse = backend.sendSearchAction(txtSearch.getText(), alert);
		StringBuilder res = backend.sendSearchAction(txtSearch.getText(), alert);
		
		if (res != null) {	
			System.out.println(res.charAt(0));
			Gson gson = new Gson();
	        JsonObject jsonObject = gson.fromJson(res.toString(), JsonObject.class);

//			JsonElement jsonElement = gson.fromJson(res.toString().trim(), JsonElement.class);
//			JsonObject jsonObject = jsonElement.getAsJsonObject();
			
			System.out.println("Search success");
			for(JsonElement product : jsonObject.getAsJsonArray()) {
				
//			InventoryItem item = new InventoryItem(0, jsonObject.get("name").toString(),
//					jsonObject.get("description").toString(), Integer.valueOf(jsonObject.get("quantity").toString()),
//					Double.valueOf(jsonObject.get("price").toString()));
			InventoryItem item = new InventoryItem(0, ((JsonObject) product).get("name").getAsString(),
					((JsonObject) product).get("description").getAsString(), Integer.valueOf(((JsonObject) product).get("quantity").getAsString()),
					Double.valueOf(((JsonObject) product).get("price").getAsString()));

			searchTable.getItems().add(item);
			}
			txtSearch.setText("");
		} else {
			System.out.println("Failed to search.");
		}

	}

	@FXML
	private void handleAddProductButton(ActionEvent event) {

		Alert alert = new Alert(Alert.AlertType.WARNING);
		Boolean response = validation.handleAddProductButtonFlow(txtName.getText(), txtQuantity.getText(),
				txtPrice.getText(), txtDescription.getText(), alert);

		if (response) {
			Boolean backendResponse = backend.sendAddAction(txtName.getText(), txtQuantity.getText(),
					txtPrice.getText(), txtDescription.getText(), alert);
			if (backendResponse) {
				txtName.setText("");
				txtQuantity.setText("");
				txtPrice.setText("");
				txtDescription.setText("");
			}

		}
	}

	@FXML
	private void handleRemoveProductButton(ActionEvent event) {

		Alert alert = new Alert(Alert.AlertType.WARNING);
		Boolean response = validation.handleRemoveProductButtonFlow(txtProductNameRemove.getText(), alert);

		if (response) {
			Boolean backendResponse = backend.sendRemoveAction(txtProductNameRemove.getText(), alert);
			if (backendResponse) {
				txtProductNameRemove.setText("");
			}

		}
	}

}
