package model;

public class Employee {
	private	long employeeId;  
	private	String employeeName;  
	private	String employePposition;
	private	double employeeSalary;
	
	/**
	 * @param employeeId
	 * @param employeeName
	 * @param employePposition
	 * @param employeeSalary
	 */
	public Employee(long employeeId, String employeeName, String employePposition, double employeeSalary) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employePposition = employePposition;
		this.employeeSalary = employeeSalary;
	}

	/**
	 * @return the employeeId
	 */
	public long getEmployeeId() {
		return employeeId;
	}



	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}



	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}



	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



	/**
	 * @return the employePposition
	 */
	public String getEmployePposition() {
		return employePposition;
	}



	/**
	 * @param employePposition the employePposition to set
	 */
	public void setEmployePposition(String employePposition) {
		this.employePposition = employePposition;
	}



	/**
	 * @return the employeeSalary
	 */
	public double getEmployeeSalary() {
		return employeeSalary;
	}



	/**
	 * @param employeeSalary the employeeSalary to set
	 */
	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	
	
		
}
