package refillSimulation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.Model;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.util.Duration;

public class SimulationController implements Initializable {
	
	int milliLitres = 1;
	int litres = 0;

	float amountRefilled, priceComp, priceCompRound; 
	
	String newPrice, newFuelLevel;
	 
	
	String milliLitresText;
	String litresText;
	String milliLitresText0;
	
	Timeline timer;
	
	
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
	Button stopTimerButton;
	@FXML
	ToggleButton superButton, dieselButton;
	
	
	Model model = Model.getInstance();	
	
	public void initialize(URL location, ResourceBundle resources) {
		
		float pricePerLitre = model.getTank("Super").getPricePerLitre();
		
		readSuperPrice();		
		
		timer = new Timeline(new KeyFrame(Duration.millis(10), (actionEvent) -> {
			milliLitresText = String.valueOf(milliLitres);
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
				if(getAmountRefilled() > Float.valueOf(model.readFuelLevel("Super"))) {
					stopTimer(actionEvent);
				}
			} else if (milliLitres > 9) {
				setAmountRefilled(litres, milliLitresText);
				if(getAmountRefilled() > Float.valueOf(model.readFuelLevel("Super"))) {
					stopTimer(actionEvent);
				}
			}

			priceComp = getAmountRefilled() * Float.parseFloat(model.readPrice("Super"));

			setPriceCompRound(Math.round(priceComp * 100) / 100.0f);
			priceCompLabel.setText(String.valueOf(getPriceCompRound()) + " €");

		}));
		timer.setCycleCount(Timeline.INDEFINITE);
		
	}
	
	public void readSuperPrice() {
		pricePerLitreLabel.setText(model.readPrice("Super") + " €");
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
		timer.play();
	}

	public void stopTimer(ActionEvent actionEvent) {
		timer.stop();
		System.out.println(amountRefilled + " Liter für " + priceCompRound + " € getankt.");
		decreaseTank("Super");
		model.getProgress(model.getTank("Super"));
	}

	public void resetTimer(ActionEvent actionEvent) {	
		setLitres(0);
		setLitresText("0");
		setMilliLitres(1);
		setMilliLitresText("00");
		setMilliLitresText0("00");
		
		litresLabel.setText(String.valueOf(getLitres()));
		milliLitresLabel.setText(getMilliLitresText());
		priceCompLabel.setText("0.00 €");
	}
	
	public void decreaseTank(String tankDescription) {
		Float actualFuelLevel = Float.valueOf(model.readFuelLevel(tankDescription));
		newFuelLevel = String.valueOf(actualFuelLevel - amountRefilled);
		model.writeFuelLevel(tankDescription, newFuelLevel);
	}
	
}
