package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.Logging.y;

public class Customer {



        boolean logState;
        public static final  String DEC="%d - ";
        private String pass;
        final static Logger logger = Logger.getLogger(Customer.class.getName());
        private String username;
        private String address;
        private String phone;
        private String email;
        private String gender;
        private int type;
        private static int productsCounter;

        boolean addstate;
        private double cost;

        private List<Product> card = new ArrayList<>() ;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    protected static final List<Customer> C = new ArrayList<>() ;
        protected static Scanner input = new Scanner (System.in);

    public List<Product> getCard() {
        return card;
    }


        public Customer() {
            logState=false;
            //pass="worker123";

        }






    public static void customerActivities() {

        boolean running = true;
        while (running) {
            logger.info("\n Welcome , to the Car accessories company.\r\n"
                    + "------------------------------------------------------------.\r\n"
                    + "Select an option:.\r\n"
                    + "1. Profile.\r\n"
                    + "2. Show all products.\r\n"
                    + "3. Filter by category.\r\n"
                    + "4. Filter by price.\r\n"
                    + "5. Make an order.\r\n"
                    + "6. Show cart.\r\n"
                    + "7. Search a product. \r"
                    
            );
            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    CustomerProfile();


                    break;

                case 2:
                    showAllProducts();
                    
                    break;

                case 3:
                    logger.info("\n Filter Accessories by category.\r\n"
                            + "------------------------------------------------------------.\r\n"
                            + "Select an option:.\r\n"
                            + "1. Interior accessories.\r\n"
                            + "2. Exterior accessories.\r\n"
                            + "3. Electronics.\r\n"
                            + "4. Back"

                    );
                    int filterChoice= input.nextInt();

                    if(filterChoice==2){
                        logger.info("Exterior Products:\r\n" + "------------------------------------------------------------.\r\n");
                        showExteriorAccessories();
                    }
                    else if(filterChoice==1) {
                        logger.info("Interior Products:\r\n" + "------------------------------------------------------------.\r\n");
                        showInteriorAccessories();
                    }
                    else if(filterChoice==3) {
                        logger.info("Electronic Products:\r\n" + "------------------------------------------------------------.\r\n");
                        showElectronicsAccessories();
                    }

                    break;
                case 4:
                    logger.info("\n Filter Accessories by price.\r\n"
                            + "------------------------------------------------------------.\r\n"
                            + "Select an option:.\r\n"
                            + "1. low priced accessories (0-70)NIS.\r\n"
                            + "2. medium priced accessories (70-150)NIS.\r\n"
                            + "3. high priced accessories (higer than 150) NIS.\r\n"


                    );
                     filterChoice= input.nextInt();
                     if(filterChoice==1){
                         for (int i = 0; i < Product.getP().size(); i++) {

                             if ((Product.getP().get(i).getPrice()>0)&& (Product.getP().get(i).getPrice()<=70) ) {
                                 String format = String.format(DEC, i + 1);
                                 logger.info(format);
                                 logger.info(Product.getP().get(i).getId() + "   " + Product.getP().get(i).getName() + "   " + Product.getP().get(i).getDescription() + "   " + Product.getP().get(i).getPrice() + "\r\n");
                             }
                         }

                     }
                    if(filterChoice==2){
                        for (int i = 0; i < Product.getP().size(); i++) {

                            if ((Product.getP().get(i).getPrice()>70)&& (Product.getP().get(i).getPrice()<=150) ) {
                                String format = String.format(DEC, i + 1);
                                logger.info(format);
                                logger.info(Product.getP().get(i).getId() + "   " + Product.getP().get(i).getName() + "   " + Product.getP().get(i).getDescription() + "   " + Product.getP().get(i).getPrice() + "\r\n");
                            }
                        }

                    }
                    if(filterChoice==3){
                        for (int i = 0; i < Product.getP().size(); i++) {

                            if ((Product.getP().get(i).getPrice()>150) ) {
                                String format = String.format(DEC, i + 1);
                                logger.info(format);
                                logger.info(Product.getP().get(i).getId() + "   " + Product.getP().get(i).getName() + "   " + Product.getP().get(i).getDescription() + "   " + Product.getP().get(i).getPrice() + "\r\n");
                            }
                        }

                    }
                    break;

                case 5:
                    showAllProducts();
                    boolean done =false;
                    while(!done) {
                        logger.info("Please enter the accessory number you want to add to cart :)\r\n Press 0 when you're done shopping");
                        int addToCart = input.nextInt();
                        if (addToCart == 0) done = true;
                        else {
                            addToCart--;
                            Customer.getC().get(y).getCard().add(Product.getP().get(addToCart));
                            double total = Customer.getC().get(y).getCost();
                            total += Product.getP().get(addToCart).getPrice();
                            Customer.getC().get(y).setCost(total);
                            logger.info("added to cart successfully !");
                        }

                    }


                    break;

                case 6:
                    showCart();
                    break;

                case 7:
                    Scanner search = new Scanner(System.in);
                    logger.info("Enter a product name\r");
                    String searchP= search.nextLine();
                    SearchAProduct(searchP);
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
                +"| Username   |    Address     |   Phone   |       Email       |\r\n"
                +"| "+Customer.getC().get(y).getUsername()+" | "+Customer.getC().get(y).getAddress()+" | "+Customer.getC().get(y).getPhone()+" | "+Customer.getC().get(y).getEmail()+" | "
                +"\r\n"
                + "Select an option:.\r\n"
                + "1. Change address.\r\n"
                + "2. Change phone number.\r\n"
                + "3. Change Email.\r\n"
                + "4. Change Password\r\n"
                + "5. View order history\r\n"
        );
        int p= input.nextInt();
        switch(p){
            case 1:
                Scanner newad1 = new Scanner(System.in);
                logger.info("Enter your new address\r\n");
                String newaddress= newad1.nextLine();
                Customer.getC().get(y).setAddress(newaddress);
                logger.info("Your address's updated :)");
                break;

            case 2:
                Scanner newph1 = new Scanner(System.in);
                logger.info("Enter your new phone number\r\n");
                String newph= newph1.nextLine();
                Customer.getC().get(y).setPhone(newph);
                logger.info("Your phone number's' updated :)");
                break;

            case 3:
                Scanner newemail = new Scanner(System.in);

                logger.info("Enter your new Email\r\n");
                String newmail= newemail.nextLine();
                Customer.getC().get(y).setEmail(newmail);
                logger.info("Your Email's updated :)");
                break;
            case 4:
                Scanner newpass1 = new Scanner(System.in);
                logger.info("Enter your old password\r\n");
                String oldpass= newpass1.next();
                if(Customer.getC().get(y).getPassword().equals(oldpass))
                {
                    logger.info("Enter your new password (no spaces allowed)\r\n");
                    String newpass = newpass1.next();
                    Customer.getC().get(y).setPassword(newpass);
                    logger.info("Your password's updated :)");
                }else{
                    logger.info("Password is incorrect\r\n");
                }

                break;

            case 5:
                //order history
                break;
        }



    }

