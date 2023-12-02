package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;
import org.example.Request;
import static org.example.Logging.y;

public class Customer {


    private String username;
    private String address;
    private String phone;
    private String email;
    private String gender;
    private int type;
    private static int productsCounter;

    boolean addstate; // where do we use it?
    private double cost;

    boolean logState;

    String date;
    public static final  String DEC="%d - ";
    private String pass;

    boolean onHold=true;


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


    //lists
    private List<Product> card = new ArrayList<>() ;
    private List<Customer> customersWaiting = new ArrayList<>() ;
    protected static final List<Customer> C = new ArrayList<>() ;
    public List<Customer> getCustomersWaiting() {
        return customersWaiting;
    }



    public Customer() {
        logState=false;
        //pass="worker123";

    }


    public static void customerActivities() {

        boolean running = true;
        while (running) {
            logger.info("\n Welcome "+Customer.getC().get(y).getUsername()+ " , to the Car accessories company.\r\n"
                    + "------------------------------------------------------------.\r\n"
                    + "Select an option:.\r\n"
                    + "1. Profile.\r\n"//done
                    + "2. Show all products.\r\n"//done
                    + "3. Filter by category.\r\n"//done
                    + "4. Filter by price.\r\n"//done
                    + "5. Buy a product.\r\n"//done
                    + "6. Show cart.\r\n"//done
                    + "7. Search a product.\r\n"//done
                    + "8. Make an installation request.\r\n"//not done
                    + "9. View your installation requests status.\r\n"//not done
                    + "10. Logout.\r\n"//done
                    + "Enter your choice\r"
                    
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
                logger.info("""
                         Filter Accessories by category.\r
                        ------------------------------------------------------------.\r
                        Select an option:.\r
                        1. Interior accessories.\r
                        2. Exterior accessories.\r
                        3. Electronics.\r
                        4. Back\r
                        Enter your choice : \r"""

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
                
                    logger.info("""
                             Filter Accessories by price.\r
                            ------------------------------------------------------------.\r
                            Select an option:.\r
                            1. low priced accessories (0-70)NIS.\r
                            2. medium priced accessories (70-150)NIS.\r
                            3. high priced accessories (higher than 150) NIS.\r
                            4.Back\r
                            Enter your choice:\s"""


                    );
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
                
                case 5://///////////////////////////////////////////////////////////////////////////////
                    showAllProducts();
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
                                    Customer.getC().get(y).getCard().add(Product.getP().get(i));
                                    total = Customer.getC().get(y).getCost();
                                    total += Product.getP().get(i).getPrice();
                                    Customer.getC().get(y).setCost(total);
                                    logger.info("added to cart successfully !");
                                    found=true;
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
                case 8://make an installation request not done yet
                    Scanner req = new Scanner(System.in);
                    logger.info("Enter your car model\r");
                    String cmodel= req.nextLine();
                    logger.info("Enter your preferred date (DD/MM/YYYY)\r");
                    String predate= req.next();
                    //make a request

                    break;

                case 9://view installation request status not done yet

                    break;
                case 10://costumer logout done
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
        logger.info("\n Hi , "+Customer.getC().get(y).getUsername()+"\r\n"
                + "------------------------------------------------------------.\r\n"
                +" Username : "+ Customer.getC().get(y).getUsername() + "\n" + " Address : "+ Customer.getC().get(y).getAddress() + "\n"
                + " Phone : "+ Customer.getC().get(y).getPhone() + "\n" + " Email : " + Customer.getC().get(y).getEmail() +"\n\n"
                + "Select an option:.\r\n"
                + "1. Change address.\r\n"
                + "2. Change phone number.\r\n"
                + "3. Change Email.\r\n"
                + "4. Change Password.\r\n"
                + "5. View order history.\r\n"//not done
                + "6. Back.\r"//done
                + "Enter your choice: \r"
        );
        int p= Main.scanner();
        switch(p){
            case 1:
                Scanner newad1 = new Scanner(System.in);
                logger.info("Enter your new address\r");
                String newaddress= newad1.nextLine();
                Customer.getC().get(y).setAddress(newaddress);
                logger.info("Your address's updated :)");
                break;

            case 2:
                Scanner newph1 = new Scanner(System.in);
                logger.info("Enter your new phone number\r");
                String newph= newph1.nextLine();
                Customer.getC().get(y).setPhone(newph);
                logger.info("Your phone number's' updated :)");
                break;

            case 3:
                Scanner newemail = new Scanner(System.in);

                logger.info("Enter your new Email\r");
                String newmail= newemail.nextLine();
                Customer.getC().get(y).setEmail(newmail);
                logger.info("Your Email's updated :)");
                break;
            case 4:
                Scanner newpass1 = new Scanner(System.in);
                logger.info("Enter your old password\r");
                String oldpass= newpass1.next();
                if(Customer.getC().get(y).getPassword().equals(oldpass))
                {
                    logger.info("Enter your new password (no spaces allowed)\r");
                    String newpass = newpass1.next();
                    Customer.getC().get(y).setPassword(newpass);
                    logger.info("Your password's updated :)");
                }else{
                    logger.info("Password is incorrect\r");
                }

                break;

            case 5:
                //order history
                break;
            case 6://back
                break;
            default:
                logger.info("Invalid input, enter your choice again!\r");
                input.nextLine();
                break;
        }



    }

    private static void showCart() {
        for(int i=0;i<Customer.getC().get(y).getCard().size();i++) {
            String format =String.format(DEC, i + 1);
            logger.info(format +
                    "ID: "+Customer.getC().get(y).getCard().get(i).getId()+"\t"
                            +"Name: "+Customer.getC().get(y).getCard().get(i).getName()+"\t"
                            +"Description: "+Customer.getC().get(y).getCard().get(i).getDescription()+"\t"
                            +"Price: "+ Customer.getC().get(y).getCard().get(i).getPrice()+ "\n");
        }
        String totalCost=String.format("Total cost : %f",Customer.getC().get(y).getCost());
        logger.info(totalCost);
    }

    public static void showAllProducts() {
        showExteriorAccessories();
        showInteriorAccessories();
        showElectronicsAccessories();


    }

    private static void showElectronicsAccessories() {
        logger.info("""
                Electronic accessories:\r
                ------------------------------------------------------------""");
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
        logger.info("""
                Interior accessories:\r
                ------------------------------------------------------------""");
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
        logger.info("""
                Exterior accessories:\r
                ------------------------------------------------------------
                """);

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
        public static List<Customer> getC() {
            return C;
        }
        public List<Product> getCard() {
        return card;
    }






    // request functions

    public boolean getRequest() {return onHold;}

    public Request setRequest(String datte,String carModel) {
        Request r=new Request();
        this.onHold=true;
        r.preferredDate=datte;
        r.carModel=carModel;

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
