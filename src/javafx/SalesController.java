package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import refillSimulation.SimulationModel;

/**
 * @author Thore Bedey, Patrick Berlet
 *
 */
public class SalesController implements Initializable {

	public static char eurosign = '\u20AC';
	
	Shoppingcart scart;
	@FXML
	ToggleButton pumpButton1, pumpButton2, pumpButton3, pumpButton4, pumpButton5, button6, button7, button8, button9,
			button10;
	@FXML
	Button prod1plus, prod1minus;
	@FXML
	ComboBox<String> comboBox;
	@FXML
	Button prod2plus, prod2minus;
	@FXML
	Button prod3plus, prod3minus;
	@FXML
	Button prod4plus, prod4minus;
	@FXML
	Button prod5plus, prod5minus;
	@FXML
	Label label1, label1amount, label2, label2amount, label3, label3amount, label4, label4amount, label5, label5amount,
			labelTotal, label6;
	@FXML
	Label pumpNumber, gasKindLabel, pricePerLitreLabel, amountRefilledLabel, priceCompleteLabel;
	@FXML
	Pane pumpPane;
	@FXML
	AnchorPane anchor1, anchor2, anchor3, anchor4, anchor5;
	private static String currentEmployee;
	private static ObservableList<String> employeesNames = FXCollections.observableArrayList();

	TankModel tankModel = TankModel.getInstance();
	ReceiptModel receiptModel = ReceiptModel.getInstance();
	SalesModel salesModel = SalesModel.getInstance();
	SimulationModel simulationModel = SimulationModel.getInstance();

	public void initialize(URL arg0, ResourceBundle arg1) {
		scart = new Shoppingcart();
		hideAll();
		if (salesModel.products.size() < 1) {
			salesModel.addProducts();
		}
		comboBox.getItems().setAll(employeesNames);
		comboBox.setPromptText("Mitarbeiter auswählen");
		disableButtonEmpty();
		ToggleGroup toggleGroup = new ToggleGroup();
		toggleGroup.getToggles().add(pumpButton1);
		toggleGroup.getToggles().add(pumpButton2);
		toggleGroup.getToggles().add(pumpButton3);
		toggleGroup.getToggles().add(pumpButton4);
		toggleGroup.getToggles().add(pumpButton5);

		pumpNumber.setText("---");
		gasKindLabel.setText("---");
		pricePerLitreLabel.setText("---");
		amountRefilledLabel.setText("---");

		pumpPane.setVisible(false);

	}

	// Disabled einen Button falls der jeweilige Bestand leer ist.
	public void disableButtonEmpty() {
		if (salesModel.getProduct("Wodka").getAmount() <= 0) {
			button6.setDisable(true);
		}
		if (salesModel.getProduct("Filip").getAmount() <= 0) {
			button7.setDisable(true);
		}
		if (salesModel.getProduct("Jupiter").getAmount() <= 0) {
			button8.setDisable(true);
		}
		if (salesModel.getProduct("Bull").getAmount() <= 0) {
			button9.setDisable(true);
		}
		if (salesModel.getProduct("Pizza").getAmount() <= 0) {
			button10.setDisable(true);
		}
	}

//Alle (+) und (-) Knoepfe werden versteckt
	public void hideAll() {
		pumpPane.setVisible(false);
		anchor1.setVisible(false);
		anchor2.setVisible(false);
		anchor3.setVisible(false);
		anchor4.setVisible(false);
		anchor5.setVisible(false);
		labelTotal.setText("Gesamtpreis: 0.00  €");
	}

//Falls Knoepfe gedrueckt sind, werden sie nun "losgelassen"
	public void unToggleAll() {
		pumpButton1.setSelected(false);
		pumpButton2.setSelected(false);
		pumpButton3.setSelected(false);
		pumpButton4.setSelected(false);
		pumpButton5.setSelected(false);
		button6.setSelected(false);
		button7.setSelected(false);
		button8.setSelected(false);
		button9.setSelected(false);
		button10.setSelected(false);
	}

//Entfernt alle Texte aus der Warenkorbanzeige
	public void clearLabels() {
		label1.setText("");
		label2.setText("");
		label3.setText("");
		label4.setText("");
		label5.setText("");
		label6.setText("");

	}

