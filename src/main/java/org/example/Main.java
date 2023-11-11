package org.example;

import java.util.HashMap;
import java.util.logging.Logger;
import java.util.Scanner;


public class Main {


    protected static Logger logger;
    protected static Scanner input = new Scanner (System.in);
    public static void main(String[] args) {


        logger = Logger.getLogger(Main.class.getName());
        Admin a= new Admin("nasser","12345",0);
        Admin t= new Admin("talah","123456",0);
        Logging.q.put("nasser", "12345");
        Logging.q.put("talah", "123");

        Admin.getAdmin().add(a);
        Admin.getAdmin().add(t);

        HomePage();





    }


    public static void HomePage()
    {
        logger.info("\n Welcome to the Car Accessories Company\r\n"
                +"Do you have an account?\r\n"
                +"1. Create account\r\n"
                +"2. Log-in\r\n");


        int accountChoice = input.nextInt();
        input.nextLine();

        switch(accountChoice){
            case 1:{
                createAccountPage();
                break;}

            case 2: {
                int u , y;

                Logging user = new Logging();
                logger.info("Please enter your username : ");
                String username = input.nextLine();
                u = user.searchUsername(username);

                while (u < 0) {

                    logger.info("Please enter your username again : ");
                    username = input.nextLine();
                    u = user.searchUsername(username);
                }

                logger.info("Please enter your password : ");
                String password = input.nextLine();
                y = user.searchPassword(password);

                while (y == -33) {

                    logger.info("Please enter your password again : ");
                    password = input.nextLine();
                    y = user.searchPassword(password);

                }

                switch (y) {

                    case 0: {//admin
                        logger.info("\n" + " Welcome Admin " + user.username + "\n");
                        Admin.adminActivities();

                    }
                    case 1:
                        logger.info("\n" + " Welcome Customer " + user.username + "\n");

                    case 2:
                        logger.info("\n" + " Welcome Installer " + user.username + "\n");

                }

            }

        }

    }

    public static void createAccountPage()
    {

        logger.info("Enter your Username:");
        String username = input.nextLine();
        logger.info("Enter your email:");
        String email = input.nextLine();
        logger.info("Enter your Gender : ");
        String gen = input.nextLine();
        logger.info("Enter your Phone number:");
        String phnum = input.nextLine();
        logger.info("Enter your Address:");
        String address = input.nextLine();
        logger.info("Enter your Password:");
        String password = input.nextLine();

        Customer r = new Customer(username, password, address, phnum, email, gen,1);
        boolean create = Operations.createC(r);
        if (create)
            logger.info("A new account was created successfully");
        else
            logger.info("This account already exists");

        HomePage();
    }
}