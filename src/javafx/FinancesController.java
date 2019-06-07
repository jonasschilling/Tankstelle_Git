package javafx;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

/**
 * @author Thore Bedey
 *
 */
public class FinancesController implements Initializable {
	@FXML
	Label balanceLabel, receipts;
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
	private static ObservableList<Receipt> receiptlist = FXCollections.observableArrayList();
	ReceiptModel receiptModel = ReceiptModel.getInstance();
	SalesModel salesModel = SalesModel.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		salesModel.setFinancesController(this);
		date.setCellValueFactory(new PropertyValueFactory<Receipt, String>("date"));
		type.setCellValueFactory(new PropertyValueFactory<Receipt, String>("type"));
		path.setCellValueFactory(new PropertyValueFactory<Receipt, String>("path"));
		datepickerFrom.setPromptText("von");
		datepickerTo.setPromptText("bis");
		tableView.setItems(receiptlist);
		balanceLabel.setText("Bilanz:      " + String.valueOf(salesModel.getBalance()) + " " + salesModel.getEurosign());

	}

	public void openFile(ActionEvent actionEvent) throws IOException {
		ObservableList<Receipt> selectedCells = tableView.getSelectionModel().getSelectedItems();
		receiptModel.setPath(selectedCells.get(0).getPath());
		Parent root = FXMLLoader.load(getClass().getResource("showReceipt.fxml"));
		Scene scene = new Scene(root);
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
		for (int i = 0; i < receiptlist.size(); i++) {
			if (receiptlist.get(i).getDate().compareTo(from) >= 0 && receiptlist.get(i).getDate().compareTo(to) <= 0) {

				tempreceiptlist.add(receiptlist.get(i));
			}

		}
		tableView.setItems(tempreceiptlist);
	}

	public static ObservableList<Receipt> getReceipts() {
		return receiptlist;

	}

	public void setBalanceLabel() {
		balanceLabel.setText("Bilanz:      " + String.valueOf(salesModel.getBalance()) + " " + salesModel.getEurosign());
	}

}
