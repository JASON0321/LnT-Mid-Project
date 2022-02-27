package models;

public class Employee {
	
	private String employeeId;
	private String name;
	private String gender;
	private String position;
	private int salary;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String employeeId, String name, String gender, String position, int salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.gender = gender;
		this.position = position;
		this.salary = salary;
	}


	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	

}
