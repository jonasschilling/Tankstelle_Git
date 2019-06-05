package javafx;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.ReceiptModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class showReceiptController implements Initializable {

	@FXML
	Label receiptLabel;
	ReceiptModel model = ReceiptModel.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		receiptLabel.setText(model.generateText());

	}

}