	public void currentEmployee(ActionEvent actionEvent) {
		currentEmployee = comboBox.getValue();
	}

	public static String getCurrentEmployee() {
		return currentEmployee;
	}

//Ein Produkt wird ausgewaehlt
	public void productButtonClicked(ActionEvent actionEvent) {

		ToggleButton source = (ToggleButton) actionEvent.getSource();

		switch (source.getId()) {

		case "button6":

			if (source.isSelected()) {
				wodkaPressed();

			} else {
				wodkaReleased();
			}
			break;
		case "button7":
			if (source.isSelected()) {
				filipPressed();

			} else {
				filipReleased();
			}
			break;
		case "button8":
			if (source.isSelected()) {
				jupiterPressed();
			} else {
				jupiterReleased();
			}
			break;
		case "button9":
			if (source.isSelected()) {
				bullPressed();
			} else {
				bullReleased();
			}
			break;
		case "button10":
			if (source.isSelected()) {
				pizzaPressed();
			} else {
				pizzaReleased();
			}
			break;
		}
		labelTotal.setText("Gesamtpreis: " + scart.getTotal() + "  €");
	}

//der (+) Button eines Produkts wird gedrueckt
	public void addButtonClicked(ActionEvent actionEvent) {

		Button source = (Button) actionEvent.getSource();
		Label currentlabel;
		float price;
		switch (source.getId()) {

		case ("prod1plus"):

			int amount1 = Integer.valueOf(label1amount.getText());
			amount1++;
			label1amount.setText(String.valueOf(amount1));
			scart.addWodka();
			prod1plus.setDisable(scart.getNumWodka() == (scart.wodka.getAmount()) ? true : false);
			currentlabel = getCurrentLabel("Wodka");
			price = (float) Math.round(scart.getNumWodka() * Float.valueOf(salesModel.readPrice("Wodka")) * 100) / 100;
			currentlabel.setText("Wodka                 " + price + " " + eurosign);
			break;
		case ("prod2plus"):
			int amount2 = Integer.valueOf(label2amount.getText());
			amount2++;
			label2amount.setText(String.valueOf(amount2));
			scart.addFilip();
			prod2plus.setDisable(scart.getNumFilip() == (scart.filip.getAmount()) ? true : false);
			currentlabel = getCurrentLabel("Filip");
			price = (float) Math.round(scart.getNumFilip() * Float.valueOf(salesModel.readPrice("Filip")) * 100) / 100;
			currentlabel.setText("Filip Maurice        " + price + " " + eurosign);
			break;
		case ("prod3plus"):
			int amount3 = Integer.valueOf(label3amount.getText());
			amount3++;
			label3amount.setText(String.valueOf(amount3));
			scart.addJupiter();
			prod3plus.setDisable(scart.getNumJupiter() == (scart.jupiter.getAmount()) ? true : false);
			currentlabel = getCurrentLabel("Jupiter");
			price = (float) Math.round(scart.getNumJupiter() * Float.valueOf(salesModel.readPrice("Jupiter")) * 100)
					/ 100;
			currentlabel.setText("Jupiter Riegel         " + price + " " + eurosign);
			break;
		case ("prod4plus"):
			int amount4 = Integer.valueOf(label4amount.getText());
			amount4++;
			label4amount.setText(String.valueOf(amount4));
			scart.addBull();
			prod4plus.setDisable(scart.getNumBull() == (scart.bull.getAmount()) ? true : false);
			currentlabel = getCurrentLabel("Bull");
			price = (float) Math.round(scart.getNumBull() * Float.valueOf(salesModel.readPrice("Bull")) * 100) / 100;
			currentlabel.setText("Sitting Bull           " + price + " " + eurosign);
			break;
		case ("prod5plus"):
			int amount5 = Integer.valueOf(label5amount.getText());
			amount5++;
			label5amount.setText(String.valueOf(amount5));
			scart.addPizza();
			prod5plus.setDisable(scart.getNumPizza() == (scart.pizza.getAmount()) ? true : false);
			currentlabel = getCurrentLabel("Pizza");
			price = (float) Math.round(scart.getNumPizza() * Float.valueOf(salesModel.readPrice("Pizza")) * 100) / 100;
			currentlabel.setText("Pizza                    " + price + " " + eurosign);
			break;
		}
		labelTotal.setText("Gesamtpreis: " + scart.getTotal() + "  €");
	}

//Der (-) Button eines Produkts wird gedrueckt
	public void removeButtonClicked(ActionEvent actionEvent) {
		Button source = (Button) actionEvent.getSource();
		Label currentlabel;
		float price;
		switch (source.getId()) {

		case ("prod1minus"):

			int amount1 = Integer.valueOf(label1amount.getText());
			if (amount1 > 1) {
				amount1--;
				label1amount.setText(String.valueOf(amount1));
				scart.removeWodka();
				prod1plus.setDisable(scart.getNumWodka() < (scart.wodka.getAmount()) ? false : true);
			}

			currentlabel = getCurrentLabel("Wodka");
			price = (float) Math.round(scart.getNumWodka() * Float.valueOf(salesModel.readPrice("Wodka")) * 100) / 100;
			currentlabel.setText("Wodka                 " + price + " " + eurosign);
			break;
		case ("prod2minus"):
			int amount2 = Integer.valueOf(label2amount.getText());
			if (amount2 > 1) {
				amount2--;
				label2amount.setText(String.valueOf(amount2));
				scart.removeFilip();
				prod2plus.setDisable(scart.getNumFilip() < (scart.filip.getAmount()) ? false : true);
			}
			currentlabel = getCurrentLabel("Filip");
			price = (float) Math.round(scart.getNumFilip() * Float.valueOf(salesModel.readPrice("Filip")) * 100) / 100;
			currentlabel.setText("Filip Maurice        " + price + " " + eurosign);
			break;
		case ("prod3minus"):
			int amount3 = Integer.valueOf(label3amount.getText());
			if (amount3 > 1) {
				amount3--;
				label3amount.setText(String.valueOf(amount3));
				scart.removeJupiter();
				prod3plus.setDisable(scart.getNumJupiter() < (scart.jupiter.getAmount()) ? false : true);
			}
			currentlabel = getCurrentLabel("Jupiter");
			price = (float) Math.round(scart.getNumJupiter() * Float.valueOf(salesModel.readPrice("Jupiter")) * 100)
					/ 100;
			currentlabel.setText("Jupiter Riegel         " + price + " " + eurosign);
			break;
		case ("prod4minus"):
			int amount4 = Integer.valueOf(label4amount.getText());
			if (amount4 > 1) {
				amount4--;
				label4amount.setText(String.valueOf(amount4));
				scart.removeBull();
				prod4plus.setDisable(scart.getNumBull() < (scart.bull.getAmount()) ? false : true);
			}
			currentlabel = getCurrentLabel("Bull");
			price = (float) Math.round(scart.getNumBull() * Float.valueOf(salesModel.readPrice("Bull")) * 100) / 100;
			currentlabel.setText("Sitting Bull           " + price + " " + eurosign);
			break;
		case ("prod5minus"):
			int amount5 = Integer.valueOf(label5amount.getText());
			if (amount5 > 1) {
				amount5--;
				label5amount.setText(String.valueOf(amount5));
				scart.removePizza();
				prod5plus.setDisable(scart.getNumPizza() < (scart.pizza.getAmount()) ? false : true);

			}
			currentlabel = getCurrentLabel("Pizza");
			price = (float) Math.round(scart.getNumPizza() * Float.valueOf(salesModel.readPrice("Pizza")) * 100) / 100;
			currentlabel.setText("Pizza                    " + price + " " + eurosign);
			break;
		}
		labelTotal.setText("Gesamtpreis: " + scart.getTotal() + "  €");
	}

//Checkout Button wird gedrueckt
	public void checkout(ActionEvent actionEvent) {
		receiptModel.getAmount(scart.getNumWodka(), scart.getNumFilip(), scart.getNumJupiter(), scart.getNumBull(),
				scart.getNumPizza(), scart.getTotal());
		salesModel.incBalance(scart.getTotal());
		salesModel.getFinancesController().setBalanceLabel();
		receiptModel.writeReceipt();
		scart.checkout();
		if(pumpButton1.isSelected() == true) {
			simulationModel.writePumpData("1", "---", 0.0f, 0.0f);
		} else if(pumpButton2.isSelected() == true) {
			simulationModel.writePumpData("2", "---", 0.0f, 0.0f);
		} else if(pumpButton3.isSelected() == true) {
			simulationModel.writePumpData("3", "---", 0.0f, 0.0f);
		} else if(pumpButton4.isSelected() == true) {
			simulationModel.writePumpData("4", "---", 0.0f, 0.0f);
		} else if(pumpButton5.isSelected() == true) {
			simulationModel.writePumpData("5", "---", 0.0f, 0.0f);
		}
		hideAll();
		clearLabels();
		unToggleAll();
		button6.setDisable((scart.wodka.getAmount() == 0) ? true : false);
		button7.setDisable((scart.filip.getAmount() == 0) ? true : false);
		button8.setDisable((scart.jupiter.getAmount() == 0) ? true : false);
		button9.setDisable((scart.bull.getAmount() == 0) ? true : false);
		button10.setDisable((scart.pizza.getAmount() == 0) ? true : false);
		
		
	}

//Cancelbutton wird gedrueckt
	public void cancel(ActionEvent actionEvent) {

		scart.cancel();
		hideAll();
		clearLabels();
		unToggleAll();
	}

// die Pressed und Released-Methoden fuehren Operationen aus, die bei Auswaehlen
// bzw. Abwaehlen eines Produktes wichtig sind.
	public void wodkaPressed() {
		scart.addWodka();
		anchor1.setVisible(true);
		label1amount.setText("1");
		prod1plus.setDisable(false);
		Label freelabel = freeLabel();
		float price = (float) Math.round(scart.getNumWodka() * Float.valueOf(salesModel.readPrice("Wodka")) * 100)
				/ 100;
		freelabel.setText("Wodka                 " + price + " " + eurosign);
	}

