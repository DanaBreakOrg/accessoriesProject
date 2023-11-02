package org.example;

import java.util.logging.Logger;
import java.util.Scanner;


public class Main {


    protected static Logger logger;
    protected static Scanner input = new Scanner (System.in);
    public static void main(String[] args) {
        logger = Logger.getLogger(Main.class.getName());

        HomePage();











    }


    public static void HomePage()
    {
        logger.info("\n Welcome to the Car accessories Company\r\n"
                +"Do you have an account?\r\n"
                +"1. Create account\r\n"
                +"2. Log-in\r\n");


        int accountChoice = input.nextInt();
        input.nextLine();

        switch(accountChoice){
            case 1:
                createAccountPage();
                break;
            case 2:
                //log-in page kinda same as createaccountpage()

        }
    }

    public static void createAccountPage()
    {

        logger.info("Enter your Username:");
        String username = input.nextLine();
        logger.info("Enter your Password:");
        String password = input.nextLine();
        logger.info("Enter your Address:");
        String address = input.nextLine();
        logger.info("Enter your Phone number:");
        String phnum = input.nextLine();
        logger.info("Enter your email:");
        String email = input.nextLine();

        Customer r = new Customer(username, password, address, phnum, email);
        boolean create = Operations.createC(r);
        if (create)
            logger.info("A new account was created successfully");
        else
            logger.info("This account already exists");

        HomePage();
    }
}