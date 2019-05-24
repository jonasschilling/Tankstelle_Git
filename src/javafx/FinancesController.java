package javafx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FinancesController implements Initializable {
	@FXML
	Label balance, receipts;
	@FXML
	TableView<Receipt> tableView;
	@FXML
	TableColumn<Receipt, String> date;
	@FXML
	TableColumn<Receipt, String> type;
	@FXML
	TableColumn<Receipt, String> path;
	Stage popup = new Stage();
	private ObservableList<Receipt> receiptlist = FXCollections.observableArrayList();
	ReceiptModel model = ReceiptModel.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File f = new File("C:\\Users\\d073551\\Desktop\\Einkaufsbeleg.txt");
		String p = f.getPath();
		Receipt R1 = new Receipt("20.05.2019", "Einkaufsbeleg", p);
		date.setCellValueFactory(new PropertyValueFactory<Receipt, String>("date"));
		type.setCellValueFactory(new PropertyValueFactory<Receipt, String>("type"));
		path.setCellValueFactory(new PropertyValueFactory<Receipt, String>("path"));
		receiptlist.add(R1);
		tableView.setItems(receiptlist);

	}

	public void openFile(ActionEvent actionEvent) throws IOException {
		ObservableList<Receipt> selectedCells = tableView.getSelectionModel().getSelectedItems();
		model.setPath(selectedCells.get(0).getPath());
		System.out.println(selectedCells.get(0).getPath());
		Parent root = FXMLLoader.load(getClass().getResource("showReceipt.fxml"));
		Scene scene = new Scene(root);
//		scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
		popup.setTitle("Beleg");
		popup.setScene(scene);
		popup.show();

	}

}