	public void wodkaReleased() {
		int amount1 = Integer.valueOf(label1amount.getText());
		for (int i = 0; i < amount1; i++) {
			scart.removeWodka();
		}
		label1amount.setText("0");
		anchor1.setVisible(false);
		removeWodka();
	}

	public void filipPressed() {
		scart.addFilip();
		anchor2.setVisible(true);
		label2amount.setText("1");
		prod2plus.setDisable(false);
		Label freelabel = freeLabel();
		float price = (float) Math.round(scart.getNumFilip() * Float.valueOf(salesModel.readPrice("Filip")) * 100)
				/ 100;
		freelabel.setText("Filip Maurice        " + price + eurosign);

	}

	public void filipReleased() {
		int amount2 = Integer.valueOf(label2amount.getText());
		for (int i = 0; i < amount2; i++) {
			scart.removeFilip();
		}
		anchor2.setVisible(false);
		label2amount.setText("0");
		removeFilip();

	}

	public void jupiterPressed() {
		scart.addJupiter();
		anchor3.setVisible(true);
		label3amount.setText("1");
		prod3plus.setDisable(false);
		Label freelabel = freeLabel();
		float price = (float) Math.round(scart.getNumJupiter() * Float.valueOf(salesModel.readPrice("Jupiter")) * 100)
				/ 100;
		freelabel.setText("Jupiter Riegel         " + price + " " + eurosign);
	}

