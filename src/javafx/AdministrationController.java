package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdministrationController implements Initializable {
	
	@FXML
	Label tankNameLabel1, tankNameLabel2;
	@FXML
	Label superPriceLabel, dieselPriceLabel;
	@FXML
	Label superLevelLabel, dieselLevelLabel;
	@FXML
	Label superCapLabel, dieselCapLabel;
	@FXML
	Button changePriceButton;
	@FXML
	ProgressBar superBar, dieselBar;
	
	
	TankModel tankModel = TankModel.getInstance();
	

	public void initialize(URL arg0, ResourceBundle arg1) {
		
//		superLevelLabel.textProperty().bind(model.getDescription());
//		label.textProperty().bind(model.getText());

		tankNameLabel1.setText(tankModel.getTank("Super").getDescription());
		tankNameLabel2.setText(tankModel.getTank("Diesel").getDescription());

		superPriceLabel.setText(tankModel.readPrice("Super") + " €/L");
		dieselPriceLabel.setText(tankModel.readPrice("Diesel") + " €/L");

		superCapLabel.setText(String.valueOf(tankModel.readFuelLevel("Super") + "/" + tankModel.getTank("Super").getCapacity()) + " L");
		dieselCapLabel.setText(String.valueOf(tankModel.readFuelLevel("Diesel") + "/" + tankModel.getTank("Diesel").getCapacity()) + " L");
		
		superBar.setProgress((double)(Float.valueOf(tankModel.readFuelLevel("Super")) / tankModel.getTank("Super").getCapacity()));
		dieselBar.setProgress((double)(Float.valueOf(tankModel.readFuelLevel("Diesel")) / tankModel.getTank("Diesel").getCapacity()));
		
	}

	public void refresh() {
		superBar.setProgress((double) Float.valueOf(tankModel.readFuelLevel("Super")) / tankModel.getTank("Super").getCapacity());
		dieselBar.setProgress((double) Float.valueOf(tankModel.readFuelLevel("Diesel")) / tankModel.getTank("Diesel").getCapacity());
		
		superPriceLabel.setText(tankModel.readPrice("Super") + " €/L");
		dieselPriceLabel.setText(tankModel.readPrice("Diesel") + " €/L");
		
		superCapLabel.setText(String.valueOf(tankModel.readFuelLevel("Super") + "/" + tankModel.getTank("Super").getCapacity()) + " L");
		dieselCapLabel.setText(String.valueOf(tankModel.readFuelLevel("Diesel") + "/" + tankModel.getTank("Diesel").getCapacity()) + " L");
	}
	
	public void showPriceChangePopup (ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ChangePricePopup.fxml"));
		Scene scene = new Scene(root);
		Stage popup = new Stage();
		scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
		popup.setTitle("Preise ändern");
		popup.setScene(scene);
		popup.show();
	}

}