package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author Patrick Berlet
 *
 */
public class ProdInfoController implements Initializable {
	@FXML
	TableView<Product> tableView;
	@FXML
	TableColumn<Product, Integer> colProdNumber;
	@FXML
	TableColumn<Product, String> colName;
	@FXML
	TableColumn<Product, String> colType;
	@FXML
	TableColumn<Product, Integer> colAmount;
	@FXML
	TableColumn<Product, Integer> colMaxAmount;
	@FXML
	TableColumn<Product, Float> colPriceBuy;
	Stage popup = new Stage();
	private ObservableList<Product> products = FXCollections.observableArrayList();
	SalesModel salesModel = SalesModel.getInstance();
	
	public void init() {
		
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		products.addAll(salesModel.getProducts());
		colProdNumber.setCellValueFactory(new PropertyValueFactory<Product, Integer>("prodNumber"));
		colName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		colType.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
		colAmount.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));
		colMaxAmount.setCellValueFactory(new PropertyValueFactory<Product, Integer>("maxAmount"));
		colPriceBuy.setCellValueFactory(new PropertyValueFactory<Product, Float>("priceBuy"));
		tableView.setItems(products);
	
	}
	
	
	
	
	
	
}
