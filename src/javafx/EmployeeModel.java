package javafx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeModel {

	private static EmployeeModel instance;

	
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
			System.out.println("Employee is closing");
		}
		
	}
	
	public void readEmployees() {
		
		File file = new File("src/javafx/resources/Employees.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		try {
			String line;
			br.readLine();
			
			while ((line = br.readLine()) != null) {
				
				String[] output = line.split(";");
				EmployeeController.getEmployees().add(new Employee(Integer.parseInt(output[0]),output[1], output[2],  ));
							
			}	
			
		}catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
