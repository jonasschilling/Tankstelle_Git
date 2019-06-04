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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import refillSimulation.SimulationModel;

public class AdministrationController implements Initializable {

	@FXML
	Label tankNameLabel1, tankNameLabel2, superOrder, dieselOrder;
	@FXML
	Label superPriceLabel, dieselPriceLabel;
	@FXML
	Label wodkaPriceLabel, filipPriceLabel, jupiterPriceLabel, bullPriceLabel, pizzaPriceLabel;
	@FXML
	Label superLevelLabel, dieselLevelLabel;
	@FXML
	Label superCapLabel, dieselCapLabel;
	@FXML
	Label wodkaStock, filipStock, jupiterStock, bullStock, pizzaStock;
	@FXML
	Button changePriceButton;
	@FXML
	ProgressBar superBar, dieselBar;
	@FXML
	Slider superSlider, dieselSlider;
	@FXML
	RadioButton wodkaOrder, filipOrder, jupiterOrder, bullOrder, pizzaOrder;

	TankModel tankModel = TankModel.getInstance();
	SalesModel salesModel = SalesModel.getInstance();

	public void initialize(URL arg0, ResourceBundle arg1) {

		tankNameLabel1.setText(tankModel.getTank("Super").getDescription());
		tankNameLabel2.setText(tankModel.getTank("Diesel").getDescription());

		superPriceLabel.setText(tankModel.readPrice("Super") + " €/L");
		dieselPriceLabel.setText(tankModel.readPrice("Diesel") + " €/L");
		wodkaPriceLabel.setText(salesModel.readPrice("Wodka") + " €");
		filipPriceLabel.setText(salesModel.readPrice("Filip") + " €");
		jupiterPriceLabel.setText(salesModel.readPrice("Jupiter") + " €");
		bullPriceLabel.setText(salesModel.readPrice("Bull") + " €");
		pizzaPriceLabel.setText(salesModel.readPrice("Pizza") + " €");

		superCapLabel.setText(
				String.valueOf(tankModel.readFuelLevel("Super") + "/" + tankModel.getTank("Super").getCapacity())
						+ " L");
		dieselCapLabel.setText(
				String.valueOf(tankModel.readFuelLevel("Diesel") + "/" + tankModel.getTank("Diesel").getCapacity())
						+ " L");

		superBar.setProgress(
				(double) (Float.valueOf(tankModel.readFuelLevel("Super")) / tankModel.getTank("Super").getCapacity()));
		if ((Float.valueOf(tankModel.readFuelLevel("Super")) / tankModel.getTank("Super").getCapacity()) > 0.5) {
			superBar.setStyle("-fx-accent: #1ae204");
		} else if ((Float.valueOf(tankModel.readFuelLevel("Super"))
				/ tankModel.getTank("Super").getCapacity()) > 0.25) {
			superBar.setStyle("-fx-accent: yellow");
		} else if ((Float.valueOf(tankModel.readFuelLevel("Super"))
				/ tankModel.getTank("Super").getCapacity()) <= 0.25) {
			superBar.setStyle("-fx-accent: red");
		}

		dieselBar.setProgress((double) (Float.valueOf(tankModel.readFuelLevel("Diesel"))
				/ tankModel.getTank("Diesel").getCapacity()));
		if ((Float.valueOf(tankModel.readFuelLevel("Diesel")) / tankModel.getTank("Diesel").getCapacity()) > 0.5) {
			dieselBar.setStyle("-fx-accent: #1ae204");
		} else if ((Float.valueOf(tankModel.readFuelLevel("Diesel"))
				/ tankModel.getTank("Diesel").getCapacity()) > 0.25) {
			dieselBar.setStyle("-fx-accent: yellow");
		} else if ((Float.valueOf(tankModel.readFuelLevel("Diesel"))
				/ tankModel.getTank("Diesel").getCapacity()) <= 0.25) {
			dieselBar.setStyle("-fx-accent: red");
		}

		superSlider.setMax(
				(double) tankModel.getTank("Super").getCapacity() - Double.valueOf(tankModel.readFuelLevel("Super")));
		superSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			superOrder.setText(String.valueOf(superSlider.getValue()));
		});
		dieselSlider.setMax(
				(double) tankModel.getTank("Diesel").getCapacity() - Double.valueOf(tankModel.readFuelLevel("Diesel")));
		dieselSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			dieselOrder.setText(String.valueOf(dieselSlider.getValue()));
		});

		wodkaOrder.setText(salesModel.getProduct("Wodka").getName());
		filipOrder.setText(salesModel.getProduct("Filip").getName());
		jupiterOrder.setText(salesModel.getProduct("Jupiter").getName());
		bullOrder.setText(salesModel.getProduct("Bull").getName());
		pizzaOrder.setText(salesModel.getProduct("Pizza").getName());
		
		wodkaStock.setText(salesModel.getProduct("Wodka").getAmount() + " / " + salesModel.getProduct("Wodka").getMaxAmount());
		filipStock.setText(salesModel.getProduct("Filip").getAmount() + " / " + salesModel.getProduct("Filip").getMaxAmount());
		jupiterStock.setText(salesModel.getProduct("Jupiter").getAmount() + " / " + salesModel.getProduct("Jupiter").getMaxAmount());
		bullStock.setText(salesModel.getProduct("Bull").getAmount() + " / " + salesModel.getProduct("Bull").getMaxAmount());
		pizzaStock.setText(salesModel.getProduct("Pizza").getAmount() + " / " + salesModel.getProduct("Pizza").getMaxAmount());

	}

	public void refresh() {
		superBar.setProgress(
				(double) Float.valueOf(tankModel.readFuelLevel("Super")) / tankModel.getTank("Super").getCapacity());
		if ((Float.valueOf(tankModel.readFuelLevel("Super")) / tankModel.getTank("Super").getCapacity()) > 0.5) {
			superBar.setStyle("-fx-accent: #1ae204");
		} else if ((Float.valueOf(tankModel.readFuelLevel("Super"))
				/ tankModel.getTank("Super").getCapacity()) > 0.25) {
			superBar.setStyle("-fx-accent: yellow");
		} else if ((Float.valueOf(tankModel.readFuelLevel("Super"))
				/ tankModel.getTank("Super").getCapacity()) <= 0.25) {
			superBar.setStyle("-fx-accent: red");
		}
		dieselBar.setProgress((double) (Float.valueOf(tankModel.readFuelLevel("Diesel"))
				/ tankModel.getTank("Diesel").getCapacity()));
		if ((Float.valueOf(tankModel.readFuelLevel("Diesel")) / tankModel.getTank("Diesel").getCapacity()) > 0.5) {
			dieselBar.setStyle("-fx-accent: #1ae204");
		} else if ((Float.valueOf(tankModel.readFuelLevel("Diesel"))
				/ tankModel.getTank("Diesel").getCapacity()) > 0.25) {
			dieselBar.setStyle("-fx-accent: yellow");
		} else if ((Float.valueOf(tankModel.readFuelLevel("Diesel"))
				/ tankModel.getTank("Diesel").getCapacity()) <= 0.25) {
			dieselBar.setStyle("-fx-accent: red");
		}

		superPriceLabel.setText(tankModel.readPrice("Super") + " €/L");
		dieselPriceLabel.setText(tankModel.readPrice("Diesel") + " €/L");
		wodkaPriceLabel.setText(salesModel.readPrice("Wodka") + " €");
		filipPriceLabel.setText(salesModel.readPrice("Filip") + " €");
		jupiterPriceLabel.setText(salesModel.readPrice("Jupiter") + " €");
		bullPriceLabel.setText(salesModel.readPrice("Bull") + " €");
		pizzaPriceLabel.setText(salesModel.readPrice("Pizza") + " €");

		superCapLabel.setText(
				String.valueOf(tankModel.readFuelLevel("Super") + "/" + tankModel.getTank("Super").getCapacity())
						+ " L");
		dieselCapLabel.setText(
				String.valueOf(tankModel.readFuelLevel("Diesel") + "/" + tankModel.getTank("Diesel").getCapacity())
						+ " L");

		superSlider.setMax(
				(double) tankModel.getTank("Super").getCapacity() - Double.valueOf(tankModel.readFuelLevel("Super")));
		dieselSlider.setMax(
				(double) tankModel.getTank("Diesel").getCapacity() - Double.valueOf(tankModel.readFuelLevel("Diesel")));
		superSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			superOrder.setText(String.valueOf(superSlider.getValue()));
		});
		dieselSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			dieselOrder.setText(String.valueOf(dieselSlider.getValue()));
		});
		
		wodkaStock.setText(salesModel.getProduct("Wodka").getAmount() + " / " + salesModel.getProduct("Wodka").getMaxAmount());
		filipStock.setText(salesModel.getProduct("Filip").getAmount() + " / " + salesModel.getProduct("Filip").getMaxAmount());
		jupiterStock.setText(salesModel.getProduct("Jupiter").getAmount() + " / " + salesModel.getProduct("Jupiter").getMaxAmount());
		bullStock.setText(salesModel.getProduct("Bull").getAmount() + " / " + salesModel.getProduct("Bull").getMaxAmount());
		pizzaStock.setText(salesModel.getProduct("Pizza").getAmount() + " / " + salesModel.getProduct("Pizza").getMaxAmount());
	}

	public void showPriceChangePopup(ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ChangePricePopup.fxml"));
		Scene scene = new Scene(root);
		Stage popup = new Stage();
		scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
		popup.setTitle("Preise ändern");
		popup.setScene(scene);
		popup.show();
	}

}