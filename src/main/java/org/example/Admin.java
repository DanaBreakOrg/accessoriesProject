package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Admin {

    public static final  String ENAME="Enter a new username:";
    public static final  String NEWID="Enter new ID: ";
    public static final  String DEC="%d - ";

    public static final  String Email="Enter Email : ";
    public static final  String LOGOUT="You are logged out.";
    public static final  String INVALID="Invalid choice. Please try again.";


    public static final String NOTE="The Username doesn't exist, please enter your Username again";
    boolean logState;

    private String password;

    private int type;
    private String username;
    public static final List<Admin> admin = new ArrayList<>() ;
    final static Logger logger = Logger.getLogger(Admin.class.getName());
    protected static Scanner input = new Scanner (System.in);


    public static List<Admin> getAdmin() {
        return admin;
    }

    public Admin() {
        logState=false;

    }

    public Admin(String name,String password, int type) {
       // super();
        this.password= password;
        this.type = type;
        this.username = name;
    }

    public void logging(boolean t) {

        logState=t;
    }
    public boolean getLogState() {

        return logState;
    }

    public String getName() {
        return username;
    }

    //check if the installer is available or not
    public static void listw ( List<Installer> list,String name) {
        for(int i=0;i<list.size();i++)
        {   if(list.get(i).getName().equalsIgnoreCase(name)) {
            if(list.get(i).available)
                list.get(i).available=false;
            break;
        }
        }
    }

    //admin features

    public static void adminActivities() {
        boolean running = true;
        while (running) {
            logger.info("\n Welcome , to the Cleaning services for carpets and covers.\r\n"
                    +"------------------------------------------------------------.\r\n"
                    +"Select an option:.\r\n"//add update delete view customers
                    +"1. Show all customers.\r\n"
                    +"2. Add a new customer.\r\n"
                    +"3. Update a customer.\r\n"
                    +"4. Delete a customer.\r\n"

                    +"Enter the number of the activity you want to perform: ");
            int choice = input.nextInt();

            switch (choice) {


                case 1://show all customers done
                    for (int i = 0; i < Customer.getC().size(); i++) {
                        String ff=String.format("%d-" ,i + 1);
                        logger.info (ff);

                        logger.info(Customer.getC().get(i).getUsername() + "   " + Customer.getC().get(i).getAddress() + "   " + Customer.getC().get(i).getPhone() + "\r\n");

                    }
                    break;


                case 2: //add a new customer done
                    Scanner pass = new Scanner(System.in);
                    Scanner pp = new Scanner(System.in);
                    Scanner nn = new Scanner(System.in);
                    Scanner nn2 = new Scanner(System.in);


                    logger.info("Enter customer username : ");
                    String n = pp.nextLine();
                    logger.info("Enter customer Email : ");
                    String s = pass.next();
                    logger.info("Enter customer Gender : ");
                    String k = nn2.nextLine();
                    logger.info("Enter customer Phone number : ");
                    String j = nn.nextLine();
                    logger.info("Enter customer address : ");
                    String h = nn.nextLine();
                    logger.info("Enter customer password : ");
                    String d = nn2.nextLine();
                    Customer customer = new Customer(n, d, h, j, s,k, 1);
                    boolean add = Operations.addCustomer(customer);
                    if (add)
                        logger.info("A new Customer added");
                    else
                        logger.info("A Customer is already exist");
                    break;


                case 3: {//update customer done
                    Scanner pass1 = new Scanner(System.in);
                    Scanner pp1 = new Scanner(System.in);
                    Scanner nn1 = new Scanner(System.in);
                    Scanner ppp1 = new Scanner(System.in);

                    logger.info("Enter the customer username you want to update:");
                    String z = pass1.next();

                    logger.info("\n Enter the number of the information you want to update: \r\n"
                                    + "------------------------------------------------------------.\r\n"
                                    + "1. Update customer username.\r\n"
                                    + "2. Update customer password.\r\n"
                                    + "3. Update customer Email.\r\n"
                                    + "4. Update customer phone number.\r\n"
                                    + "5. Update customer address.\r\n"
                            //update type?????????????????????????????
                    );
                    int updateChoice = input.nextInt();
                    boolean update = true;

                    switch (updateChoice) {

                        case 1:
                            logger.info(ENAME);
                            String n1 = pp1.nextLine();
                            update = Operations.updateName(z, n1);
                            break;

                        case 2:
                            logger.info("Enter a new password : ");
                            String d1 = ppp1.nextLine();
                            update = Operations.updatePassword(z, d1);
                            break;

                        case 3:
                            logger.info("Enter a new Email : ");
                            String s1 = pass1.next();
                            update = Operations.updateEmail(z, s1);
                            break;

                        case 4:
                            logger.info("Enter a new Phone number : ");
                            String j1 = nn1.nextLine();
                            update = Operations.updatePhone(z, j1);
                            break;

                        case 5:
                            logger.info("Enter a new address:");
                            String h1 = nn1.nextLine();
                            update = Operations.updateAddress(z, h1);
                            break;

                        default:
                            logger.info(INVALID);
                            break;

                    }

                    if (!update)
                        logger.info("A Customer was updated");
                    else
                        logger.info("Customer doesn't exist");
                    break;
                }
                case 4: //delete customer
                    int index = -1;
                    Scanner pass2 = new Scanner(System.in);
                    logger.info("Enter the customer username you want to delete:");//username or smth else??
                    String z1 = pass2.next();
                    for (int i = 0; i < Customer.getC().size(); i++) {
                        if (Customer.getC().get(i).getUsername().equals(z1)) {
                            index = i;
                        }
                    }
                    if (index == -1) {
                        logger.info("A customer you want to delete doesn't exist");
                    } else {
                        boolean delete = Operations.deleteCustomer(Customer.getC().get(index));
                        if (!delete)
                            logger.info("A customer was deleted");
                    }
                    break;



                case 5:




                case 9:
                    System.exit(0);
                    break;


                default:
                    logger.info(INVALID);

                    break;
            }
        }

    }


}
