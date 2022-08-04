package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    /*public static void main(String[] args) {
        getConnection();
        Employee employee = new Employee();
        employee.setName("oleg");
        employee.setEmail(" ");
        employee.setCountry(" ");
        save(employee);
    }*/

    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/employee";
        String user = "postgres";
        String password = "wildfly";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    public static int save(Employee employee) {
        int status = 0;
        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into auto_parts(name,price,availability) values (?,?,?)");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getPrice());
            ps.setString(3, employee.getAvailability());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(Employee employee) {

        int status = 0;

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update auto_parts set name=?,price=?,availability=? where id=?");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getPrice());
            ps.setString(3, employee.getAvailability());
            ps.setInt(4, employee.getCode());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }

    public static int delete(int code) {

        int status = 0;

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from auto_parts where code=?");
            ps.setInt(1, code);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Employee getEmployeeById(int code) {

        Employee employee = new Employee();

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from auto_parts where code=?");
            ps.setInt(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setCode(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setPrice(rs.getString(3));
                employee.setAvailability(rs.getString(4));
            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return employee;
    }

    public static List<Employee> getAllEmployees() {

        List<Employee> listEmployees = new ArrayList<>();

        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from auto_parts");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee employee = new Employee();

                employee.setCode(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setPrice(rs.getString(3));
                employee.setAvailability(rs.getString(4));

                listEmployees.add(employee);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listEmployees;
    }
}