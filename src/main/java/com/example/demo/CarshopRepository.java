package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarshopRepository {

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
            if (connection == null) {
                System.out.println("Failed to make connection!");
            } else {
                System.out.println("Connected to the PostgreSQL server successfully.");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    public static int save(Carshop carshop) {
        int status = 0;
        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into auto_parts(name,price,availability) values (?,?,?)");
            ps.setString(1, carshop.getName());
            ps.setString(2, carshop.getPrice());
            ps.setString(3, carshop.getAvailability());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(Carshop carshop) {

        int status = 0;

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update auto_parts set name=?,price=?,availability=? where code=?");
            ps.setString(1, carshop.getName());
            ps.setString(2, carshop.getPrice());
            ps.setString(3, carshop.getAvailability());
            ps.setInt(4, carshop.getCode());

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
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from auto_parts where code=?");
            ps.setInt(1, code);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Carshop getCarshopByCode(int code) {

        Carshop carshop = new Carshop();

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from auto_parts where code=?");
            ps.setInt(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carshop.setCode(rs.getInt(1));
                carshop.setName(rs.getString(2));
                carshop.setPrice(rs.getString(3));
                carshop.setAvailability(rs.getString(4));
            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return carshop;
    }

    public static List<Carshop> getAllCarshops() {

        List<Carshop> listCarshops = new ArrayList<>();

        try {
            Connection connection = CarshopRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from auto_parts");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Carshop carshop = new Carshop();

                carshop.setCode(rs.getInt(1));
                carshop.setName(rs.getString(2));
                carshop.setPrice(rs.getString(3));
                carshop.setAvailability(rs.getString(4));

                listCarshops.add(carshop);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCarshops;
    }
}