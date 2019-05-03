package tanken_test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

public class Controller implements Initializable {

	@FXML
	ProgressBar superBar;
	@FXML
	ProgressBar dieselBar;
	
	Tank tank1 = new Tank("Super", 12000f);
	Tank tank2 = new Tank("Diesel", 8000f);
	
	public void initialize(URL location, ResourceBundle resources) {
		superBar.setProgress((double)tank1.getRelFuelLevel());
		dieselBar.setProgress((double)tank2.getRelFuelLevel());
	}

}
