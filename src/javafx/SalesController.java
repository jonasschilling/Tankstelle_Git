package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class SalesController implements Initializable {

	Shoppingcart scart;
	@FXML
	ToggleButton button6;
	@FXML
	ToggleButton button7;
	@FXML
	ToggleButton button8;
	@FXML
	ToggleButton button9;
	@FXML
	ToggleButton button10;
	@FXML
	Button prod1plus, prod1minus;
	@FXML
	Button prod2plus, prod2minus;
	@FXML
	Button prod3plus, prod3minus;
	@FXML
	Button prod4plus, prod4minus;
	@FXML
	Button prod5plus, prod5minus;
	@FXML
	Label label1, label1amount;
	@FXML
	Label label2, label2amount;
	@FXML
	Label label3, label3amount;
	@FXML
	Label label4, label4amount;
	@FXML
	Label label5, label5amount;
	@FXML
	Label labelTotal;
	@FXML
	AnchorPane anchor1;
	@FXML
	AnchorPane anchor2;
	@FXML
	AnchorPane anchor3;
	@FXML
	AnchorPane anchor4;
	@FXML
	AnchorPane anchor5;

	public void initialize(URL arg0, ResourceBundle arg1) {
		scart = new Shoppingcart();
		hideAll();

	}

	public void hideAll() {
		anchor1.setVisible(false);
		anchor2.setVisible(false);
		anchor3.setVisible(false);
		anchor4.setVisible(false);
		anchor5.setVisible(false);
//		label1amount.setVisible(false);
//		label2amount.setVisible(false);
//		label3amount.setVisible(false);
//		label4amount.setVisible(false);
//		label5amount.setVisible(false);
//		prod1minus.setVisible(false);
//		prod1plus.setVisible(false);
//		prod2minus.setVisible(false);
//		prod2plus.setVisible(false);
//		prod3minus.setVisible(false);
//		prod3plus.setVisible(false);
//		prod4minus.setVisible(false);
//		prod4plus.setVisible(false);
//		prod5minus.setVisible(false);
//		prod5plus.setVisible(false);
		labelTotal.setText("Gesamtpreis: 0.00 €");
	}

	public void unToggleAll() {
		button6.setSelected(false);
		button7.setSelected(false);
		button8.setSelected(false);
		button9.setSelected(false);
		button10.setSelected(false);
	}

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
		labelTotal.setText("Gesamtpreis: " + scart.getTotal() + " €");
	}

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
			price = scart.getNumWodka() * scart.wodka.getPriceSell();
			currentlabel.setText("Wodka                 " + price + "€");
			break;
		case ("prod2plus"):
			int amount2 = Integer.valueOf(label2amount.getText());
			amount2++;
			label2amount.setText(String.valueOf(amount2));
			scart.addFilip();
			prod2plus.setDisable(scart.getNumFilip() == (scart.filip.getAmount()) ? true : false);
			currentlabel = getCurrentLabel("Filip");
			price = scart.getNumFilip() * scart.filip.getPriceSell();
			currentlabel.setText("Filip Maurice        " + price + "€");
			break;
		case ("prod3plus"):
			int amount3 = Integer.valueOf(label3amount.getText());
			amount3++;
			label3amount.setText(String.valueOf(amount3));
			scart.addJupiter();
			prod3plus.setDisable(scart.getNumJupiter() == (scart.jupiter.getAmount()) ? true : false);
			currentlabel = getCurrentLabel("Jupiter");
			price = scart.getNumJupiter() * scart.jupiter.getPriceSell();
			currentlabel.setText("Jupiter Riegel        " + price + "€");
			break;
		case ("prod4plus"):
			int amount4 = Integer.valueOf(label4amount.getText());
			amount4++;
			label4amount.setText(String.valueOf(amount4));
			scart.addBull();
			prod4plus.setDisable(scart.getNumBull() == (scart.bull.getAmount()) ? true : false);
			currentlabel = getCurrentLabel("Bull");
			price = scart.getNumBull() * scart.bull.getPriceSell();
			currentlabel.setText("Sitting Bull           " + price + "€");
			break;
		case ("prod5plus"):
			int amount5 = Integer.valueOf(label5amount.getText());
			amount5++;
			label5amount.setText(String.valueOf(amount5));
			scart.addPizza();
			prod5plus.setDisable(scart.getNumPizza() == (scart.pizza.getAmount()) ? true : false);
			currentlabel = getCurrentLabel("Pizza");
			price = scart.getNumPizza() * scart.pizza.getPriceSell();
			currentlabel.setText("Pizza                    " + price + "€");
			break;
		}
		labelTotal.setText("Gesamtpreis: " + scart.getTotal() + " €");
	}

	public void removeButtonClicked(ActionEvent actionEvent) {
		Button source = (Button) actionEvent.getSource();
		switch (source.getId()) {

		case ("prod1minus"):

			int amount1 = Integer.valueOf(label1amount.getText());
			if (amount1 > 1) {
				amount1--;
				label1amount.setText(String.valueOf(amount1));
				scart.removeWodka();
				prod1plus.setDisable(scart.getNumWodka() < (scart.wodka.getAmount()) ? false : true);
			}
			break;
		case ("prod2minus"):
			int amount2 = Integer.valueOf(label2amount.getText());
			if (amount2 > 1) {
				amount2--;
				label2amount.setText(String.valueOf(amount2));
				scart.removeFilip();
				prod2plus.setDisable(scart.getNumFilip() < (scart.filip.getAmount()) ? false : true);
			}
			break;
		case ("prod3minus"):
			int amount3 = Integer.valueOf(label3amount.getText());
			if (amount3 > 1) {
				amount3--;
				label3amount.setText(String.valueOf(amount3));
				scart.removeJupiter();
				prod3plus.setDisable(scart.getNumJupiter() < (scart.jupiter.getAmount()) ? false : true);
			}
			break;
		case ("prod4minus"):
			int amount4 = Integer.valueOf(label4amount.getText());
			if (amount4 > 1) {
				amount4--;
				label4amount.setText(String.valueOf(amount4));
				scart.removeBull();
				prod4plus.setDisable(scart.getNumBull() < (scart.bull.getAmount()) ? false : true);
			}
			break;
		case ("prod5minus"):
			int amount5 = Integer.valueOf(label5amount.getText());
			if (amount5 > 1) {
				amount5--;
				label5amount.setText(String.valueOf(amount5));
				scart.removePizza();
				prod5plus.setDisable(scart.getNumPizza() < (scart.pizza.getAmount()) ? false : true);

			}
			break;
		}
		labelTotal.setText("Gesamtpreis: " + scart.getTotal() + " €");
	}

	public void checkout(ActionEvent actionEvent) {
		scart.checkout();
		hideAll();
		unToggleAll();
	}

	public void cancel(ActionEvent actionEvent) {
		scart.cancel();
		hideAll();
		unToggleAll();
	}

	// die Pressed und Released-Methoden führen Operationen aus, die bei Auswählen
	// bzw. Abwählen eines Produktes wichtig sind.
	public void wodkaPressed() {
		scart.addWodka();
		anchor1.setVisible(true);
		label1amount.setText("1");
		prod1plus.setDisable(false);
		Label freelabel = freeLabel();
		float price = scart.getNumWodka() * scart.wodka.getPriceSell();
		freelabel.setText("Wodka                 " + price + "€");
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
		float price = scart.getNumFilip() * scart.filip.getPriceSell();
		freelabel.setText("Filip Maurice        " + price + "€");

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
		float price = scart.getNumJupiter() * scart.jupiter.getPriceSell();
		freelabel.setText("Jupiter Riegel        " + price + "€");
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
		float price = scart.getNumBull() * scart.bull.getPriceSell();
		freelabel.setText("Sitting Bull           " + price + "€");
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
		float price = scart.getNumPizza() * scart.pizza.getPriceSell();
		freelabel.setText("Pizza                    " + price + "€");

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
	// anderen Schriftzüge weiter hoch rücken in der Anzeige
	public void removeWodka() {
		if (label1.getText().contains("Wodka")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label2.getText().contains("Wodka")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label3.getText().contains("Wodka")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label4.getText().contains("Wodka")) {
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label5.getText().contains("Wodka")) {
			label5.setText("");
		}
	}

	public void removeFilip() {
		if (label1.getText().contains("Filip")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label2.getText().contains("Filip")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label3.getText().contains("Filip")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label4.getText().contains("Filip")) {
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label5.getText().contains("Filip")) {
			label5.setText("");
		}

	}

	public void removeJupiter() {
		if (label1.getText().contains("Jupiter")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label2.getText().contains("Jupiter")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label3.getText().contains("Jupiter")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label4.getText().contains("Jupiter")) {
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label5.getText().contains("Jupiter")) {
			label5.setText("");
		}
	}

	public void removeBull() {
		if (label1.getText().contains("Bull")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label2.getText().contains("Bull")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label3.getText().contains("Bull")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label4.getText().contains("Bull")) {
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label5.getText().contains("Bull")) {
			label5.setText("");
		}
	}

	public void removePizza() {
		if (label1.getText().contains("Pizza")) {
			label1.setText(label2.getText());
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label2.getText().contains("Pizza")) {
			label2.setText(label3.getText());
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label3.getText().contains("Pizza")) {
			label3.setText(label4.getText());
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label4.getText().contains("Pizza")) {
			label4.setText(label5.getText());
			label5.setText("");
		} else if (label5.getText().contains("Pizza")) {
			label5.setText("");
		}
	}

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
		} else {
			return null;
		}
	}

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
		} else {
			return null;
		}
	}
}