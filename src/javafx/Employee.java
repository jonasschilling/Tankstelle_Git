package javafx;

import java.text.DateFormat;
import java.time.*;
import java.util.*;

public class Employee {
	private static int employees = 0;
	private int employeeId;
	private String lastName;
	private String firstName;
	private Date employmentDate;
	DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
	private String stringDate = df.format(now.getTime());
	static GregorianCalendar now = new GregorianCalendar();
	
	public Employee(String lastName, String firstName) {
		employeeId = ++employees;
		this.lastName = lastName;
		this.firstName = firstName;
		employmentDate = now.getTime();
	}

	public static void setEmployees(int employees) {
		Employee.employees = employees;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public static int getEmployees() {
		return employees;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
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

}
