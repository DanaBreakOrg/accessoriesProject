package org.example;

import java.util.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.example.Admin.getCusReq;
import static org.example.Logging.y;

public class Installer {

    private String name;
    private String email;
    private String address;
    private String phone;

    public String getIdd() {
        return idd;
    }

    private String idd;
    boolean available;
    @SuppressWarnings("unused")

    boolean logState;
    String pass;


    protected static final List<Installer> INSTALLER_LIST = new ArrayList<>() ;
    private Map<Customer,String> reservaedDates= new HashMap<>();

    protected static Map<Request,Installer> reservedDone = new HashMap<>();
    static final Logger logger = Logger.getLogger(Installer.class.getName());



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


    public Installer() {
        logState=false;
        pass="worker123";

    }

    public static void viewInsReq() {
        if(Admin.informInstallerMethod()!= null){
        for (Request key : getKeys(Admin.informInstallerMethod(), getInstallerList().get(y))) {
            logger.info(toString(key));
        }}
    }
    public static void printAllInstallers() {
        for (int n = 0; n < getInstallerList().size(); n++) {
            if(getInstallerList().get(n)!= null) {
                logger.info(Admin.toString(getInstallerList().get(n)));//////woroud only, 3 reser
            }
        }
    }



    public Installer(String email,String name,String password, String address, String phone, String iD, boolean available) {
        super();
        this.pass = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.idd = iD;
        this.available = available;

        this.email=email;

    }


public static boolean installerAnswer(String x , Request key, int k)
{
    boolean flag=true;
    if(x.equals("yes")||x.equals("Yes")||x.equals("1")) {
        key.setStatus("Approved.");
        EmailSender.sendEmail(getCusReq().get(key).getEmail(),"Installation request approval","Your installation request was approved by the installer :)");

        getCusReq().remove(key);
        Admin.informInstallerMethod().remove(key);
        getReservedDone().put(key, Installer.getInstallerList().get(k));
        //inform customer that the installation was approved

    }

    else if(x.equals("No")||x.equals("no")||x.equals("2")){

        key.setStatus("Waiting for Installer response.");
        Admin.informInstallerMethod().remove(key);
        flag=false;
        return flag;
    }

    else logger.info("Invalid choice. Please try again.");

    return flag;

}

    public static Set<Request> getKeys(Map<Request, Installer> map, Installer value) {

        Set<Request> result = new HashSet<>();
        if (map.containsValue(value)) {
            for (Map.Entry<Request, Installer> entry : map.entrySet()) {
                if (Objects.equals(entry.getValue(), value)) {
                    result.add(entry.getKey());
                }
            }
        }
        return result;
    }



    public static String toString(Request r) {
        return "Request Info : " +r.carModel+" "+r.location+" "+r.preferredDate;
    }



    public static List<Installer> getInstallerList() {return INSTALLER_LIST;}
    public Map<Customer,String> getReservaeddates() {return reservaedDates;}
    public static Map<Request,Installer> getReservedDone() {return reservedDone;}



    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }
    public void logging(boolean t) {logState=t;}
    public boolean getLogState() {return logState;}
    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name;}
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


/*
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
*/

/*

    public static void viewInstallationRequests() {
        for(int i = 0; i<Customer.getCustomerList().get(y).customerRequestsHistory.size(); i++) {
            if (Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Waiting for Installer response.") || Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Waiting for Admin response.")) {
                logger.info("Your Request   :\n"+Admin.toString(Customer.getCustomerList().get(y).customerRequestsHistory.get(i)) + "          Waiting\n");
            } else if (Customer.getCustomerList().get(y).customerRequestsHistory.get(i).getStatus().equals("Approved.")&& Installer.getReservedDone().containsKey(Customer.getCustomerList().get(y).customerRequestsHistory.get(i))) {
                logger.info("Your Request   :\n"+Admin.toString(Customer.getCustomerList().get(y).customerRequestsHistory.get(i)) + "          Approved by    " + Admin.toString(Installer.getReservedDone().get(Customer.getCustomerList().get(y).customerRequestsHistory.get(i))));
            }
        }
    }*/

/*
    public static void filterProductsbyPrice(int filterChoice) {
        if (filterChoice == 1) {
            for (int i = 0; i < Product.getP().size(); i++) {
                if ((Product.getP().get(i).getPrice() > 0) && (Product.getP().get(i).getPrice() <= 70)) {
                    logger.info(
                            "ID: " + Product.getP().get(i).getId() + "\t"
                                    + NAME + Product.getP().get(i).getName() + "\t"
                                    + DESCRIPTION + Product.getP().get(i).getDescription() + "\t"
                                    + PRICE + Product.getP().get(i).getPrice() + "\n");
                }
            }
        }
        if (filterChoice == 2) {
            for (int i = 0; i < Product.getP().size(); i++) {
                if ((Product.getP().get(i).getPrice() > 70) && (Product.getP().get(i).getPrice() <= 150)) {
                    logger.info(
                            "ID: " + Product.getP().get(i).getId() + "\t"
                                    + NAME + Product.getP().get(i).getName() + "\t"
                                    + DESCRIPTION + Product.getP().get(i).getDescription() + "\t"
                                    + PRICE + Product.getP().get(i).getPrice() + "\n");
                }
            }
        }
        if (filterChoice == 3) {
            for (int i = 0; i < Product.getP().size(); i++) {
                if ((Product.getP().get(i).getPrice() > 150)) {
                    logger.info(
                            "ID: " + Product.getP().get(i).getId() + "\t"
                                    + NAME + Product.getP().get(i).getName() + "\t"
                                    + DESCRIPTION + Product.getP().get(i).getDescription() + "\t"
                                    + PRICE + Product.getP().get(i).getPrice() + "\n");
                }
            }
        }
        else{
            logger.info("Invalid input, enter your choice again\r");
            //input.nextLine();

        }
    }

*/

}
