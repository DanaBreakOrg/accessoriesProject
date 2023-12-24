package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;

import static org.example.Admin.cusReq;
import static org.example.Admin.toString1;
import static org.example.Installer.reqq;
import static org.example.Logging.y;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Customer {


    private String username;
    private String address;
    private String phone;
    private String email;
    private String gender;
    private int type;
    private static int productsCounter;
    private double cost;
    boolean logState;
    public static final  String DEC="%d - ";
    private String pass;
    boolean onHold=true;




    private List<Product> card = new ArrayList<>() ;
    protected static final List<Customer> C = new ArrayList<>() ;//the used list that contains all customers,getC
    protected final List<Request> customerRequestsHistory = new ArrayList<>() ;
    protected final List<Product> customerProductsHistory = new ArrayList<>() ;


    protected static Scanner input = new Scanner (System.in);
    final static Logger logger = Logger.getLogger(Customer.class.getName());

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


    public Customer() {
        logState=false;
        //pass="worker123";

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
                  //  + "8.  View your installation requests status.\r\n"// done
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
                    showAllProducts();
                    break;

                case 3://filter by cat done
                logger.info("Filter Accessories by category.\r"+
                        "------------------------------------------------------------.\r"+
                        "Select an option:.\r"+
                        "1. Interior accessories.\r"+
                        "2. Exterior accessories.\r"+
                        "3. Electronics.\r"+
                                "4. Back\r"+
                        "Enter your choice : \r"
                    );
                    int filterChoice = Main.scanner();

                    if (filterChoice == 2) {
                        showExteriorAccessories();
                    } else if (filterChoice == 1) {
                        showInteriorAccessories();
                    } else if (filterChoice == 3) {
                        showElectronicsAccessories();
                    } else{
                        logger.info("Invalid input, enter your choice again\r");
                        input.nextLine();
                        break;
                    }
                    break;
            
                case 4://filter by price done
                
                    logger.info("Filter Accessories by price.\r"+
                            "------------------------------------------------------------.\r"+
                            "Select an option:.\r"+
                            " 1. low priced accessories (0-70)NIS.\r"+
                    " 2. medium priced accessories (70-150)NIS.\r"+
                    " 3. high priced accessories (higher than 150) NIS.\r"+
                    "4.Back\r"+
                    "Enter your choice:\r ");
                    filterChoice = Main.scanner();
                    if (filterChoice == 1) {
                        for (int i = 0; i < Product.getP().size(); i++) {
                            if ((Product.getP().get(i).getPrice() > 0) && (Product.getP().get(i).getPrice() <= 70)) {
                                logger.info(
                                        "ID: " + Product.getP().get(i).getId() + "\t"
                                                + "Name: " + Product.getP().get(i).getName() + "\t"
                                                + "Description: " + Product.getP().get(i).getDescription() + "\t"
                                                + "Price: " + Product.getP().get(i).getPrice() + "\n");
                            }
                        }
                    }
                    if (filterChoice == 2) {
                        for (int i = 0; i < Product.getP().size(); i++) {
                            if ((Product.getP().get(i).getPrice() > 70) && (Product.getP().get(i).getPrice() <= 150)) {
                                logger.info(
                                        "ID: " + Product.getP().get(i).getId() + "\t"
                                                + "Name: " + Product.getP().get(i).getName() + "\t"
                                                + "Description: " + Product.getP().get(i).getDescription() + "\t"
                                                + "Price: " + Product.getP().get(i).getPrice() + "\n");
                            }
                        }
                    }
                    if (filterChoice == 3) {
                        for (int i = 0; i < Product.getP().size(); i++) {
                            if ((Product.getP().get(i).getPrice() > 150)) {
                                logger.info(
                                        "ID: " + Product.getP().get(i).getId() + "\t"
                                                + "Name: " + Product.getP().get(i).getName() + "\t"
                                                + "Description: " + Product.getP().get(i).getDescription() + "\t"
                                                + "Price: " + Product.getP().get(i).getPrice() + "\n");
                            }
                        }
                    }
                    else{
                        logger.info("Invalid input, enter your choice again\r");
                        input.nextLine();
                        break;
                    }
                    break;
                
                case 5:
                    showAllProducts();
                    Order order=new Order();
                    boolean done = false;
                    double total=0;
                    boolean found=false;
                    while (!done) {
                        logger.info("Enter the accessory id you want to purchase :)\r\n Press 0 when you're done shopping");
                        String pid = input.next();
                        if (pid.equals("0")) done = true;
                        else {
                            //addToCart--;
                            for(int i=0 ; i<Product.getP().size();i++){
                                if(pid.equals(Product.getP().get(i).getId())){
                                    Customer.getCustomerList().get(y).getCard().add(Product.getP().get(i));
                                    Customer.getCustomerList().get(y).customerProductsHistory.add(Product.getP().get(i));
                                    total = Customer.getCustomerList().get(y).getCost();
                                    total += Product.getP().get(i).getPrice();
                                    Customer.getCustomerList().get(y).setCost(total);
                                    logger.info("added to cart successfully !");
                                    found=true;

                                    if(Integer.parseInt(pid)<2000&&Integer.parseInt(pid)>1000){
                                        logger.info("Would you lik to make an installation request?\n"+
                                                "1- Yes." +
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
                                            showCart();
                                            logger.info("Enter the product ID you want to install\r");
                                            String pchoice = c.next();
                                            boolean flag=false;


                                            for(int k = 0; k<Customer.getCustomerList().get(y).getCard().size(); k++) {
                                                if(pchoice.equals(Customer.getCustomerList().get(y).getCard().get(k).getId())){
                                                    //make a request
                                                    Request r=Customer.getCustomerList().get(y).setRequest(predate,cmodel,Customer.getCustomerList().get(y).getCard().get(k),location);
                                                    r.setStatus("Waiting for Admin response.");
                                                    cusReq.put(r,Customer.getCustomerList().get(y));

                                                    Customer.getCustomerList().get(y).customerRequestsHistory.add(r);
                                                    logger.info("Your installation request is submitted and waiting for the admin to schedule it. \r");
                                                    EmailSender.sendEmail(Customer.getCustomerList().get(y).getEmail(),"Installation request submission","Your installation request was submitted and on waiting status :)");
                                                    flag=true;
                                                    break;
                                                }
                                            }
                                            if(!flag){
                                                logger.info("Invalid input\r");
                                            }
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
                    LocalDateTime currentDateTime = LocalDateTime.now();

                    // Define the date and time format
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    // Format the current date and time
                    String formattedDateTime = currentDateTime.format(formatter);

                    // Print the formatted current date and time
                    // System.out.println("Formatted current date and time: " + formattedDateTime);

                    order.makeOrder(Customer.getCustomerList().get(y),Customer.getCustomerList().get(y).getCard(),formattedDateTime);

                    break;
                
                case 6:
                    showCart();
                    break;

                case 7://search product done
                Scanner search = new Scanner(System.in);
                    logger.info("Enter a product name\r");
                    String searchP= search.nextLine();
                    SearchAProduct(searchP);
                    break;


/*
                case 8: {//view installation requests

                    for(int i = 0; i<Customer.getCustomerList().get(y).customerRequestsHistory.size(); i++) {
                        if (Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Waiting for Installer response.") || Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Waiting for Admin response.")) {
                            logger.info("Your Request   :\n"+Admin.toString(Customer.getCustomerList().get(y).customerRequestsHistory.get(i)) + "          Waiting\n");
                        } else if (Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Approved.")&&reqq.containsKey(Customer.getCustomerList().get(y).customerRequestsHistory.get(i))) {
                            logger.info("Your Request   :\n"+Admin.toString(Customer.getCustomerList().get(y).customerRequestsHistory.get(i)) + "          Approved by    " + Admin.toString(reqq.get(Customer.getCustomerList().get(y).customerRequestsHistory.get(i))));
                        }
                    }
                    break;
                }

*/
                case 8://costumer logout done
                    running = false;
                    break;


                default:
                    logger.info("Invalid input, enter your choice again\r");
                    input.nextLine();
                    break;
            }
        }
    }

    private static void SearchAProduct(String searchP) {
        boolean found =false;
        for (int i = 0; i < Product.getP().size(); i++) {

            if (Product.getP().get(i).getName().equalsIgnoreCase(searchP) ) {
                found=true;
                logger.info(Product.getP().get(i).getId()+" | "+Product.getP().get(i).getName() + " | " + Product.getP().get(i).getDescription() + " | " + Product.getP().get(i).getPrice() +" | "+ "\r\n" );
            }else {
                found =false;
            }
        }
        if(!found)logger.info("this product can't be found");

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
                + "7. Back.\r"//done
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
                for(int i=0; i<Customer.getCustomerList().get(y).customerProductsHistory.size();i++){
                    logger.info(toString1(Customer.getCustomerList().get(y).customerProductsHistory.get(i)));

                }

                break;
            }
                //order history



            case 6:{

                //view installation requests

                for(int i = 0; i<Customer.getCustomerList().get(y).customerRequestsHistory.size(); i++) {
                    if (Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Waiting for Installer response.") || Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Waiting for Admin response.")) {
                        logger.info("Your Request   :\n"+Admin.toString(Customer.getCustomerList().get(y).customerRequestsHistory.get(i)) + "          Waiting\n");
                    } else if (Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Approved.")&&reqq.containsKey(Customer.getCustomerList().get(y).customerRequestsHistory.get(i))) {
                        logger.info("Your Request   :\n"+Admin.toString(Customer.getCustomerList().get(y).customerRequestsHistory.get(i)) + "          Approved by    " + Admin.toString(reqq.get(Customer.getCustomerList().get(y).customerRequestsHistory.get(i))));
                    }
                }
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

    private static void showCart() {
        for(int i = 0; i<Customer.getCustomerList().get(y).getCard().size(); i++) {
            String format =String.format(DEC, i + 1);
            logger.info(format +
                    "ID: "+Customer.getCustomerList().get(y).getCard().get(i).getId()+"\t"
                            +"Name: "+Customer.getCustomerList().get(y).getCard().get(i).getName()+"\t"
                            +"Description: "+Customer.getCustomerList().get(y).getCard().get(i).getDescription()+"\t"
                            +"Price: "+ Customer.getCustomerList().get(y).getCard().get(i).getPrice()+ "\n");
        }
        String totalCost=String.format("Total cost : %f",Customer.getCustomerList().get(y).getCost());
        logger.info(totalCost);
    }

    public static void showAllProducts() {
        showExteriorAccessories();
        showInteriorAccessories();
        showElectronicsAccessories();


    }

    private static void showElectronicsAccessories() {
        logger.info("Electronic accessories:\r"+
                "------------------------------------------------------------");
        for (int i = 0; i < Product.getP().size(); i++) {
            if (Product.getP().get(i).getCategory().equals("electronics")) {
                logger.info(
                        "ID: "+Product.getP().get(i).getId()+"\t"
                                +"Name: "+Product.getP().get(i).getName()+"\t"
                                +"Description: "+Product.getP().get(i).getDescription()+"\t"
                                +"Price: "+ Product.getP().get(i).getPrice()+ "\n");            }
        }

    }

    private static void showInteriorAccessories() {
        logger.info("Interior accessories:\r"+
                "------------------------------------------------------------");
        for (int i = 0; i < Product.getP().size(); i++) {
            if (Product.getP().get(i).getCategory().equals("interior")) {
                logger.info(
                        "ID: "+Product.getP().get(i).getId()+"\t"
                                +"Name: "+Product.getP().get(i).getName()+"\t"
                                +"Description: "+Product.getP().get(i).getDescription()+"\t"
                                +"Price: "+ Product.getP().get(i).getPrice()+ "\n");            }
        }
    }

    private static void showExteriorAccessories() {
        logger.info("Exterior accessories:\r"+
                "------------------------------------------------------------ ");

        for (int i = 0; i < Product.getP().size(); i++) {
            if (Product.getP().get(i).getCategory().equals("exterior")) {

                logger.info(
                        "ID: "+Product.getP().get(i).getId()+"\t"
                                +"Name: "+Product.getP().get(i).getName()+"\t"
                                +"Description: "+Product.getP().get(i).getDescription()+"\t"
                                +"Price: "+ Product.getP().get(i).getPrice()+ "\n");
                productsCounter++;
            }
        }
    }

    public static String toString(Product p) {
        return  "ID:            "+p.getId()+"\t"
                +"Name:         "+p.getName()+"\t"
                +"Description:  "+p.getDescription()+"\t"
                +"Price:        "+ p.getPrice()+ "\n";
    }

    public void logging(boolean t) {
            logState=t;
        }
        public boolean getLogState() {
            return logState;
        }


        public Customer(String username,String password, String address, String phone, String email,String Gender,double cost,int type) {
            super();
            this.cost=cost;
            this.pass = password;
            this.username = username;
            this.address = address;
            this.phone = phone;
            this.email = email;
            this.type= type;
            this.gender= Gender;


        }




        //lists functions , must be static or not?
        public static List<Customer> getCustomerList() {
            return C;
        }
        public List<Product> getCard() {
        return card;
    }






    // request functions
    public boolean getRequest() {return onHold;}

    public Request setRequest(String datte,String carModel,Product pr,String location) {
        Request r=new Request();
        this.onHold=true;
        r.preferredDate=datte;
        r.carModel=carModel;
        r.product=pr;
        r.location=location;
        r.setStatus("waiting");

        return r;
    }





    //fields functions
        public String getUsername() {
        return username;
    }
        public void setUsername(String username) {
            this.username = username;
        }


        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }


        public String getPhone() {
            return phone;
        }
        public void setPhone(String phone) {
            this.phone = phone;
        }


        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }


        public String getPassword() { return pass; }
        public void setPassword(String password) { this.pass = password; }


        public double getCost() {
        return cost;
    }
        public void setCost(double cost) {
        this.cost = cost;
    }



}