	public void jupiterReleased() {
		int amount3 = Integer.valueOf(label3amount.getText());
		for (int i = 0; i < amount3; i++) {
			scart.removeJupiter();
		}
		anchor3.setVisible(false);
		label3amount.setText("0");
		removeJupiter();
	}

	public void bullPressed() {
		scart.addBull();
		anchor4.setVisible(true);
		label4amount.setText("1");
		prod4plus.setDisable(false);
		Label freelabel = freeLabel();
		float price = (float) Math.round(scart.getNumBull() * Float.valueOf(salesModel.readPrice("Bull")) * 100) / 100;
		freelabel.setText("Sitting Bull           " + price + " " + eurosign);
	}

	public void bullReleased() {
		int amount4 = Integer.valueOf(label4amount.getText());
		for (int i = 0; i < amount4; i++) {
			scart.removeBull();
		}
		anchor4.setVisible(false);
		label4amount.setText("0");
		removeBull();
	}

	public void pizzaPressed() {
		scart.addPizza();
		anchor5.setVisible(true);
		label5amount.setText("1");
		prod5plus.setDisable(false);
		Label freelabel = freeLabel();
		float price = (float) Math.round(scart.getNumPizza() * Float.valueOf(salesModel.readPrice("Pizza")) * 100)
				/ 100;
		freelabel.setText("Pizza                    " + price + " " + eurosign);

	}

