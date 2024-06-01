package com.nt.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nt.bo.CustomerBO;

@Repository("customerDAO")
public class CustomerDAOImpl implements CustomerDAO {
    private static final String INSERT_QUERY = "INSERT INTO CUSTOMER (CUST_NAME, CUST_ADDRS, PRINCIPLE_AMOUNT, TIME_PERIOD, RATE, SIMPLE_INTEREST) VALUES (?, ?, ?, ?, ?, ?)";
    
    @Autowired
    private DataSource dataSource;

    public CustomerDAOImpl(DataSource dataSource) {
        System.out.println("Customer DAO impl 1 param constructor");
        this.dataSource = dataSource;
    }

    @Override
    public int insert(CustomerBO bo) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            // Set the parameters for the prepared statement
            preparedStatement.setString(1, bo.getCustName());
            preparedStatement.setString(2, bo.getCustAddrs());
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(bo.getPamt()));
            preparedStatement.setFloat(4, bo.getTime());
            preparedStatement.setFloat(5, bo.getRate());
            preparedStatement.setBigDecimal(6, BigDecimal.valueOf(calculateSimpleInterest(bo))); // Calculate simple interest here

            // Execute the INSERT query
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Log the exception or throw a custom exception
            throw new SQLException("Error occurred while inserting customer: " + e.getMessage(), e);
        }
    }
    
    private double calculateSimpleInterest(CustomerBO bo) {
        // Calculate simple interest based on the principle amount, rate, and time period
        double principleAmount = bo.getPamt();
        double time = bo.getTime();
        double rate = bo.getRate();
        return (principleAmount * rate * time) / 100;
    }
}
