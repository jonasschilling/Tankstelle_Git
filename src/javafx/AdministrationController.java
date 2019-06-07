package javafx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.stage.Stage;


/**
 * @author Jonas Schilling
 *
 */
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
	Button changePriceButton, bookProductsButton, bookFuelsButton, refreshButton;
	@FXML
	ProgressBar superBar, dieselBar;
	@FXML
	Slider superSlider, dieselSlider;
	@FXML
	RadioButton wodkaOrder, filipOrder, jupiterOrder, bullOrder, pizzaOrder;

	private static char eurosign = '\u20AC';

	TankModel tankModel = TankModel.getInstance();
	SalesModel salesModel = SalesModel.getInstance();
	ReceiptModel receiptModel = ReceiptModel.getInstance();

	public void initialize(URL arg0, ResourceBundle arg1) {

		tankNameLabel1.setText(tankModel.getTank("Super").getDescription());
		tankNameLabel2.setText(tankModel.getTank("Diesel").getDescription());

		superPriceLabel.setText(tankModel.readPrice("Super") + " " + eurosign + "/L");
		dieselPriceLabel.setText(tankModel.readPrice("Diesel") + " " + eurosign + "/L");
		wodkaPriceLabel.setText(salesModel.readPrice("Wodka") + " " + eurosign);
		filipPriceLabel.setText(salesModel.readPrice("Filip") + " " + eurosign);
		jupiterPriceLabel.setText(salesModel.readPrice("Jupiter") + " " + eurosign);
		bullPriceLabel.setText(salesModel.readPrice("Bull") + " " + eurosign);
		pizzaPriceLabel.setText(salesModel.readPrice("Pizza") + " " + eurosign);

		superCapLabel.setText(String.valueOf(Math.round(Float.valueOf(tankModel.readFuelLevel("Super")) * 100) / 100f
				+ "/" + tankModel.getTank("Super").getCapacity()) + " L");
		dieselCapLabel.setText(String.valueOf(Math.round(Float.valueOf(tankModel.readFuelLevel("Diesel")) * 100) / 100f
				+ "/" + tankModel.getTank("Diesel").getCapacity()) + " L");

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

		wodkaStock.setText(
				salesModel.getProduct("Wodka").getAmount() + " / " + salesModel.getProduct("Wodka").getMaxAmount());
		filipStock.setText(
				salesModel.getProduct("Filip").getAmount() + " / " + salesModel.getProduct("Filip").getMaxAmount());
		jupiterStock.setText(
				salesModel.getProduct("Jupiter").getAmount() + " / " + salesModel.getProduct("Jupiter").getMaxAmount());
		bullStock.setText(
				salesModel.getProduct("Bull").getAmount() + " / " + salesModel.getProduct("Bull").getMaxAmount());
		pizzaStock.setText(
				salesModel.getProduct("Pizza").getAmount() + " / " + salesModel.getProduct("Pizza").getMaxAmount());

	}
	
	// Aktualisiert alle Controls in der Administrationview
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
		wodkaPriceLabel.setText(salesModel.readPrice("Wodka") + " " + eurosign);
		filipPriceLabel.setText(salesModel.readPrice("Filip") + " " + eurosign);
		jupiterPriceLabel.setText(salesModel.readPrice("Jupiter") + " " + eurosign);
		bullPriceLabel.setText(salesModel.readPrice("Bull") + " " + eurosign);
		pizzaPriceLabel.setText(salesModel.readPrice("Pizza") + " " + eurosign);

		superCapLabel.setText(String.valueOf(Math.round(Float.valueOf(tankModel.readFuelLevel("Super")) * 100) / 100f
				+ "/" + tankModel.getTank("Super").getCapacity()) + " L");
		dieselCapLabel.setText(String.valueOf(Math.round(Float.valueOf(tankModel.readFuelLevel("Diesel")) * 100) / 100f
				+ "/" + tankModel.getTank("Diesel").getCapacity()) + " L");

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

		wodkaStock.setText(
				salesModel.getProduct("Wodka").getAmount() + " / " + salesModel.getProduct("Wodka").getMaxAmount());
		filipStock.setText(
				salesModel.getProduct("Filip").getAmount() + " / " + salesModel.getProduct("Filip").getMaxAmount());
		jupiterStock.setText(
				salesModel.getProduct("Jupiter").getAmount() + " / " + salesModel.getProduct("Jupiter").getMaxAmount());
		bullStock.setText(
				salesModel.getProduct("Bull").getAmount() + " / " + salesModel.getProduct("Bull").getMaxAmount());
		pizzaStock.setText(
				salesModel.getProduct("Pizza").getAmount() + " / " + salesModel.getProduct("Pizza").getMaxAmount());
	}

	// Oeffnet Popup, um Verkaufspreise zu Aendern
	public void showPriceChangePopup(ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ChangePricePopup.fxml"));
		Scene scene = new Scene(root);
		Stage popup = new Stage();
		scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
		popup.setTitle("Preise ändern");
		popup.setScene(scene);
		popup.show();
	}

	// Oeffnet Popup um Details zu den Waren anzeigen zu lassen
	public void showProdInfoPopup(ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ProductInformationPopUp.fxml"));
		Scene scene = new Scene(root);
		Stage popup = new Stage();
		scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
		popup.setTitle("Warendetails");
		popup.setScene(scene);
		popup.show();
	}

	// Prueft, ob etwas ausgewaehlt ist, wenn ja -> bestellt die Kraftstoff- und Warenmengen
	public void makeOrder(ActionEvent actionEvent) {
		if (superOrder.getText().equals("0.0") == false || dieselOrder.getText().equals("0.0") == false
				|| wodkaOrder.isSelected() || filipOrder.isSelected() || jupiterOrder.isSelected()
				|| bullOrder.isSelected() || pizzaOrder.isSelected()) {

			if ((superOrder.getText().equals("0.0") == false) || (dieselOrder.getText().equals("0.0")) == false) {
				superSlider.setMax((double) tankModel.getTank("Super").getCapacity() - Double.valueOf(tankModel.readFuelLevel("Super")) - Double.valueOf(superOrder.getText()));
				dieselSlider.setMax((double) tankModel.getTank("Diesel").getCapacity() - Double.valueOf(tankModel.readFuelLevel("Diesel")) - Double.valueOf(superOrder.getText()));
				salesModel.writeNoOrders("Gas");
				salesModel.writeGasDeliveryNote(superOrder.getText(), dieselOrder.getText());
				salesModel.writeGasOrder(superOrder.getText(), dieselOrder.getText());

			}

			ArrayList<Product> productsOrder = new ArrayList<>();
			if (wodkaOrder.isSelected() || filipOrder.isSelected() || jupiterOrder.isSelected()
					|| bullOrder.isSelected() || pizzaOrder.isSelected()) {

				salesModel.writeNoOrders("Products");

				if (wodkaOrder.isSelected()) {
					productsOrder.add(salesModel.getProduct("Wodka"));
				}
				if (filipOrder.isSelected()) {
					productsOrder.add(salesModel.getProduct("Filip"));
				}
				if (jupiterOrder.isSelected()) {
					productsOrder.add(salesModel.getProduct("Jupiter"));
				}
				if (bullOrder.isSelected()) {
					productsOrder.add(salesModel.getProduct("Bull"));
				}
				if (pizzaOrder.isSelected()) {
					productsOrder.add(salesModel.getProduct("Pizza"));
				}
				salesModel.writeProductDeliveryNote(productsOrder);
				salesModel.writeProductOrder(productsOrder);

			}

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Bestellung erfolgreich abgegeben.");
			alert.show();

			superSlider.setValue(0.0);
			dieselSlider.setValue(0.0);
			wodkaOrder.setSelected(false);
			filipOrder.setSelected(false);
			jupiterOrder.setSelected(false);
			bullOrder.setSelected(false);
			pizzaOrder.setSelected(false);

		}
	}

	// Liest Produkt-Lieferscheine ein und erhaelt Bestaende
	public void bookProductOrder() {
		File file = new File("src/javafx/resources/Deliveries/ProductDeliveryNote"
				+ salesModel.readNoDeliveryNote("Products") + ".txt");
		FileReader fr = null;
		BufferedReader br = null;

		if (file.exists()) {
			salesModel.writeNoDeliveryNote("Products");
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String line;
				br.readLine();
				br.readLine();
				while ((line = br.readLine()) != null) {

					String[] output = line.split(";");
					salesModel.decBalance(Integer.valueOf(output[3]) * Float.valueOf(output[4]));
					if (Integer.parseInt(output[0]) == salesModel.getProduct("Wodka").getProdNumber()) {
						int newAmount = salesModel.getProduct("Wodka").getAmount() + Integer.valueOf(output[3]);
						salesModel.getProduct("Wodka").setAmount(newAmount);
					}

					if (Integer.parseInt(output[0]) == salesModel.getProduct("Filip").getProdNumber()) {
						int newAmount = salesModel.getProduct("Filip").getAmount() + Integer.valueOf(output[3]);
						salesModel.getProduct("Filip").setAmount(newAmount);
					}

					if (Integer.parseInt(output[0]) == salesModel.getProduct("Jupiter").getProdNumber()) {
						int newAmount = salesModel.getProduct("Jupiter").getAmount() + Integer.valueOf(output[3]);
						salesModel.getProduct("Jupiter").setAmount(newAmount);
					}

					if (Integer.parseInt(output[0]) == salesModel.getProduct("Bull").getProdNumber()) {
						int newAmount = salesModel.getProduct("Bull").getAmount() + Integer.valueOf(output[3]);
						salesModel.getProduct("Bull").setAmount(newAmount);
					}

					if (Integer.parseInt(output[0]) == salesModel.getProduct("Pizza").getProdNumber()) {
						int newAmount = salesModel.getProduct("Pizza").getAmount() + Integer.valueOf(output[3]);
						salesModel.getProduct("Pizza").setAmount(newAmount);
					}

				}
				salesModel.getFinancesController().setBalanceLabel();
				refresh();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Die Waren wurden erfolgreich eingebucht.");
				alert.show();
				String p = file.getPath();
				Receipt R1 = new Receipt(printSimpleDateFormatWithTime(), "Einkaufsbeleg", p);
				receiptModel.incReceipts();
				FinancesController.getReceipts().add(R1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Kein aktueller Lieferschein für Waren vorhanden.");
			alert.show();
		}

	}

	// Liest Kraftstoff-Lieferscheine ein und erhaelt Bestaende
	public void bookGasOrder() {
		float dieselFuelAdd = 0;
		float superFuelAdd = 0;
		File file = new File(
				"src/javafx/resources/Deliveries/GasDeliveryNote" + salesModel.readNoDeliveryNote("Gas") + ".txt");
		FileReader fr = null;
		BufferedReader br = null;

		if (file.exists()) {
			salesModel.writeNoDeliveryNote("Gas");
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String line;
				br.readLine();

				while ((line = br.readLine()) != null) {
					String[] output = line.split("=");
					if (output[0].equals(tankModel.getTank("Super").getDescription().toUpperCase())) {
						float superFuelLevelOld = Float.valueOf(tankModel.readFuelLevel("Super"));
						superFuelAdd = Float.valueOf(output[1]);
						float newSuperLevel = superFuelLevelOld + superFuelAdd;
						tankModel.writeFuelLevel("Super", String.valueOf(newSuperLevel));
					}
					if (output[0].equals(tankModel.getTank("Diesel").getDescription().toUpperCase())) {
						float dieselFuelLevelOld = Float.valueOf(tankModel.readFuelLevel("Diesel"));
						dieselFuelAdd = Float.valueOf(output[1]);
						float newDieselLevel = dieselFuelLevelOld + dieselFuelAdd;
						tankModel.writeFuelLevel("Diesel", String.valueOf(newDieselLevel));
					}
					if (output[0].equals("DIESEL_PREIS")) {
						salesModel.setDieselPriceBuy(Float.valueOf(output[1]));

					}
					if (output[0].equals("SUPER_PREIS")) {
						salesModel.setSuperPriceBuy(Float.valueOf(output[1]));

					}

				}
				salesModel.decBalance(dieselFuelAdd * salesModel.getDieselPriceBuy());
				salesModel.decBalance(superFuelAdd * salesModel.getSuperPriceBuy());
				salesModel.getFinancesController().setBalanceLabel();
				String p = file.getPath();
				Receipt R1 = new Receipt(printSimpleDateFormatWithTime(), "Einkaufsbeleg", p);
				receiptModel.incReceipts();
				FinancesController.getReceipts().add(R1);
				refresh();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Der Kraftstoff wurde erfolgreich eingebucht.");
				alert.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Kein aktueller Lieferschein für Kraftstoff vorhanden.");
			alert.show();
		}

	}

	// Gibt aktuelles Datum im Format dd.MM.yyyy kk:mm zurueck
	public String printSimpleDateFormatWithTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy kk:mm");
		Date currentTime = new Date();
		return (formatter.format(currentTime));
	}
	
	
	
}