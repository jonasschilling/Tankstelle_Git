package refillSimulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.TankModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Duration;

public class SimulationController implements Initializable {

	int milliLitres = 1;
	int litres = 0;

	float amountRefilled, priceComp, priceCompRound;

	String newPrice, newFuelLevel, tankFrom;

	String milliLitresText;
	String litresText;
	String milliLitresText0;

	Timeline timer;
	ToggleButton pump, gas;

	@FXML
	Label pricePerLitreLabel;
	@FXML
	Label priceCompLabel;
	@FXML
	Label milliLitresLabel;
	@FXML
	Label litresLabel;
	@FXML
	Button startTimerButton;
	@FXML
	Button stopTimerButton; // , resetButton;
	@FXML
	ToggleButton superButton, dieselButton, pumpButton1, pumpButton2, pumpButton3, pumpButton4, pumpButton5;

	TankModel tankModel = TankModel.getInstance();
	SimulationModel simulationModel = SimulationModel.getInstance();

	public void initialize(URL location, ResourceBundle resources) {

		ToggleGroup pumpToggleGroup = new ToggleGroup();
		pumpToggleGroup.getToggles().add(pumpButton1);
		pumpToggleGroup.getToggles().add(pumpButton2);
		pumpToggleGroup.getToggles().add(pumpButton3);
		pumpToggleGroup.getToggles().add(pumpButton4);
		pumpToggleGroup.getToggles().add(pumpButton5);

		pricePerLitreLabel.setText("-.--- €/L");

		ToggleGroup tankToggleGroup = new ToggleGroup();
		tankToggleGroup.getToggles().add(superButton);
		tankToggleGroup.getToggles().add(dieselButton);

		superButton.setDisable(true);
		dieselButton.setDisable(true);
		startTimerButton.setDisable(true);
		stopTimerButton.setDisable(true);

		timer = new Timeline(new KeyFrame(Duration.millis(10), (actionEvent) -> {
			milliLitresText = String.valueOf(milliLitres);

			if (superButton.isPressed() == true) {
				if (getAmountRefilled() > Float.valueOf(tankModel.readFuelLevel(gas.getText()))) {
					stopTimer(actionEvent);
				}
			} else if (dieselButton.isPressed() == true) {
				if (getAmountRefilled() > Float.valueOf(tankModel.readFuelLevel(gas.getText()))) {
					stopTimer(actionEvent);
				}
			}

			if (milliLitres < 10) {
				milliLitresText0 = ("0" + getMilliLitresText());
				milliLitresLabel.setText(getMilliLitresText0());
				if (milliLitres == 0) {
					litres++;
					milliLitres++;
				} else if (milliLitres < 99) {
					milliLitres++;
				} else if (milliLitres == 99) {
					milliLitres = 0;
				}
			} else {
				milliLitresLabel.setText(getMilliLitresText());
				if (milliLitres == 0) {
					litres++;
					milliLitres++;
				} else if (milliLitres < 99) {
					milliLitres++;
				} else if (milliLitres == 99) {
					milliLitres = 0;
				}
			}

			litresText = String.valueOf(litres);
			litresLabel.setText(getLitresText());

			if (milliLitres < 9) {
				setAmountRefilled(litres, milliLitresText0);
			} else if (milliLitres > 9) {
				setAmountRefilled(litres, milliLitresText);
			}

//			if (superButton.isPressed() == true) {
//				tankFrom = "Super";
//				dieselButton.setDisable(true);
////				pricePerLitreLabel.setText(tankModel.readPrice("Super") + " €/L");
////				priceComp = getAmountRefilled() * Float.valueOf(tankModel.readPrice("Super"));
//			} else if (dieselButton.isPressed() == true) {
//				tankFrom = "Diesel";
//				superButton.setDisable(true);
////				pricePerLitreLabel.setText(tankModel.readPrice("Super") + " €/L");
////				priceComp = getAmountRefilled() * Float.valueOf(tankModel.readPrice("Diesel"));
//			}

			pricePerLitreLabel.setText(tankModel.readPrice(gas.getText()) + " €/L");
			priceComp = getAmountRefilled() * Float.valueOf(tankModel.readPrice(gas.getText()));
			setPriceCompRound(Math.round(priceComp * 100) / 100.0f);
			priceCompLabel.setText(String.valueOf(getPriceCompRound()) + " €");
		}));
		timer.setCycleCount(Timeline.INDEFINITE);
	}

	public void matchRefillData(ActionEvent e) {
		pump = (ToggleButton) e.getSource();
		if (pump != pumpButton1) {
			pumpButton1.setDisable(true);
		}
		if (pump != pumpButton2) {
			pumpButton2.setDisable(true);
		}
		if (pump != pumpButton3) {
			pumpButton3.setDisable(true);
		}
		if (pump != pumpButton4) {
			pumpButton4.setDisable(true);
		}
		if (pump != pumpButton5) {
			pumpButton5.setDisable(true);
		}
		superButton.setDisable(false);
		dieselButton.setDisable(false);
	}

