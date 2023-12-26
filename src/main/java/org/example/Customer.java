package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.*;

import static org.example.Admin.getCusReq;
import static org.example.Admin.toString;
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
    protected List<Request> customerRequestsHistory = new ArrayList<>() ;
    public List<Order> customerOrders = new ArrayList<>() ;

    //Order order=new Order();
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






    public static boolean SearchAProduct(String searchP) {
        boolean found =false;
        for (int i = 0; i < Product.getP().size(); i++) {

            if (Product.getP().get(i).getName().equalsIgnoreCase(searchP) ) {
                found=true;
                logger.info(Product.getP().get(i).getId()+" | "+Product.getP().get(i).getName() + " | " + Product.getP().get(i).getDescription() + " | " + Product.getP().get(i).getPrice() +" | "+ "\r\n" );
            }
        }
        if(!found)logger.info("this product can't be found");

        return found;
    }





    public static void showCart() {
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
    public static void filterProducts(int by){
        switch(by){
            case 1: showInteriorAccessories(); break;
            case 2: showExteriorAccessories(); break;
            case 3: showElectronicsAccessories(); break;
            case 4: break;
            default:logger.info("Invalid input, enter your choice again\r");
                //input.nextLine();
                break;
        }

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

    public void printOrderProducts(Order order) {

        logger.info("Order info : \n");
        double total=0;
        for (Product product : order.products) {
            logger.info("Product id : " + product.getId() + "\t\t" + " Name : " + product.getName() + "\t\t" + " Price : " + product.getPrice()+"\n");
            total += product.getPrice();
        }

        logger.info("Time       :    "+order.date2+"\n");
        logger.info("Total cost :    "+total+"\n\n");


    }

    public static void viewOrderHistory() {
        for(int i=0; i<Customer.getCustomerList().get(y).customerOrders.size();i++){
            Customer.getCustomerList().get(y).printOrderProducts(Customer.getCustomerList().get(y).customerOrders.get(i));

        }
    }

    public static void showAllCustomers() {
        for (int i = 0; i < Customer.getCustomerList().size(); i++) {
            String ff = String.format("%d-", i + 1);
            logger.info(ff);
            logger.info(Customer.getCustomerList().get(i).getUsername() + "   " + Customer.getCustomerList().get(i).getAddress() + "   " + Customer.getCustomerList().get(i).getPhone() + "\r\n");

        }
    }
    public static void deleteCustomerUsingUsername(String username) {
        int index = -1;
        for (int i = 0; i < Customer.getCustomerList().size(); i++) {
            if (Customer.getCustomerList().get(i).getUsername().equals(username)) {
                index = i;
            }
        }
        if (index == -1) {
            logger.info("A customer you want to delete doesn't exist");
        } else {
            boolean delete = Operations.deleteCustomer(Customer.getCustomerList().get(index));
            if (!delete)
                logger.info("A customer was deleted");
        }
    }

    public static void printAllRequestsAndCustomers() {
        for (Map.Entry< Request,Customer> entry : getCusReq().entrySet()) {//do we need to know the product?
            logger.info("Request Info   :\n"+Admin.toString(entry.getKey()) + " \n " + "Customer Info   :\n"+Admin.toString(entry.getValue()));//////////////
        }
    }

    /*
    public static void printAllRequestsOfaCustomer(String cusmail) {
        for (Map.Entry< Request,Customer> entry : cusReq.entrySet()) {//do we need to know the product?
            if(cusReq.get(entry.getKey()).getEmail().equals(cusmail)) {
                logger.info("Request Info   :\n" + Admin.toString(entry.getKey()) + " \n " );//////////////
            }
        }
    }*/



    public static void viewInstallationRequests() {
        for(int i = 0; i<Customer.getCustomerList().get(y).customerRequestsHistory.size(); i++) {
            if (Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Waiting for Installer response.") || Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Waiting for Admin response.")) {
                logger.info("Your Request   :\n"+Admin.toString(Customer.getCustomerList().get(y).customerRequestsHistory.get(i)) + "          Waiting\n");
            } else if (Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Approved.")&& Installer.getReservedDone().containsKey(Customer.getCustomerList().get(y).customerRequestsHistory.get(i))) {
                logger.info("Your Request   :\n"+Admin.toString(Customer.getCustomerList().get(y).customerRequestsHistory.get(i)) + "          Approved by    " + Admin.toString(Installer.getReservedDone().get(Customer.getCustomerList().get(y).customerRequestsHistory.get(i))));
            }
        }
    }

    public static void makeRequest(String predate, String cmodel, Product p, String location) {
        Request r=Customer.getCustomerList().get(y).setRequest(predate, cmodel,p, location);
        r.setStatus("Waiting for Admin response.");
        getCusReq().put(r,Customer.getCustomerList().get(y));

        Customer.getCustomerList().get(y).customerRequestsHistory.add(r);
        logger.info("Your installation request is submitted and waiting for the admin to schedule it. \r");
        EmailSender.sendEmail(Customer.getCustomerList().get(y).getEmail(),"Installation request submission","Your installation request was submitted and on waiting status :)");
    }

    public static void pFound(int i) {
        double total;
        Customer.getCustomerList().get(y).getCard().add(Product.getP().get(i));
        total = Customer.getCustomerList().get(y).getCost();
        total += Product.getP().get(i).getPrice();
        Customer.getCustomerList().get(y).setCost(total);
        logger.info("added to cart successfully !");
    }
    public static void filterProductsbyPrice(int filterChoice) {
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
            //input.nextLine();

        }
    }
    /*private static List<Product> products1;
    public static void submitOrder(Customer customer, List<Product> card) {
        products1=card;
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);

        Order order=new Order(customer,products1,formattedDateTime);
        Order.getOrderList().add(order);
        //card.clear();
        //order.makeOrder(customer,card,formattedDateTime);

    }*/

    public void logging(boolean t) {
        logState=t;
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


    public List<Request> getReqArry() {
        return this.customerRequestsHistory ;
    }
}
