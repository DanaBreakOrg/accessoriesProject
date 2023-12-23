package org.example;

import java.util.logging.*;
import java.util.Scanner;

import static org.example.Admin.cusReq;


public class Main {

    protected static Scanner input = new Scanner (System.in);
    protected static Logger logger;

    static {

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            handler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord logRecord) {
                    return logRecord.getMessage() + "\n";
                }
            });
        }
    }

    public static void main(String[] args) {


        logger = Logger.getLogger(Main.class.getName());


        Admin a= new Admin("nasser@gmail.com","nasser","12345",0);
        Admin t= new Admin("talah@gmail.com","talah","123456",0);
        Admin.getAdmin().add(a);
        Admin.getAdmin().add(t);

        Logging.q.put(a.getEmail(), a.getPassword());
        Logging.q.put(t.getEmail(), t.getPassword());
//jelenab798@vkr1.com
        Customer c=new Customer("shahd","111","QAM","02872228","jixag36030@usoplay.com","Male",0.0,1);
        Customer c2=new Customer("dana","222","DAM","028725323","dana@gmail.com","Male",0.0,1);
        Customer.getC().add(c);
        Customer.getC().add(c2);

        Logging.q.put(c.getEmail(), c.getPassword());
        Logging.q.put(c2.getEmail(), c2.getPassword());


        Product product1 = new Product("1200","car seat" ,"waterproof car seats", "interior",50);
        Product product2 = new Product("1","Car mats" ,"black rubber car mats", "exterior",70);
        Product product3 = new Product("1400","Roof racks" ,"pack of 2 car roof rack", "electronics",100);
        Product product4 = new Product("2200","Roof racksrfs" ,"pack of 2 car roof racklkmfvm", "electronics",100);
        Product product5 = new Product("3000","Roof racks1" ,"pack of 2 car roof racksac,m", "electronics",200);
        Product product6 = new Product("3576","Roof racks2" ,"pack of 2 car roof rackkmvkl", "electronics",130);
        Product product7 = new Product("3100","Roof racks3" ,"pack of 2 car roof rackkvkv", "electronics",300);

        Operations.addProduct(product7);
        Operations.addProduct(product6);
        Operations.addProduct(product5);
        Operations.addProduct(product4);
        Operations.addProduct(product3);
        Operations.addProduct(product2);
        Operations.addProduct(product1);



        Installer n=new Installer("jelenab798@vkr1.com","woroud","123123","RAM","0568725598","122",true,2);
        Installer n1=new Installer("l,mfvjelenab798@vkr1.com","ahmad","123","nablus","0568665598","123",true,2);
        Installer n2=new Installer("leen@gmail.com","leen","321","SAM","0568722198","124",false,2);
        Installer.getInstaller().add(n);
        Installer.getInstaller().add(n1);
        Installer.getInstaller().add(n2);


        n.getReservaeddates().put(c2,"24/10/2002");

        Logging.q.put(n.getEmail(), n.getPass());
        Logging.q.put(n2.getEmail(), n2.getPass());
        Logging.q.put(n1.getEmail(), n2.getPass());

        cusReq.put(c.setRequest("24/10/2002","kia",product1,"nabl"),c);
        cusReq.put(c.setRequest("25/10/2002","kia",product1,"jenin"),c);
        //cusReq.put(c2.setRequest("24/10/2020","lampo",product1,"kalil"),c2);


        HomePage();

    }

    public static int scanner() {
        int c;
        while (true) {
            try {
                c = input.nextInt();
                break; // Exit the loop if integer input is successfully read
            } catch (java.util.NoSuchElementException e) {
                logger.log(Level.SEVERE, "Invalid input. Please enter a valid integer.", e);
                input.nextLine();
            }
        }
         input.nextLine();// Clear the input buffer
        return c;
    }



    public static void HomePage() {

        while (true) {
            logger.info("\n Welcome to the Car Accessories Company\r\n"
                    + "Do you have an account?\r\n"
                    + "1. Create account\r\n"
                    + "2. Log-in\r\n"
                    + "3. Exit.\r\n"
                    + "Enter your choice: ");


            int accountChoice = scanner(); // only integer input


            switch (accountChoice) {
                case 1: {
                    createAccountPage();
                    break;
                }

                case 2: {
                    int utype, y;

                    Logging user = new Logging();
                    logger.info("Please enter your email : ");
                    String email = input.nextLine();
                    utype = user.searchEmail(email);

                    while (utype < 0) {

                        logger.info("Please enter your email again : ");
                        email = input.nextLine();
                        utype = user.searchEmail(email);
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
                    switch (utype) {


                        case 0: {

                            Admin.adminActivities();
                            break;

                        }
                        case 1:

                            Customer.customerActivities();
                            break;



                        case 2:

                            Installer.installerActivities();
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

        logger.info("Enter your Email:");
        String email = input.nextLine();
        logger.info("Enter your username:");
        String username = input.nextLine();
        logger.info("Enter your Gender : ");
        String gen = input.nextLine();
        logger.info("Enter your Phone number:");
        String phnum = input.nextLine();
        logger.info("Enter your Address:");
        String address = input.nextLine();
        logger.info("Enter your Password:");
        String password = input.nextLine();

        Customer r = new Customer(username, password, address, phnum, email, gen,0.0,1);
        boolean create = Operations.createCustomer(r);
        if (create) {
            logger.info("A new account was created successfully");
            Logging.q.put(email, password);
        }
        else
            logger.info("This account already exists");

        HomePage();
    }
}