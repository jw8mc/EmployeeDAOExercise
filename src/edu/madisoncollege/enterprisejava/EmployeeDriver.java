package edu.madisoncollege.enterprisejava;

import edu.madisoncollege.enterprisejava.entity.Employee;
import edu.madisoncollege.enterprisejava.persistence.Database;
import edu.madisoncollege.enterprisejava.persistence.EmployeeDao;

import java.sql.SQLException;

/**
 * @author paulawaite
 * @version 1.0 10/20/15.
 */
public class EmployeeDriver {
    /** The main method is used to run the various
     *   Dao methods.
     *
     * @param args
     */
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        try {
            Database.getInstance().connect();
            System.out.println(dao.getAllEmployees());

            //TODO add code to call the other dao methods

            System.out.println(dao.getEmployee(104).toString());

            Employee updateEmployee = new Employee(104, "Barbey", "Carbby", "144-55-6660", "HR", "227", "555-3787");
            //Employee updateEmployee = new Employee(104, "Barney", "Curry", "444-55-6666", "IT", "128", "555-3729");
            dao.updateEmployee(updateEmployee);

            System.out.println(dao.getEmployee(104).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } /*finally {
            Database.getInstance().disconnect();
        }*/
    }

}
