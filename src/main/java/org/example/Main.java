package org.example;

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
        Customer i=new Customer("shahod","222","QAM","02872228","shahdslajhst@gmail.com","Male",0.0,1);
        Customer i2=new Customer("dana","555","DAM","028725323","99","Male",0.0,1);
        Customer.getC().add(i);
        Customer.getC().add(i2);
        Admin.getAdmin().add(a);
        Admin.getAdmin().add(t);
        Logging.q.put("shahod", "222");
        Logging.q.put("dana", "555");
        Product product1 = new Product("1","car seat" ,"waterproof car seats", "interior",50);
        Product product2 = new Product("2","Car mats" ,"black rubber car mats", "exterior",70);
        Product product3 = new Product("3","Roof racks" ,"pack of 2 car roof rack", "electronics",100);
        Product product4 = new Product("4","Roof racksrfs" ,"pack of 2 car roof racklkmfvm", "electronics",100);
        Product product5 = new Product("5","Roof racks1" ,"pack of 2 car roof racksac,m", "electronics",200);
        Product product6 = new Product("6","Roof racks2" ,"pack of 2 car roof rackkmvkl", "electronics",130);
        Product product7 = new Product("7","Roof racks3" ,"pack of 2 car roof rackkvkv", "electronics",300);


        Operations.addP(product6);
        Operations.addP(product5);
        Operations.addP(product4);

        Operations.addP(product3);
        Operations.addP(product2);
        Operations.addP(product1);

        HomePage();





    }


    public static void HomePage() {

        while (true) {
            logger.info("\n Welcome to the Car Accessories Company\r\n"
                    + "Do you have an account?\r\n"
                    + "1. Create account\r\n"
                    + "2. Log-in\r\n"
                    + "3. Exit.\r\n");

            int accountChoice = input.nextInt();
            input.nextLine();

            switch (accountChoice) {
                case 1: {
                    createAccountPage();
                    break;
                }

                case 2: {
                    int u, y;

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

                    int index=0;
                    switch (y) {


                        case 0: {//admin
                            logger.info("\n" + " Welcome Admin " + user.username + "\n");
                            for (int i = 0; i < Admin.getAdmin().size(); i++) {
                                if (username.equals(Admin.getAdmin().get(i).getName())) {

                                    index = 0;
                                    break;
                                }
                            }
                            Admin.adminActivities();
                            break;

                        }
                        case 1:
                            logger.info("\n" + " Welcome Customer " + user.username + "\n");

                                for (int i = 0; i < Customer.getC().size(); i++) {
                                    if (username.equals(Customer.getC().get(i).getUsername())) {

                                        index = 1;
                                        y=i;
                                        break;
                                    }
                                }

                            Customer.customerActivities();
                            break;



                        case 2:
                            logger.info("\n" + " Welcome Installer " + user.username + "\n");

                                for (int i = 0; i < Installer.getW().size(); i++) {
                                    if (username.equals(Installer.getW().get(i).getName())) {

                                        index = 1;
                                        //y=i;

                                        break;
                                    }
                                }

                            break;

                    }
                    break;
                }

                case 3: System.exit(0);

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

        Customer r = new Customer(username, password, address, phnum, email, gen,0.0,1);
        boolean create = Operations.createC(r);
        if (create) {
            logger.info("A new account was created successfully");
            Logging.q.put(username, password);
        }
        else
            logger.info("This account already exists");

        HomePage();
    }
}