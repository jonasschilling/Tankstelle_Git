package javafx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.corba.se.impl.io.TypeMismatchException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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

	public void changePrices(ActionEvent actionEvent) {
		boolean check = false;
		try {
			if (!newSuperPrice.getText().equals("")) {
				tankModel.getTank("Super").setPricePerLitre(Float.valueOf(newSuperPrice.getText()));
				tankModel.writePrice("Super", newSuperPrice.getText());
			}
			if (!newDieselPrice.getText().equals("")) {
				tankModel.getTank("Diesel").setPricePerLitre(Float.valueOf(newDieselPrice.getText()));
				tankModel.writePrice("Diesel", newDieselPrice.getText());
			}
			if (!newWodkaPrice.getText().equals("")) {
				salesModel.getProduct("Wodka").setPriceSell(Float.valueOf(newWodkaPrice.getText()));
				salesModel.writePrice("Wodka", newWodkaPrice.getText());
			}
			if (!newFilipPrice.getText().equals("")) {
				salesModel.getProduct("Filip").setPriceSell(Float.valueOf(newFilipPrice.getText()));
				salesModel.writePrice("Filip", newFilipPrice.getText());
			}
			if (!newJupiterPrice.getText().equals("")) {
				salesModel.getProduct("Jupiter").setPriceSell(Float.valueOf(newJupiterPrice.getText()));
				salesModel.writePrice("Jupiter", newJupiterPrice.getText());
			}
			if (!newBullPrice.getText().equals("")) {
				salesModel.getProduct("Bull").setPriceSell(Float.valueOf(newBullPrice.getText()));
				salesModel.writePrice("Bull", newBullPrice.getText());
			}
			if (!newPizzaPrice.getText().equals("")) {
				salesModel.getProduct("Pizza").setPriceSell(Float.valueOf(newPizzaPrice.getText()));
				salesModel.writePrice("Pizza", newPizzaPrice.getText());
			}
			check = true;
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Ungültige Preiseingabe.");
			alert.show();
			check = false;
		}

		Stage popup = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		if (check) {
			popup.hide();
		}
	}

	public void cancel(ActionEvent actionEvent) {
		Stage popup = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		popup.hide();
	}

}
