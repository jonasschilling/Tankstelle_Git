package javafx;


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Thore Bedey
 *
 */
public class Employee {
	private static int employees = 0;
	private int employeeId;
	private String firstname;
	private String lastname;
	private Date employmentDate;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private String stringDate = df.format(now.getTime());
	static GregorianCalendar now = new GregorianCalendar();
	
	public Employee(String firstname, String lastname) {
		employeeId = ++employees;
		this.firstname = firstname;
		this.lastname = lastname;
		employmentDate = now.getTime();
	}

	public static void setEmployees(int employees) {
		Employee.employees = employees;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public static int getEmployees() {
		return employees;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getLastName() {
		return lastname;
	}

	public String getFirstName() {
		return firstname;
	}

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public String getStringDate() {
		return stringDate;
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}
	
	//Attribute der Mitarbeiter werden zu einem String zusammengefuegt
	public String getEmployeeAttributes() {
		
		String employeeId = Integer.toString(getEmployeeId());
		String firstname = getFirstName();
		String lastname = getLastName();
		String stringDate = getStringDate();
		
		String employeeAttributes = employeeId + ";" + firstname + ";" + lastname + ";" + stringDate;
				
		return employeeAttributes;
		
	}

}
