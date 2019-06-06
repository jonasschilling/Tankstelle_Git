package javafx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Patrick Berlet
 *
 */
public class EmployeeModel {

	private static EmployeeModel instance;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	public EmployeeModel() {

	}

	public static EmployeeModel getInstance() {
		if (EmployeeModel.instance == null) {
			EmployeeModel.instance = new EmployeeModel();
		}
		return EmployeeModel.instance;
	}
	
	//schreibt Textdatei "Employees.txt", welche alle aktuellen Mitarbeiter beinhaltet und speichert sie ab
	public void writeEmployees() {
		File file = new File("src/javafx/resources/Employees.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for (Employee e : EmployeeController.getEmployees()) {
				bw.write(e.getEmployeeAttributes());
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	//liest Textdatei "Employees.txt" mit Mitarbeitern aus und fügt diese der ObservableList "employees" hinzu
	public void readEmployees() {

		File file = new File("src/javafx/resources/Employees.txt");

		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {
			String line;

			while ((line = br.readLine()) != null) {

				String[] output = line.split(";");
				Employee E = new Employee(output[1], output[2]);
				EmployeeController.getEmployees().add(E);
				String fullName = E.getFirstName() + " " + E.getLastName();
				SalesController.getEmployeesNames().add(fullName);
			}

		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}

	}

}
