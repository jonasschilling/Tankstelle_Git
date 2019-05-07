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

	public void initialize(URL arg0, ResourceBundle arg1) {
		scart = new Shoppingcart();
		hideAll();
		
	}

	public void hideAll() {
		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		label1amount.setVisible(false);
		label2amount.setVisible(false);
		label3amount.setVisible(false);
		label4amount.setVisible(false);
		label5amount.setVisible(false);
		prod1minus.setVisible(false);
		prod1plus.setVisible(false);
		prod2minus.setVisible(false);
		prod2plus.setVisible(false);
		prod3minus.setVisible(false);
		prod3plus.setVisible(false);
		prod4minus.setVisible(false);
		prod4plus.setVisible(false);
		prod5minus.setVisible(false);
		prod5plus.setVisible(false);
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
			
			if(source.isSelected()) {
				
				scart.addWodka();
				label1.setVisible(true);
				label1amount.setVisible(true);
				label1amount.setText("1");
				prod1plus.setVisible(true);
				prod1plus.setDisable(false);
				prod1minus.setVisible(true);
			} else {
				label1.setVisible(false);
				int amount1 = Integer.valueOf(label1amount.getText());
				for (int i = 0; i < amount1; i++) {
					scart.removeWodka();
				}
				label1amount.setVisible(false);
				label1amount.setText("0");
				prod1plus.setVisible(false);
				prod1minus.setVisible(false);
			}
			break;
		case "button7":
			if(source.isSelected()) {
				scart.addFilip();
				label2.setVisible(true);
				label2amount.setVisible(true);
				label2amount.setText("1");
				prod2plus.setVisible(true);
				prod2plus.setDisable(false);
				prod2minus.setVisible(true);
			} else {
				label2.setVisible(false);
				int amount2 = Integer.valueOf(label2amount.getText());
				for (int i = 0; i < amount2; i++) {
					scart.removeFilip();
				}
				label2amount.setVisible(false);
				label2amount.setText("0");
				prod2plus.setVisible(false);
				prod2minus.setVisible(false);
			}
			break;
		case "button8":
			if(source.isSelected()) {
				scart.addJupiter();
				label3.setVisible(true);
				label3amount.setVisible(true);
				label3amount.setText("1");
				prod3plus.setVisible(true);
				prod3plus.setDisable(false);
				prod3minus.setVisible(true);
			} else {
				label3.setVisible(false);
				int amount3 = Integer.valueOf(label3amount.getText());
				for (int i = 0; i < amount3; i++) {
					scart.removeJupiter();
				}
				label3amount.setVisible(false);
				label3amount.setText("0");
				prod3plus.setVisible(false);
				prod3minus.setVisible(false);			
			}
			break;
		case "button9":
			if(source.isSelected()) {
				scart.addBull();
				label4.setVisible(true);
				label4amount.setVisible(true);
				label4amount.setText("1");
				prod4plus.setVisible(true);
				prod4plus.setDisable(false);
				prod4minus.setVisible(true);
			} else {
				label4.setVisible(false);
				int amount4 = Integer.valueOf(label4amount.getText());
				for (int i = 0; i < amount4; i++) {
					scart.removeBull();
				}
				label4amount.setVisible(false);
				label4amount.setText("0");
				prod4plus.setVisible(false);
				prod4minus.setVisible(false);
			}
			break;
		case "button10":
			if(source.isSelected()) {
				scart.addPizza();
				label5.setVisible(true);
				label5amount.setVisible(true);
				label5amount.setText("1");
				prod5plus.setVisible(true);
				prod5plus.setDisable(false);
				prod5minus.setVisible(true);
			} else {
				label5.setVisible(false);
				int amount5 = Integer.valueOf(label5amount.getText());
				for (int i = 0; i < amount5; i++) {
					scart.removePizza();
				}
				label5amount.setVisible(false);
				label5amount.setText("0");
				prod5plus.setVisible(false);
				prod5minus.setVisible(false);
			}
			break;
		}
		labelTotal.setText("Gesamtpreis: " + scart.getTotal() + " €");
	}

	public void addButtonClicked(ActionEvent actionEvent) {

		Button source = (Button) actionEvent.getSource();
		switch (source.getId()) {

		case ("prod1plus"):
			
			int amount1 = Integer.valueOf(label1amount.getText());
			amount1++;
			label1amount.setText(String.valueOf(amount1));
			scart.addWodka();
			prod1plus.setDisable(scart.getNumWodka() == (scart.wodka.getAmount())? true: false);
			break;
		case ("prod2plus"):
			int amount2 = Integer.valueOf(label2amount.getText());
			amount2++;
			label2amount.setText(String.valueOf(amount2));
			scart.addFilip();
			prod2plus.setDisable(scart.getNumFilip() == (scart.filip.getAmount())? true: false);
			break;
		case ("prod3plus"):
			int amount3 = Integer.valueOf(label3amount.getText());
			amount3++;
			label3amount.setText(String.valueOf(amount3));
			scart.addJupiter();
			prod3plus.setDisable(scart.getNumJupiter() == (scart.jupiter.getAmount())? true: false);
			break;
		case ("prod4plus"):
			int amount4 = Integer.valueOf(label4amount.getText());
			amount4++;
			label4amount.setText(String.valueOf(amount4));
			scart.addBull();
			prod4plus.setDisable(scart.getNumBull() == (scart.bull.getAmount())? true: false);
			break;
		case ("prod5plus"):
			int amount5 = Integer.valueOf(label5amount.getText());
			amount5++;
			label5amount.setText(String.valueOf(amount5));
			scart.addPizza();
			prod5plus.setDisable(scart.getNumPizza() == (scart.pizza.getAmount())? true: false);
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
				prod1plus.setDisable(scart.getNumWodka() < (scart.wodka.getAmount())? false: true);
			}
			break;
		case ("prod2minus"):
			int amount2 = Integer.valueOf(label2amount.getText());
			if (amount2 > 1) {
				amount2--;
				label2amount.setText(String.valueOf(amount2));
				scart.removeFilip();
				prod2plus.setDisable(scart.getNumFilip() < (scart.filip.getAmount())? false: true);
			}
			break;
		case ("prod3minus"):
			int amount3 = Integer.valueOf(label3amount.getText());
			if (amount3 > 1) {
				amount3--;
				label3amount.setText(String.valueOf(amount3));
				scart.removeJupiter();
				prod3plus.setDisable(scart.getNumJupiter() < (scart.jupiter.getAmount())? false: true);
			}
			break;
		case ("prod4minus"):
			int amount4 = Integer.valueOf(label4amount.getText());
			if (amount4 > 1) {
				amount4--;
				label4amount.setText(String.valueOf(amount4));
				scart.removeBull();
				prod4plus.setDisable(scart.getNumBull() < (scart.bull.getAmount())? false: true);
			}
			break;
		case ("prod5minus"):
			int amount5 = Integer.valueOf(label5amount.getText());
			if (amount5 > 1) {
				amount5--;
				label5amount.setText(String.valueOf(amount5));
				scart.removePizza();
				prod5plus.setDisable(scart.getNumPizza() < (scart.pizza.getAmount())? false: true);
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
}

