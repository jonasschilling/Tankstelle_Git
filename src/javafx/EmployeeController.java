package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeController implements Initializable{
	
	@FXML TableView<Employee> tableView;
	@FXML TableColumn<Employee, Integer> employeeId;
	@FXML TableColumn<Employee, String> firstName;
	@FXML TableColumn<Employee, String> lastName;
	@FXML TableColumn<Employee, String> employmentDate;
	@FXML Button addEmployee;
	private ObservableList<Employee> employees = FXCollections.observableArrayList();


	public void initialize(URL arg0, ResourceBundle arg1) {
		Employee e1 = new Employee("Patrick", "Berlet");
		Employee e2 = new Employee("Jonas", "Schilling");
		Employee e3 = new Employee("Assila", "Templin");
		employees.addAll(e1, e2, e3);
		employeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
		firstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		employmentDate.setCellValueFactory(new PropertyValueFactory<Employee, String>("stringDate"));
		tableView.setItems(employees);
	}
	public void addEmployee(ActionEvent actionEvent) {
		
		
	}
}
