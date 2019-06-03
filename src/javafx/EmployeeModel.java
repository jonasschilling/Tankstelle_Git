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

public class EmployeeModel {

	private static EmployeeModel instance;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	public EmployeeModel(){
		
	}
	
	public static EmployeeModel getInstance() {
		if (EmployeeModel.instance == null) {
			EmployeeModel.instance = new EmployeeModel();
		}
		return EmployeeModel.instance;
	}
	
	public void writeEmployees(){
//		System.getProperty("user.dir") + "\\Employees.txt\\"
		File file = new File("src/javafx/resources/Employees.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for(Employee e : EmployeeController.getEmployees()) {
				bw.write(e.getEmployeeAttributes());
				bw.newLine();
				}
		
		} catch ( IOException e ) {
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
	
	public void readEmployees() {
		
		File file = new File("src/javafx/resources/Employees.txt");

		
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);){
			String line;
			br.readLine();
			
			while ((line = br.readLine()) != null) {
				
				String[] output = line.split(";");
				EmployeeController.getEmployees().add(new Employee(output[0],output[1]));
							
			}	
			
		}catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		
		
	}
	
}