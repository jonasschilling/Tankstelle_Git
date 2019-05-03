package litreclock;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Controller implements Initializable {

	int milliLitres = 1;
	int litres = 0;

	float amountRefilled, pricePerLitre = 1.539f, priceComp, priceCompRound;

	String milliLitresText0;

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

	Timeline timer;

	public void initialize(URL location, ResourceBundle resources) {

		pricePerLitreLabel.setText(String.valueOf(pricePerLitre) + " €");

		timer = new Timeline(new KeyFrame(Duration.millis(10), (actionEvent) -> {
			String milliLitresText = String.valueOf(milliLitres + 1);
			if (milliLitres < 9) {
				milliLitresText0 = ("0" + milliLitresText);
				milliLitresLabel.setText(milliLitresText0);
				if (milliLitres == 0) {
					litres++;
					milliLitres++;
				} else if (milliLitres < 98) {
					milliLitres++;
				} else if (milliLitres == 98) {
					milliLitres = 0;
				}
			} else {
				milliLitresLabel.setText(milliLitresText);
				if (milliLitres == 0) {
					litres++;
					milliLitres++;
				} else if (milliLitres < 98) {
					milliLitres++;
				} else if (milliLitres == 98) {
					milliLitres = 0;
				}
			}

			String litresText = String.valueOf(litres);
			litresLabel.setText(litresText);

			if (milliLitres < 9) {
				setAmountRefilled(litres, milliLitresText0);
			} else if (milliLitres > 9) {
				setAmountRefilled(litres, milliLitresText);
			}

			priceComp = getAmountRefilled() * pricePerLitre;
			
			
			priceCompRound = Math.round(priceComp * 100) / 100.0f;
			priceCompLabel.setText(String.valueOf(priceCompRound) + " €");

		}));
		timer.setCycleCount(Timeline.INDEFINITE);
	}

	public String getMilliLitresText0() {
		return milliLitresText0;
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
	}

}
