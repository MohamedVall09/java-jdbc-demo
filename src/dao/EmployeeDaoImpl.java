package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.Employee;
import util.DatabaseManager;

public class EmployeeDaoImpl implements EmployeeDao{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Override
    public void createEmployee(Employee employee) {
        String insert_employee_stm= "INSERT INTO tb_employees (employee_name, position, salary) VALUES (?, ?, ?)";
        executeSql(insert_employee_stm, employee.getEmployeeName(), employee.getEmployePposition(), employee.getEmployeeSalary());
    }
	
	private void executeSql(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseManager.getConnection();
            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            int affectedRows = stmt.executeUpdate();
            logger.info("SQL executed successfully. Affected rows: {}", affectedRows);

        } catch (SQLException e) {
            logger.error("Error executing SQL: {}", sql, e);
        } finally {
            closeResource(stmt);
            closeResource(conn);
        }
    }
	private void closeResource(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                logger.warn("Failed to close resource", e);
            }
        }
    }
}