	public void pizzaReleased() {
		int amount5 = Integer.valueOf(label5amount.getText());
		for (int i = 0; i < amount5; i++) {
			scart.removePizza();
		}
		anchor5.setVisible(false);
		label5amount.setText("0");
		removePizza();
	}

// Die Remove-methoden lassen die Schriftzug des Produkts verschwinden und die
// anderen Schriftzuege weiter hochruecken in der Anzeige
	public void removeWodka() {
		if (label1.getText().contains("Wodka")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label2.getText().contains("Wodka")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label3.getText().contains("Wodka")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label4.getText().contains("Wodka")) {
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label5.getText().contains("Wodka")) {
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label6.getText().contains("Wodka")) {
			label6.setText("");
		}
	}

	public void removeFilip() {
		if (label1.getText().contains("Filip")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label2.getText().contains("Filip")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label3.getText().contains("Filip")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label4.getText().contains("Filip")) {
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label5.getText().contains("Filip")) {
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label6.getText().contains("Filip")) {
			label6.setText("");
		}

	}

	public void removeJupiter() {
		if (label1.getText().contains("Jupiter")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label2.getText().contains("Jupiter")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label3.getText().contains("Jupiter")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label4.getText().contains("Jupiter")) {
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label5.getText().contains("Jupiter")) {
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label6.getText().contains("Jupiter")) {
			label6.setText("");
		}
	}

	public void removeBull() {
		if (label1.getText().contains("Bull")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label2.getText().contains("Bull")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label3.getText().contains("Bull")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label4.getText().contains("Bull")) {
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label5.getText().contains("Bull")) {
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label6.getText().contains("Bull")) {
			label6.setText("");
		}
	}

	public void removePizza() {
		if (label1.getText().contains("Pizza")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label2.getText().contains("Pizza")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label3.getText().contains("Pizza")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label4.getText().contains("Pizza")) {
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label5.getText().contains("Pizza")) {
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label6.getText().contains("Pizza")) {
			label6.setText("");
		}
	}

