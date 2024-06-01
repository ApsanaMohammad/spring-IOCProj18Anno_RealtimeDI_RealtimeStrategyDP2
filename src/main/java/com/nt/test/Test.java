// Test.java
package com.nt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nt.controller.MainController;
import com.nt.vo.CustomerVO;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        // Load Spring configuration file
        ApplicationContext context = new ClassPathXmlApplicationContext("com/nt/cfg/applicationcontext.xml");

        // Get MainController bean from Spring container
        MainController mainController = (MainController) context.getBean("controller");

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        try {
            // Get customer details from the user
            System.out.println("Enter customer name:");
            String custName = scanner.nextLine();

            System.out.println("Enter customer address:");
            String custAddrs = scanner.nextLine();

            System.out.println("Enter principle amount:");
            String pamt = scanner.nextLine();

            System.out.println("Enter rate of interest:");
            String rate = scanner.nextLine();

            System.out.println("Enter time period (in years):");
            String time = scanner.nextLine();

            // Create a CustomerVO object with user input
            CustomerVO customerVO = new CustomerVO();
            customerVO.setCustName(custName);
            customerVO.setCustAddrs(custAddrs);
            customerVO.setPamt(pamt);
            customerVO.setTime(time);
            customerVO.setRate(rate);
       

            // Process the customer
            String result = mainController.processCustomer(customerVO);

            // Print the result
            System.out.println(result);
        } catch (Exception e) {
            // Handle exceptions
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the Scanner object
            if (scanner != null) {
                scanner.close();
            }
            // Close the Spring context
            if (context instanceof ClassPathXmlApplicationContext) {
                ((ClassPathXmlApplicationContext) context).close();
            }
        }
    }
}
