package org.main;

import org.example.*;


import java.util.*;
import java.util.logging.*;

//import static org.example.Admin.toString1;
//import static org.example.Installer.reqq;
import static org.example.Admin.*;
import static org.example.Logging.y;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        Admin.getAdminList().add(a);
        Admin.getAdminList().add(t);

        Logging.q.put(a.getEmail(), a.getPassword());
        Logging.q.put(t.getEmail(), t.getPassword());
//jelenab798@vkr1.com
        Customer c=new Customer("shahd","111","QAM","02872228","jixag36030@usoplay.com","Male",0.0,1);
        Customer c2=new Customer("dana","222","DAM","028725323","dana@gmail.com","Male",0.0,1);
        Customer.getCustomerList().add(c);
        Customer.getCustomerList().add(c2);

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

        getCusReq().put(c.setRequest("24/10/2002","kia",product1,"nabl"),c);
        getCusReq().put(c.setRequest("25/10/2002","kia",product1,"jenin"),c);
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

                    //int index=0;
                    switch (utype) {


                        case 0: {

                            adminActivities();
                            break;

                        }
                        case 1:

                            customerActivities();
                            break;



                        case 2:

                            installerActivities();
                            break;

                    }
                    break;
                }

                case 3: System.exit(0);

            }

        }


    }

    public static void adminActivities() {
        boolean running = true;
        while (running) {
            logger.info("\n Welcome Admin "+ Admin.getAdminList().get(y).getName() +" , to the Car accessories company.\r\n"
                    +"------------------------------------------------------------.\r\n"
                    +"Select an option:.\r\n"
                    +"1.  Show all customers.\r\n"
                    +"2.  Add a new customer.\r\n"
                    +"3.  Update a customer.\r\n"
                    +"4.  Delete a customer.\r\n"
                    +"5.  Schedule and manage installation appointments.\r\n"
                    +"6.  Add a product.\r\n"
                    +"7.  Delete a product.\r\n"
                    +"8.  Update a product.\r\n"
                    +"9.  Logout.\r\n"
                    +"Enter your choice : ");
            int choice = Main.scanner();


            switch (choice) {
                case 1: {//show all customers done
                  Customer.showAllCustomers();
                  break;
                }

                case 2: {//add a new customer done
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
                    Customer customer = new Customer(n, d, h, j, s, k,0.0, 1);
                    boolean add = Operations.addCustomer(customer);
                    if (add)
                        logger.info("A new Customer added");
                    else
                        logger.info("A Customer is already exist");
                    break;
                }

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
                                    + "6. Back.\r\n"
                    );
                    int updateChoice = input.nextInt();
                    boolean update = true;

                    switch (updateChoice) {

                        case 1:
                            logger.info("Enter a new username:");
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


                        case 6: break;

                        default:
                            logger.info("Invalid choice. Please try again.");
                            input.nextLine();
                            break;

                    }

                    if (!update)
                        logger.info("A Customer was updated");
                    else
                        logger.info("Customer doesn't exist");
                    break;
                }
                case 4: {//delete customer done
                    Scanner pass2 = new Scanner(System.in);
                    logger.info("Enter the customer username you want to delete:");//username or smth else??
                    String z1 = pass2.next();
                    Customer.deleteCustomerUsingUsername(z1);
                    break;
                }

                case 5: {

                    //Set<Request> resulttt = new HashSet<>();
                    //print all the requests on hold (need to be sent by admin)=3 2 for same cus
                    Customer.printAllRequestsAndCustomers();
                    logger.info("\n");
                    logger.info("\n");

                    Installer.printAllInstallers();

                    Scanner customerEmail = new Scanner(System.in);
                    logger.info("Enter the customer email you want to schedule installation appointment for :");
                    String ce = customerEmail.next();

                    Admin.handleCustomerRequests(ce);


                    break;

                }


                case 6: { //add a product
                    Customer.showAllProducts();

                    Scanner pId = new Scanner(System.in);
                    Scanner pName = new Scanner(System.in);
                    Scanner pDescription = new Scanner(System.in);
                    Scanner pCategory = new Scanner(System.in);
                    Scanner pPrice = new Scanner(System.in);

                    logger.info("Enter product ID : ");
                    String id = pName.nextLine();
                    logger.info("Enter product name : ");
                    String name = pName.nextLine();
                    logger.info("Enter product description : ");
                    String description = pDescription.nextLine();
                    logger.info("Enter product category (interor/exterior/electronics) : ");
                    String category = pCategory.nextLine();
                    logger.info("Enter product price : ");
                    String price = pPrice.nextLine();
                    double value = Double.parseDouble(price);
                    ;
                    Product prod = new Product(id, name, description, category, value);
                    boolean add = Operations.addProduct(prod);
                    if (add)
                        logger.info("A new product is added");
                    else
                        logger.info("This product already exists");

                    break;
                }

                case 7: {
                    Customer.showAllProducts();
                    Scanner pid = new Scanner(System.in);
                    logger.info("Enter the product name you want to delete:");
                    String z1 = pid.next();
                    int index = Operations.returnProductIndex(z1);
                    if (index == -1) {
                        logger.info("The product you want to delete doesn't exist");
                    } else {
                        boolean delete = Operations.deleteProduct(Product.getP().get(index));
                        if (!delete)
                            logger.info("The product was deleted");
                    }
                    break;
                }

                case 8: {

                    Customer.showAllProducts();
                    Scanner pId1 = new Scanner(System.in);
                    Scanner pName1 = new Scanner(System.in);
                    Scanner pDescription1 = new Scanner(System.in);
                    Scanner pCategory1 = new Scanner(System.in);
                    Scanner pPrice1 = new Scanner(System.in);

                    logger.info("Enter the product id you want to update:");
                    String z = pId1.next();

                    logger.info("Enter a new name:");
                    String pnamenew = pName1.next();
                    logger.info("Enter a new description:");
                    String pdescnew = pDescription1.nextLine();
                    logger.info("Enter a new category:");
                    String pcatnew = pCategory1.nextLine();
                    logger.info("Enter a new price:");
                    String ppricenew = pPrice1.nextLine();
                    double v = Double.parseDouble(ppricenew);
                    boolean update1 = Operations.updateP(z, z, pnamenew, pdescnew, v, pcatnew);
                    if (!update1) {
                        logger.info("A Product was updated");
                    } else {
                        logger.info("Your message was sent successfully");
                    }

                    break;
                }

                case 9: running=false;
                    break;


                default:
                    logger.info("Invalid choice. Please try again.");

                    break;
            }
        }

    }



    public static void installerActivities() {

        boolean running = true;
        while (running) {
            logger.info("\n Welcome Installer "+ Installer.getInstaller().get(y).getName() +" , to the Car accessories company.\r\n"
                    +"------------------------------------------------------------.\r\n"
                    +"Select an option:.\r\n"//add update delete view customers
                    +"1.  View installation requests.\r\n"
                    +"2.  Schedule appointments.\r\n"
                    +"3.  Logout.\r\n"
                    +"Enter your choice : ");
            int choice = Main.scanner();

            switch (choice) {

                case 1: {
                    Installer.viewInsReq();
                    break;
                }

                case 2:{
                    HandleRequestsFromAdmin(InformInstallerMethod(),Installer.getInstaller().get(y).getEmail());
                    break;
                }

                case 3: {
                    running = false;
                    break;
                }

                default:{
                    logger.info("Invalid choice. Please try again.");
                    input.nextLine();
                    break;
                }
            }

        }

    }
    public static void HandleRequestsFromAdmin(Map<Request, Installer> h, String email){

        Scanner choice = new Scanner(System.in);

        for (int k = 0; k < Installer.getInstaller().size(); k++) {

            if(Installer.getInstaller().get(k).getEmail().equals(email)) {


                for (Request key : Installer.getKeys(h, Installer.getInstaller().get(k))) {
                    logger.info(Installer.toString(key));
/*
                    logger.info("Do you want to accept this request ?\n"
                            +"1- Accept.\n"
                            +"2- Reject.\n"
                            +"Please, enter the number of the choice you want to proceed.\n");
                    String x = choice.next();*/

                    String x = acceptReq(choice);
                    if(!Installer.installerAnswer( x ,  key, k)){
                        break;
                    }

                }

            }
        }
    }



    private static String acceptReq(Scanner choice) {
        logger.info("Do you want to accept this request ?\n"
                +"1- Accept.\n"
                +"2- Reject.\n"
                +"Please, enter the number of the choice you want to proceed.\n");
        String x = choice.next();
        return x;
    }

    public static void customerActivities() {

        boolean running = true;
        while (running) {
            logger.info("\n Welcome "+Customer.getCustomerList().get(y).getUsername()+ " , to the Car accessories company.\r\n"
                            + "------------------------------------------------------------.\r\n"
                            + "Select an option:.\r\n"
                            + "1.  Profile.\r\n"//done
                            + "2.  Show all products.\r\n"//done
                            + "3.  Filter by category.\r\n"//done
                            + "4.  Filter by price.\r\n"//done
                            + "5.  Buy a product.\r\n"//done
                            + "6.  Show cart.\r\n"//done
                            + "7.  Search a product.\r\n"//done
                            + "8. Logout.\r\n"//done
                            + "Enter your choice\r"
                    //view order history
            );
            int choice = Main.scanner();

            switch (choice) {

                case 1:
                    CustomerProfile();
                    break;

                case 2:
                    Customer.showAllProducts();
                    break;

                case 3://filter by cat done

                    logger.info("Filter Accessories by category.\r\n"+
                            "------------------------------------------------------------.\r\n"+
                            "Select an option:.\r\n"+
                            "1. Interior accessories.\r\n"+
                            "2. Exterior accessories.\r\n"+
                            "3. Electronics.\r\n"+
                            "4. Back\r\n"+
                            "Enter your choice : \r"
                    );
                    int filterChoice = Main.scanner();
                    Customer.filterProducts(filterChoice);
                    //logger.info("HI");
                    break;

                case 4://filter by price done

                    logger.info("Filter Accessories by price.\n"+
                            "------------------------------------------------------------.\n"+
                            "Select an option:.\n"+
                            " 1. low priced accessories (0-70)NIS.\n"+
                            " 2. medium priced accessories (70-150)NIS.\n"+
                            " 3. high priced accessories (higher than 150) NIS.\n"+
                            " 4.Back\n"+
                            "Enter your choice:\r ");
                    int filterChoicebuPrice = Main.scanner();
                    Customer.filterProductsbyPrice(filterChoicebuPrice);
                    break;

                case 5:
                    Customer.showAllProducts();

                    boolean done = false;
                    //double total=0;
                    boolean found=false;
                    while (!done) {
                        logger.info("Enter the accessory id you want to purchase :)\r\n Press 0 when you're done shopping");
                        String pid = input.next();
                        if (pid.equals("0")) done = true;
                        else {

                            for(int i=0 ; i<Product.getP().size();i++){
                                if(pid.equals(Product.getP().get(i).getId())){
                                    Customer.pFound(i);
                                    found=true;

                                    if(Integer.parseInt(pid)<2000&&Integer.parseInt(pid)>1000){
                                        logger.info("Would you lik to make an installation request?\n"+
                                                "1- Yes.\n" +
                                                "2- No.\n"
                                                +"Enter your choice : ");

                                        String choicee= input.next();
                                        if(choicee.equals("yes")||choicee.equals("Yes")||choicee.equals("1")){

                                            Scanner req = new Scanner(System.in);
                                            Scanner c = new Scanner(System.in);
                                            logger.info("Enter your car model\r");
                                            String cmodel= req.nextLine();
                                            logger.info("Enter your preferred date (DD/MM/YYYY)\r");
                                            String predate= req.next();
                                            logger.info("Enter your location\r");
                                            String location= req.next();
                                            //Customer.showCart();
                                            //logger.info("Enter the product ID you want to install\r");
                                            //String pchoice = c.next();
                                            //boolean flag=false;


                                            for(int k = 0; k<Customer.getCustomerList().get(y).getCard().size(); k++) {
                                                if(pid.equals(Customer.getCustomerList().get(y).getCard().get(k).getId())){
                                                    //make a request
                                                    Customer.makeRequest(predate, cmodel, Customer.getCustomerList().get(y).getCard().get(k), location);
                                                    //flag=true;
                                                    break;
                                                }
                                            }
                                            //if(!flag){
                                              //  logger.info("Invalid input\r");
                                            //}
                                        }
                                        else break;
                                    }
                                    break;

                                }
                            }

                            if(!found)
                            {
                                logger.info("There is no such product id !");
                                input.nextLine();
                            }
                        }




                    }
                    //
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    String formattedDateTime = currentDateTime.format(formatter);
                    Order order=new Order(Customer.getCustomerList().get(y),Customer.getCustomerList().get(y).getCard(),formattedDateTime);

                    Customer.getCustomerList().get(y).getCustomerOrders().add(order);
                    Customer.getCustomerList().get(y).getCard().clear();
                    //not working properly

                   // Customer.submitOrder(Customer.getCustomerList().get(y),Customer.getCustomerList().get(y).getCard());
                    //Customer.getCustomerList().get(y).getCard().clear();

                    break;

                case 6:
                    Customer.showCart();
                    break;

                case 7://search product done
                    Scanner search = new Scanner(System.in);
                    logger.info("Enter a product name\r");
                    String searchP= search.nextLine();
                    Customer.searchAProduct(searchP);
                    break;

                case 8://costumer logout done
                    running = false;
                    break;


                default:
                    logger.info("Invalid input, enter your choice again\r\n");
                    input.nextLine();
                    break;
            }
        }
    }




    private static void CustomerProfile() {
        logger.info("\n Hi , "+Customer.getCustomerList().get(y).getUsername()+"\r\n"
                + "------------------------------------------------------------.\r\n"
                +" Username : "+ Customer.getCustomerList().get(y).getUsername() + "\n" + " Address : "+ Customer.getCustomerList().get(y).getAddress() + "\n"
                + " Phone : "+ Customer.getCustomerList().get(y).getPhone() + "\n" + " Email : " + Customer.getCustomerList().get(y).getEmail() +"\n\n"
                + "Select an option:.\r\n"
                + "1. Change address.\r\n"
                + "2. Change phone number.\r\n"
                + "3. Change Email.\r\n"
                + "4. Change Password.\r\n"
                + "5. View order history.\r\n"//done
                + "6. View your installation requests status.\r\n"// done
                + "7. Back.\r\n"//done
                + "Enter your choice: \r"
        );
        int p= Main.scanner();
        switch(p){
            case 1:
                Scanner newad1 = new Scanner(System.in);
                logger.info("Enter your new address\r");
                String newaddress= newad1.nextLine();
                Customer.getCustomerList().get(y).setAddress(newaddress);
                logger.info("Your address's updated :)");
                break;

            case 2:
                Scanner newph1 = new Scanner(System.in);
                logger.info("Enter your new phone number\r");
                String newph= newph1.nextLine();
                Customer.getCustomerList().get(y).setPhone(newph);
                logger.info("Your phone number's' updated :)");
                break;

            case 3:
                Scanner newemail = new Scanner(System.in);

                logger.info("Enter your new Email\r");
                String newmail= newemail.nextLine();
                Customer.getCustomerList().get(y).setEmail(newmail);
                logger.info("Your Email's updated :)");
                break;
            case 4:
                Scanner newpass1 = new Scanner(System.in);
                logger.info("Enter your old password\r");
                String oldpass= newpass1.next();
                if(Customer.getCustomerList().get(y).getPassword().equals(oldpass))
                {
                    logger.info("Enter your new password (no spaces allowed)\r");
                    String newpass = newpass1.next();
                    Customer.getCustomerList().get(y).setPassword(newpass);
                    logger.info("Your password's updated :)");
                }else{
                    logger.info("Password is incorrect\r");
                }

                break;

            case 5:{
                Customer.viewOrderHistory();
                break;
            }
            //order history


                /*case 9:{
                    logger.info("List of Orders : \n");
                    for (int i=0 ; i<Order.getOrderList().size();i++)
                    {
                        logger.info("Order "+i+"\n");
                        Order.printOrderProducts();
                        Order.getOrderList().get(i).getProducts().clear();

                    }

                    break;
                }*/

            case 6:{

                Customer.viewInstallationRequests();
                break;


            }
            case 7://back
                break;
            default:
                logger.info("Invalid input, enter your choice again!\r");
                input.nextLine();
                break;
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