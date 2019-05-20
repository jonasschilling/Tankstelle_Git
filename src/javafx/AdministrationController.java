package javafx;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;

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
	
	
	Model model = Model.getInstance();

	public void initialize(URL arg0, ResourceBundle arg1) {
		
//		superLevelLabel.textProperty().bind(model.getDescription());
//		label.textProperty().bind(model.getText());

		tankNameLabel1.setText(model.getTank("Super").getDescription());
		tankNameLabel2.setText(model.getTank("Diesel").getDescription());

		superPriceLabel.setText(model.readPrice("Super") + " €/L");
		dieselPriceLabel.setText(model.readPrice("Diesel") + " €/L");

		superCapLabel.setText(String.valueOf(model.readFuelLevel("Super") + "/" + model.getTank("Super").getCapacity()) + " L");
		dieselCapLabel.setText(String.valueOf(model.readFuelLevel("Diesel") + "/" + model.getTank("Diesel").getCapacity()) + " L");
		
		superBar.setProgress((double)(Float.valueOf(model.readFuelLevel("Super")) / model.getTank("Super").getCapacity()));
		dieselBar.setProgress((double)(Float.valueOf(model.readFuelLevel("Diesel")) / model.getTank("Diesel").getCapacity()));
			
	}
	
	public void showChangePriceDialog(ActionEvent actionEvent) {
		TextInputDialog newSuperPrice = new TextInputDialog();
		newSuperPrice.setTitle("Preise ändern");
		newSuperPrice.setHeaderText("Preise ändern");
		newSuperPrice.setContentText("Neuer Preis für Super eingeben: ");
		Optional<String> input = newSuperPrice.showAndWait();
		input.ifPresent(text -> model.writeSuperPrice(text));
		superPriceLabel.setText(model.readPrice("Super") + " €/L");
//		simModel.readNewPricePerLitre();
//		simModel.setSuperPrice(Float.parseFloat(text))
	}

	public void refresh() {
		superBar.setProgress((double) Float.valueOf(model.readFuelLevel("Super")) / model.getTank("Super").getCapacity());
		dieselBar.setProgress((double) Float.valueOf(model.readFuelLevel("Diesel")) / model.getTank("Diesel").getCapacity());
		
		superCapLabel.setText(String.valueOf(model.readFuelLevel("Super") + "/" + model.getTank("Super").getCapacity()) + " L");
		dieselCapLabel.setText(String.valueOf(model.readFuelLevel("Diesel") + "/" + model.getTank("Diesel").getCapacity()) + " L");
	}

}