package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePriceController implements Initializable {
	
	@FXML
	TextField newSuperPrice;
	@FXML
	TextField newDieselPrice;
	@FXML
	TextField newWodkaPrice;
	@FXML
	TextField newFilipPrice;
	@FXML
	TextField newJupiterPrice;
	@FXML
	TextField newBullPrice;
	@FXML
	TextField newPizzaPrice;
	
	TankModel tankModel = TankModel.getInstance();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		newSuperPrice.setPromptText(tankModel.readPrice("Super"));
		newDieselPrice.setPromptText(tankModel.readPrice("Diesel"));
		newWodkaPrice.setPromptText(tankModel.readPrice("Wodka"));
		newFilipPrice.setPromptText(tankModel.readPrice("Filip"));
		newJupiterPrice.setPromptText(tankModel.readPrice("Jupiter"));
		newBullPrice.setPromptText(tankModel.readPrice("Bull"));
		newPizzaPrice.setPromptText(tankModel.readPrice("Pizza"));
	}
	
	public void changePrices(ActionEvent actionEvent){
		if(newSuperPrice != null) {
			tankModel.getTank("Super").setPricePerLitre(Float.valueOf(newSuperPrice.getText()));
			tankModel.writePrice("Super", newSuperPrice.getText());
		}
		if(newDieselPrice != null) {
			tankModel.getTank("Diesel").setPricePerLitre(Float.valueOf(newDieselPrice.getText()));
			tankModel.writePrice("Diesel", newDieselPrice.getText());
		}
		Stage popup = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
		popup.hide();
	}
	
	public void cancel(ActionEvent actionEvent) {
		Stage popup = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
		popup.hide();
	}
	
}
