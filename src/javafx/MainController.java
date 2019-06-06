package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.FinancesController;
import javafx.SalesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Thore Bedey
 *
 */
public class MainController implements Initializable {
	@FXML
	private TabPane tabPane;

	@FXML
	private Tab salesTab, financesTab, administrationTab, employeeTab;

	@FXML
	private SalesController salesPageController;
	@FXML
	private AdministrationController administrationPageController;
	@FXML
	private EmployeeController employeePageController;
	@FXML
	private FinancesController financesPageController;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}
