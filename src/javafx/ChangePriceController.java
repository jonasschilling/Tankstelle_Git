package javafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Jonas Schilling
 *
 */
public class ChangePriceController implements Initializable {

	@FXML
	TextField newSuperPrice;
	@FXML
	TextField newDieselPrice;
	@FXML
	TextField newWodkaPrice;
	@FXML
	TextField newFilipPrice;
	@FXML
	TextField newJupiterPrice;
	@FXML
	TextField newBullPrice;
	@FXML
	TextField newPizzaPrice;

	TankModel tankModel = TankModel.getInstance();
	SalesModel salesModel = SalesModel.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		newSuperPrice.setPromptText(tankModel.readPrice("Super"));
		newDieselPrice.setPromptText(tankModel.readPrice("Diesel"));
		newWodkaPrice.setPromptText(tankModel.readPrice("Wodka"));
		newFilipPrice.setPromptText(tankModel.readPrice("Filip"));
		newJupiterPrice.setPromptText(tankModel.readPrice("Jupiter"));
		newBullPrice.setPromptText(tankModel.readPrice("Bull"));
		newPizzaPrice.setPromptText(tankModel.readPrice("Pizza"));
	}

	// Prüft ob die Preise im korrekten Format angegeben wurden.
	// Wenn ja -> ändert Preise
	// Wenn nein -> Alert-Popup und markiert die fehlerhaften Felder rot
	public void changePrices(ActionEvent actionEvent) {
		boolean check = false;
		ArrayList<TextField> tf = new ArrayList<>();
		try {
			if (!newSuperPrice.getText().equals("")) {
				tankModel.getTank("Super").setPricePerLitre(Float.valueOf(newSuperPrice.getText()));
				tankModel.writePrice("Super", newSuperPrice.getText());
			}
		} catch (NumberFormatException e) {
			tf.add(newSuperPrice);
			check = true;
		}
		try {
			if (!newDieselPrice.getText().equals("")) {
				tankModel.getTank("Diesel").setPricePerLitre(Float.valueOf(newDieselPrice.getText()));
				tankModel.writePrice("Diesel", newDieselPrice.getText());
			}
		} catch (NumberFormatException e) {
			tf.add(newDieselPrice);
			check = true;
		}
		try {
			if (!newWodkaPrice.getText().equals("")) {
				salesModel.getProduct("Wodka").setPriceSell(Float.valueOf(newWodkaPrice.getText()));
				salesModel.writePrice("Wodka", newWodkaPrice.getText());
			}
		} catch (NumberFormatException e) {
			tf.add(newWodkaPrice);
			check = true;
		}
		try {
			if (!newFilipPrice.getText().equals("")) {
				salesModel.getProduct("Filip").setPriceSell(Float.valueOf(newFilipPrice.getText()));
				salesModel.writePrice("Filip", newFilipPrice.getText());
			}
		} catch (NumberFormatException e) {
			tf.add(newFilipPrice);
			check = true;
		}
		try {
			if (!newJupiterPrice.getText().equals("")) {
				salesModel.getProduct("Jupiter").setPriceSell(Float.valueOf(newJupiterPrice.getText()));
				salesModel.writePrice("Jupiter", newJupiterPrice.getText());
			}
		} catch (NumberFormatException e) {
			tf.add(newJupiterPrice);
			check = true;
		}
		try {
			if (!newBullPrice.getText().equals("")) {
				salesModel.getProduct("Bull").setPriceSell(Float.valueOf(newBullPrice.getText()));
				salesModel.writePrice("Bull", newBullPrice.getText());
			}
		} catch (NumberFormatException e) {
			tf.add(newBullPrice);
			check = true;
		}

		try {
			if (!newPizzaPrice.getText().equals("")) {
				salesModel.getProduct("Pizza").setPriceSell(Float.valueOf(newPizzaPrice.getText()));
				salesModel.writePrice("Pizza", newPizzaPrice.getText());
			}
		} catch (NumberFormatException e) {
			tf.add(newPizzaPrice);
			check = true;
		}
		if (check) {
			for (TextField textField : tf) {
				textField.setStyle("-fx-text-inner-color: rgb(255, 0, 0)");
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Ungültige Preiseingabe.");
			alert.show();
			check = false;
		} else {
			Stage popup = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			popup.hide();
		}
	}

	// Schließt Popup
	public void cancel(ActionEvent actionEvent) {
		Stage popup = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		popup.hide();
	}

}
