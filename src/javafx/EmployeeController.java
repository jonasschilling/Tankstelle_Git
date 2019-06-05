package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EmployeeController implements Initializable {

	@FXML
	TableView<Employee> tableView;
	@FXML
	TableColumn<Employee, Integer> employeeId;
	@FXML
	TableColumn<Employee, String> firstName;
	@FXML
	TableColumn<Employee, String> lastName;
	@FXML
	TableColumn<Employee, String> employmentDate;
	@FXML
	ToggleButton addEmployee;
	@FXML
	Button confirm, deleteEmployee;
	@FXML
	TextField firstNameField, lastNameField;

	EmployeeModel employeeModel = EmployeeModel.getInstance();

	private static ObservableList<Employee> employees = FXCollections.observableArrayList();

	public void initialize(URL arg0, ResourceBundle arg1) {
		if (EmployeeController.getEmployees().size() < 1) {
			createEmployee();
		}

		firstNameField.setPromptText("Vorname eingeben");
		lastNameField.setPromptText("Nachname eingeben");
		firstNameField.setVisible(false);
		lastNameField.setVisible(false);
		confirm.setVisible(false);
		employeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
		firstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		employmentDate.setCellValueFactory(new PropertyValueFactory<Employee, String>("stringDate"));
		tableView.setItems(employees);
	}

	public void addEmployee(ActionEvent actionEvent) {
		if (addEmployee.isSelected()) {
			firstNameField.setVisible(true);
			lastNameField.setVisible(true);
			confirm.setVisible(true);
		} else {
			firstNameField.setVisible(false);
			lastNameField.setVisible(false);
			confirm.setVisible(false);
		}
	}

	public void confirm(ActionEvent actionEvent) throws IOException {
		String firstname = firstNameField.getText();
		String lastname = lastNameField.getText();
		if (firstname.equals("Daniel") && (lastname.equals("Appenmaier"))) {
			showEasteregg();
		} else {
			Employee e = new Employee(firstname, lastname);
			employees.add(e);
		}
		firstNameField.clear();
		lastNameField.clear();
	}
	
	public void showEasteregg() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Easteregg.fxml"));
		Scene scene = new Scene(root);
		Stage popup = new Stage();
		popup.setTitle("Hihihihi");
		popup.setScene(scene);
		popup.show();
	}

	public void deleteEmployee(ActionEvent actionEvent) {
		ObservableList<Employee> selectedCells = tableView.getSelectionModel().getSelectedItems();
		for (int i = 0; i < selectedCells.size(); i++) {
			for (int j = 0; j < employees.size(); j++) {
				Employee t1 = selectedCells.get(i);
				Employee t2 = employees.get(j);
				if (t1.equals(t2)) {
					showConfirmationDialog(t1.getFirstName(), t1.getLastName());
					employees.remove(j);
				}

			}
		}
		tableView.setItems(employees);
	}

	public void showConfirmationDialog(String firstname, String lastname) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Mitarbeiter Entfernen");
		alert.setHeaderText(firstname + " " + lastname + " wirklich entfernen?");
		alert.setContentText("");
		Optional<ButtonType> option = alert.showAndWait();
		System.out.println(option.get().getText());
	}

	public static ObservableList<Employee> getEmployees() {

		return employees;

	}
		public void createEmployee() {

			Employee e1 = new Employee("Patrick", "Berlet");
			Employee e2 = new Employee("Jonas", "Schilling");
			Employee e3 = new Employee("Assila", "Templin");
			employees.addAll(e1, e2, e3);
			
		}
		 
	 

}
