package javafx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
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
	@FXML
	DatePicker datepickerFrom, datepickerTo;
	Stage popup = new Stage();
	private ObservableList<Receipt> receiptlist = FXCollections.observableArrayList();
	ReceiptModel receiptmodel = ReceiptModel.getInstance();
	Model model = Model.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File f = new File("C:\\Users\\d073551\\Desktop\\Einkaufsbeleg.txt");
		String p = f.getPath();
		Receipt R1 = new Receipt("20.05.2019", "Einkaufsbeleg", p);
		File f2 = new File("C:\\Users\\d073551\\Desktop\\Einkaufsbeleg2.txt");
		String p2 = f2.getPath();
		Receipt R2 = new Receipt("13.05.2019", "Einkaufsbeleg2", p2);
		date.setCellValueFactory(new PropertyValueFactory<Receipt, String>("date"));
		type.setCellValueFactory(new PropertyValueFactory<Receipt, String>("type"));
		path.setCellValueFactory(new PropertyValueFactory<Receipt, String>("path"));
		receiptlist.add(R1);
		receiptlist.add(R2);
		datepickerFrom.setPromptText("von");
		datepickerTo.setPromptText("bis");
		tableView.setItems(receiptlist);
		balance.setText("Bilanz:         " + Float.toString(model.getBalance()) + " €");

	}

	public void openFile(ActionEvent actionEvent) throws IOException {
		ObservableList<Receipt> selectedCells = tableView.getSelectionModel().getSelectedItems();
		receiptmodel.setPath(selectedCells.get(0).getPath());
		Parent root = FXMLLoader.load(getClass().getResource("showReceipt.fxml"));
		Scene scene = new Scene(root);
//		scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
		popup.setTitle("Beleg");
		popup.setScene(scene);
		popup.show();

	}
	
	public void filter(ActionEvent actionEvent) {
		ObservableList<Receipt> tempreceiptlist = FXCollections.observableArrayList();
		LocalDate fromDate = datepickerFrom.getValue();
		LocalDate toDate = datepickerTo.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String from = fromDate.format(formatter);
		String to = toDate.format(formatter);
		for(int i = 0; i < receiptlist.size(); i++) {
			if(receiptlist.get(i).getDate().compareTo(from) >= 0 && receiptlist.get(i).getDate().compareTo(to) <= 0) {
				
				tempreceiptlist.add(receiptlist.get(i));
			}
			
		}
		tableView.setItems(tempreceiptlist);
	}

}
