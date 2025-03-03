import dao.EmployeeDao;
import dao.EmployeeDaoImpl;

import model.Employee;


public class Main {
    public static void main(String[] args) {

        EmployeeDao employeeService = new EmployeeDaoImpl();

        Employee dev = new  Employee(100L, "Müller Klein", "Developer", 40000);
        Employee mgr = new  Employee(200L, "Monika Hardt", "Manager", 50000);
        employeeService.createEmployee(dev);
        employeeService.createEmployee(mgr);



    }
}