	public void removeGas() {
		if (label1.getText().contains("Zapf")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label2.getText().contains("Zapf")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label3.getText().contains("Zapf")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label4.getText().contains("Zapf")) {
			label4.setText(label5.getText());
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label5.getText().contains("Zapf")) {
			label5.setText(label6.getText());
			label6.setText("");
		} else if (label6.getText().contains("Zapf")) {
			label6.setText("");
		}
	}

//Ueberprueft, welches Label in der Warenkorbanzeige frei ist
	public Label freeLabel() {
		if (label1.getText().equals("")) {
			return label1;
		} else if (label2.getText().equals("")) {
			return label2;
		} else if (label3.getText().equals("")) {
			return label3;
		} else if (label4.getText().equals("")) {
			return label4;
		} else if (label5.getText().equals("")) {
			return label5;
		} else if (label6.getText().equals("")) {
			return label6;
		} else {
			return null;
		}
	}

//Ueberprueft, in welchem Label der Uebergebene String angezeigt wird
	public Label getCurrentLabel(String s) {
		if (label1.getText().contains(s)) {
			return label1;
		} else if (label2.getText().contains(s)) {
			return label2;
		} else if (label3.getText().contains(s)) {
			return label3;
		} else if (label4.getText().contains(s)) {
			return label4;
		} else if (label5.getText().contains(s)) {
			return label5;
		} else if (label6.getText().contains(s)) {
			return label6;
		} else {
			return null;
		}
	}

	private float currentprice = 0;

	public void getPumpData(ActionEvent actionEvent) throws IOException {
		ToggleButton t = (ToggleButton) actionEvent.getSource();

		if (t.isSelected() == true) {
			if (actionEvent.getSource() == pumpButton1) {
				simulationModel.readPumpData(1);
				pumpNumber.setText("1");
			} else if (actionEvent.getSource() == pumpButton2) {
				simulationModel.readPumpData(2);
				pumpNumber.setText("2");
			} else if (actionEvent.getSource() == pumpButton3) {
				simulationModel.readPumpData(3);
				pumpNumber.setText("3");
			} else if (actionEvent.getSource() == pumpButton4) {
				simulationModel.readPumpData(4);
				pumpNumber.setText("4");
			} else if (actionEvent.getSource() == pumpButton5) {
				simulationModel.readPumpData(5);
				pumpNumber.setText("5");
			}
			pumpPane.setVisible(true);
			gasKindLabel.setText(simulationModel.getGasKind());
			if (simulationModel.getGasKind().equals("---") == true) {
				pricePerLitreLabel.setText("---");
			} else {
				pricePerLitreLabel.setText(tankModel.readPrice(simulationModel.getGasKind()) + " " + eurosign);
			}
			amountRefilledLabel.setText(simulationModel.getReadAmount() + " L");
			priceCompleteLabel.setText(simulationModel.getReadPriceComp() + " " + eurosign);
			if (label1.getText().contains("Zapf") || label2.getText().contains("Zapf")
					|| label3.getText().contains("Zapf") || label4.getText().contains("Zapf")
					|| label5.getText().contains("Zapf") || label6.getText().contains("Zapf")) {
				removeGas();
				scart.decTotal(Float.valueOf(currentprice));
			}
			scart.incTotal(Float.valueOf(simulationModel.getReadPriceComp()));
			labelTotal.setText("Gesamtpreis: " + scart.getTotal() + " " + eurosign);
			Label freelabel = freeLabel();
			currentprice = Float.valueOf(simulationModel.getReadPriceComp());
			freelabel.setText("Zapfsäule " + pumpNumber.getText() + "         " + priceCompleteLabel.getText());
		} else if (t.isSelected() == false) {
			pumpPane.setVisible(false);
			removeGas();
			scart.decTotal(Float.valueOf(simulationModel.getReadPriceComp()));
			labelTotal.setText("Gesamtpreis: " + scart.getTotal() + " " + eurosign);
		}
	}
	
	//gibt ObservableList "employees" zur�ck
	public static ObservableList<String> getEmployeesNames() {

		return employeesNames;

	}
}