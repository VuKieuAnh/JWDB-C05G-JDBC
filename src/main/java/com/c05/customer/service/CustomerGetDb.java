package com.c05.customer.service;

import com.c05.customer.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerGetDb implements CustomerService {


    public static final String CUSTOMER = "select * from customer;";

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/c05g",
                    "root",
                    "123456@Abc");
            System.out.println("ket noi thanh cong");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Ket noi khong thanh cong");
        }
        return connection;
    }


    @Override
    public List<Customer> findAll() {
        Connection connection = getConnection();
        List<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(CUSTOMER);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                Customer c = new Customer(id, name, email, address);
                customers.add(c);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customers;
    }

    @Override
    public void save(Customer customer) {
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into customer(name, email, address) value (?, ?, ?);");
            System.out.println(statement);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddress());
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall("call get_customer_by_id(?)");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                customer = new Customer(name, email, address);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    @Override
    public void update(int id, Customer customer) {

    }

    @Override
    public void remove(int id) {

    }
}