	public void setPrice(ActionEvent e) {
		gas = (ToggleButton) e.getSource();
		pricePerLitreLabel.setText(tankModel.readPrice(gas.getText()) + " €/L");
		priceComp = getAmountRefilled() * Float.valueOf(tankModel.readPrice(gas.getText()));
		startTimerButton.setDisable(false);
		stopTimerButton.setDisable(false);
	}

//	public float getPricePerLitre() {
//		return pricePerLitre;
//	}
//
//	public void setPricePerLitre(float pricePerLitre) {
//		this.pricePerLitre = pricePerLitre;
//	}

	public float getPriceCompRound() {
		return priceCompRound;
	}

	public void setPriceCompRound(float priceCompRound) {
		this.priceCompRound = priceCompRound;
	}

	public int getMilliLitres() {
		return milliLitres;
	}

	public void setMilliLitres(int milliLitres) {
		this.milliLitres = milliLitres;
	}

	public int getLitres() {
		return litres;
	}

	public void setLitres(int litres) {
		this.litres = litres;
	}

	public void setMilliLitresText0(String milliLitresText0) {
		this.milliLitresText0 = milliLitresText0;
	}

	public String getMilliLitresText0() {
		return milliLitresText0;
	}

	public String getMilliLitresText() {
		return milliLitresText;
	}

	public void setMilliLitresText(String milliLitresText) {
		this.milliLitresText = milliLitresText;
	}

	public String getLitresText() {
		return litresText;
	}

	public void setLitresText(String litresText) {
		this.litresText = litresText;
	}

	public void setAmountRefilled(int litres, String milliLitres) {
		String textAmount = Integer.toString(litres) + "." + milliLitres;
		amountRefilled = Float.valueOf(textAmount);
	}

	public float getAmountRefilled() {
		return amountRefilled;
	}

	public void startTimer(ActionEvent actionEvent) {
//		timer = new Timeline(new KeyFrame(Duration.millis(10), (actionEvent2) -> {
//			milliLitresText = String.valueOf(milliLitres);
//			if (getAmountRefilled() > Float.valueOf(tankModel.readFuelLevel("Super"))) {
//				stopTimer(actionEvent2);
//			}
//			if (milliLitres < 10) {
//				milliLitresText0 = ("0" + getMilliLitresText());
//				milliLitresLabel.setText(getMilliLitresText0());
//				if (milliLitres == 0) {
//					litres++;
//					milliLitres++;
//				} else if (milliLitres < 99) {
//					milliLitres++;
//				} else if (milliLitres == 99) {
//					milliLitres = 0;
//				}
//			} else {
//				milliLitresLabel.setText(getMilliLitresText());
//				if (milliLitres == 0) {
//					litres++;
//					milliLitres++;
//				} else if (milliLitres < 99) {
//					milliLitres++;
//				} else if (milliLitres == 99) {
//					milliLitres = 0;
//				}
//			}
//
//			litresText = String.valueOf(litres);
//			litresLabel.setText(getLitresText());
//
//			if (milliLitres < 9) {
//				setAmountRefilled(litres, milliLitresText0);
//			} else if (milliLitres > 9) {
//				setAmountRefilled(litres, milliLitresText);
//			}
//			
//			
//			pricePerLitreLabel.setText(tankModel.readPrice("Super") + " €/L");
//			priceComp = getAmountRefilled() * Float.valueOf(tankModel.readPrice("Super"));
//			setPriceCompRound(Math.round(priceComp * 100) / 100.0f);
//			priceCompLabel.setText(String.valueOf(getPriceCompRound()) + " €");
//
//		}));
//		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}

	public void stopTimer(ActionEvent actionEvent) {
		timer.stop();
		simulationModel.writePumpData(pump.getText(), gas.getText(), getAmountRefilled());
		decreaseTank(gas.getText());
		tankModel.getProgress(tankModel.getTank(gas.getText()));
	}

//	public void resetTimer(ActionEvent actionEvent) {
//		setLitres(0);
//		setLitresText("0");
//		setMilliLitres(1);
//		setMilliLitresText("00");
//		setMilliLitresText0("00");
//
//		litresLabel.setText(String.valueOf(getLitres()));
//		milliLitresLabel.setText(getMilliLitresText());
//		priceCompLabel.setText("0.00 €");
//	}

	public void decreaseTank(String tankDescription) {
		Float actualFuelLevel = Float.valueOf(tankModel.readFuelLevel(tankDescription));
		newFuelLevel = String.valueOf(actualFuelLevel - amountRefilled);
		tankModel.writeFuelLevel(tankDescription, newFuelLevel);
	}

	public void resetButtons(ActionEvent actionEvent) {
		startTimerButton.setDisable(false);
		stopTimerButton.setDisable(false);
	}

}