    private static void showCart() {
        for(int i=0;i<Customer.getC().get(y).getCard().size();i++) {
            String format =String.format(DEC, i + 1);

            logger.info("\n"+format +"  "+Customer.getC().get(y).getCard().get(i).getName() + " | " + Customer.getC().get(y).getCard().get(i).getDescription()+ " | " + Customer.getC().get(y).getCard().get(i).getPrice() + "\r\n");
        }
        String totalCost=String.format("\nTotal cost : %f",Customer.getC().get(y).getCost());
        logger.info(totalCost);
    }

    public static void showAllProducts() {
        logger.info("\nExterior accessories:\r\n"+"------------------------------------------------------------");
        showExteriorAccessories();
        
        logger.info("\nInterior accessories:\r\n" + "------------------------------------------------------------");
        showInteriorAccessories();

        logger.info("\nElectronic accessories:\r\n"+"------------------------------------------------------------");
        showElectronicsAccessories();


    }

    private static void showElectronicsAccessories() {
        for (int i = 0; i < Product.getP().size(); i++) {

            if (Product.getP().get(i).getCategory().equals("electronics")) {
                String format = String.format(DEC, i + 1);
                logger.info("\n"+ format + " | "+Product.getP().get(i).getId()+" | "+Product.getP().get(i).getName() + " | " + Product.getP().get(i).getDescription() + " | " + Product.getP().get(i).getPrice() +" | "+ "\r\n");


            }
        }

    }

    private static void showInteriorAccessories() {
        for (int i = 0; i < Product.getP().size(); i++) {
            if (Product.getP().get(i).getCategory().equals("interior")) {
                String format = String.format(DEC, i + 1);
                logger.info("\n"+ format + " | "+Product.getP().get(i).getId()+" | "+Product.getP().get(i).getName() + " | " + Product.getP().get(i).getDescription() + " | " + Product.getP().get(i).getPrice() +" | "+ "\r\n");

            }
        }
    }

    private static void showExteriorAccessories() {
        for (int i = 0; i < Product.getP().size(); i++) {
            if (Product.getP().get(i).getCategory().equals("exterior")) {
                String format = String.format(DEC, i + 1);
                logger.info("\n"+ format + " | "+Product.getP().get(i).getId()+" | "+Product.getP().get(i).getName() + " | " + Product.getP().get(i).getDescription() + " | " + Product.getP().get(i).getPrice() +" | "+ "\r\n");
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

        public static List<Customer> getC() {
            return C;
        }


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


        public String getEmail() {
            return email;
        }


        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
        return pass;
    }


        public void setPassword(String password) {
        this.pass = password;
    }



}
