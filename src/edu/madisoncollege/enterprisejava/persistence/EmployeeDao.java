package edu.madisoncollege.enterprisejava.persistence;

import java.sql.PreparedStatement;
import edu.madisoncollege.enterprisejava.entity.Employee;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
/**
 * @author YOUR NAME HERE
 * @version 1.0 10/20/15.
 */
public class EmployeeDao {

    public List<Employee> getAllEmployees() throws SQLException {

            List<Employee> employees = new ArrayList<Employee>();

            Connection connection = Database.getInstance().getConnection();

            String sql = "select * from employees order by emp_id";
            Statement selectStatement = connection.createStatement();

            ResultSet results = selectStatement.executeQuery(sql);

            while (results.next()) {
                int employeeId = results.getInt("emp_id");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String socialSecurityNumber = results.getString("ssn");
                String department = results.getString("dept");
                String room = results.getString("room");
                String phone = results.getString("phone");

                Employee employee = new Employee(employeeId, firstName, lastName, socialSecurityNumber, department, room, phone);
                employees.add(employee);
            }

            results.close();
            selectStatement.close();

            return employees;
        }



    public Employee getEmployee(int employeeId) throws SQLException {

        Connection connection = Database.getInstance().getConnection();

        String sql = "select * from employees where emp_id = ?";
        PreparedStatement getStmt = connection.prepareStatement(sql);
        getStmt.setInt(1, employeeId);

        ResultSet results = getStmt.executeQuery();

        results.next();

        int empId = results.getInt("emp_id");
        String firstName = results.getString("first_name");
        String lastName = results.getString("last_name");
        String socialSecurityNumber = results.getString("ssn");
        String department = results.getString("dept");
        String room = results.getString("room");
        String phone = results.getString("phone");

        Employee employee = new Employee(empId, firstName, lastName, socialSecurityNumber, department, room, phone);
        return employee;

    }

    public void updateEmployee(Employee employee) throws SQLException {
        Connection connection = Database.getInstance().getConnection();

        int empId = employee.getEmployeeId();
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        String ssn = employee.getSocialSecurityNumber();
        String dept = employee.getDepartment();
        String room = employee.getRoom();
        String phone = employee.getPhone();

        String sql = "update employees set first_name = ?," +
                " last_name = ?, ssn = ?, " +
                "dept = ?, room = ?, phone = ? where emp_id = ?";

        PreparedStatement updateStmt = connection.prepareStatement(sql);

        updateStmt.setString(1, firstName);
        updateStmt.setString(2, lastName);
        updateStmt.setString(3, ssn);
        updateStmt.setString(4, dept);
        updateStmt.setString(5, room);
        updateStmt.setString(6, phone);

        updateStmt.setInt(7, empId);

        int rows = updateStmt.executeUpdate();
        System.out.println(System.lineSeparator() + "Update successful on " + rows + " row(s).");

    }
    public void deleteEmployee(Employee employee) throws SQLException {
        // TODO implement this method

    }
    public Employee addEmployee(Employee employee) throws SQLException {
        Connection connection = Database.getInstance().getConnection();

        int empId = employee.getEmployeeId();
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        String ssn = employee.getSocialSecurityNumber();
        String dept = employee.getDepartment();
        String room = employee.getRoom();
        String phone = employee.getPhone();

        String sql = "insert into employees values (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement addStmt = connection.prepareStatement(sql);

        addStmt.setInt(1, empId);
        addStmt.setString(2, firstName);
        addStmt.setString(3, lastName);
        addStmt.setString(4, ssn);
        addStmt.setString(5, dept);
        addStmt.setString(6, room);
        addStmt.setString(7, phone);

        addStmt.executeUpdate();

        Employee newEmployee = getEmployee(empId);

        return newEmployee;
    }
}
