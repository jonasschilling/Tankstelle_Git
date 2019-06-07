package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author Patrick Berlet
 *
 */
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
		//Textfelder werden beschriftet und noch ausgeblendet, erst bei Button druecken anzeigen
		firstNameField.setPromptText("Vorname eingeben");
		lastNameField.setPromptText("Nachname eingeben");
		firstNameField.setVisible(false);
		lastNameField.setVisible(false);
		confirm.setVisible(false);
		//Attribute der Mitarbeiter werden den Zellen zugeordnet
		employeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
		firstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		employmentDate.setCellValueFactory(new PropertyValueFactory<Employee, String>("stringDate"));
		tableView.setItems(employees); //Der TableView wird die ObservableList "employees" zugewiesen
	}
	
	//Textfelder werden beim druecken des Buttons angezeigt
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

	//erstellt neuen Mitarbeiter mit Angaben aus den Textfeldern und fuegt ihn der Tablle hinzu
	public void confirm(ActionEvent actionEvent) throws IOException {
		String firstname = firstNameField.getText();
		String lastname = lastNameField.getText();
		if (firstname.matches("[A-Za-z]+") && lastname.matches("[A-Za-z]+")) {
			//EasterEgg
			if (firstname.equals("Daniel") && (lastname.equals("Appenmaier"))) {
				showEasteregg();
			} else {
				Employee e = new Employee(firstname, lastname);
				employees.add(e);
			}
			firstNameField.clear();
			lastNameField.clear();
		}
		//Alert bei falscher Eingabe (nur Buchstaben von A-Z moeglich)
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Fehler");
			alert.setHeaderText("Nur Buchstaben und keine Umlaute verwenden!");
			alert.setContentText("Außerdem bitte beide Felder ausfüllen");
			alert.showAndWait();
		}
	}

	//loest EasterEgg aus
	public void showEasteregg() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Easteregg.fxml"));
		Scene scene = new Scene(root);
		Stage popup = new Stage();
		popup.setTitle("Hihihihi");
		popup.setScene(scene);
		popup.show();
	}
	
	//loescht ausgewaehlten Mitarbeiter aus der Tablle und Textdatei
	public void deleteEmployee(ActionEvent e) {
		ObservableList<Employee> selectedCells = tableView.getSelectionModel().getSelectedItems();
		if (selectedCells.size() != 0) {
			showConfirmationDialog();
		}
	}

	//Bestaetigung ob Mitarbeiter wirklich geloescht werden soll
	public void showConfirmationDialog() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Mitarbeiter Entfernen");
		alert.setHeaderText("Diesen Mitarbeiter wirklich entfernen?");
		alert.setContentText("");
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get().getText().equals("Cancel")) {
			alert.hide();
		} else {
			ObservableList<Employee> selectedCells = tableView.getSelectionModel().getSelectedItems();
			for (int i = 0; i < selectedCells.size(); i++) {
				for (int j = 0; j < employees.size(); j++) {
					Employee t1 = selectedCells.get(i);
					Employee t2 = employees.get(j);
					if (t1.equals(t2)) {
						employees.remove(j);
					}

				}
			}
			tableView.setItems(employees);
		}
	}

	//gibt ObservableList "employees" zurueck
	public static ObservableList<Employee> getEmployees() {

		return employees;

	}

	//drei Mitarbeiter werden erstellt und der ObservableList "employees" hinzugefuegt 
	public void createEmployee() {

		Employee e1 = new Employee("Patrick", "Berlet");
		Employee e2 = new Employee("Jonas", "Schilling");
		Employee e3 = new Employee("Assila", "Templin");
		employees.addAll(e1, e2, e3);

	}

}
