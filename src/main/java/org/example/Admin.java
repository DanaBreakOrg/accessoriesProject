package org.example;

import java.util.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Admin {

    boolean logState;
    private String password;
    private String username;
    private String email;


    protected static final List<Admin> adminList = new ArrayList<>() ;
    protected static Map<Request,Customer> cusReq= new HashMap<>();//for admin usage,each customer with his requests,make an installation request (customer fills it)
    protected static Map<Request,Installer> informInstallerr= new HashMap<>();
    protected static final Logger logger = Logger.getLogger(Admin.class.getName());

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



    public Admin() {logState=false;}

    public Admin(String email, String name,String password) {
        this.password= password;
        this.username = name;
        this.email = email;
    }


/*
    public static boolean handleCustomerRequests(String customerEmail) {
        boolean done=false;
        for (int k = 0; k < Customer.getCustomerList().size(); k++) {

            if (Customer.getCustomerList().get(k).getEmail().equals(customerEmail) && getCusReq().containsValue(Customer.getCustomerList().get(k))) {//customer found


                //all his requests
                for (Request key : getKeys(getCusReq(), Customer.getCustomerList().get(k))) {
                    String logMessage = String.format("Request Info:%n%s", toString(key));
                    logger.info(logMessage);
                }


                for (Request key : getKeys(getCusReq(), Customer.getCustomerList().get(k))) {
                    for (int i = 0; i < Installer.getInstallerList().size(); i++) {

                        done = searchAvailable(Installer.getInstallerList().get(i), Customer.getCustomerList().get(k), key.preferredDate);

                        if (!done) {
                            //successful finding installer
                            //send to installer
                            key.setStatus("Waiting for Installer response.");
                            Admin.informInstallerMethod().put(key, Installer.getInstallerList().get(i));//list from admin to installer waiting
                            sendEmailToInstaller(Installer.getInstallerList().get(i).getEmail(),"New installation request","New installation request was submitted and waiting for your response :\n"
                                    + "Customer Info   : " + "\n" +
                                    "Name           : " + Customer.getCustomerList().get(k).getUsername() + "\n" +
                                    "Email          : " + Customer.getCustomerList().get(k).getEmail() + "\n" +
                                    "Phone Number   : " + Customer.getCustomerList().get(k).getPhone() + "\n" +
                                    "Address        : " + Customer.getCustomerList().get(k).getAddress() + "\n" +
                                    "----------------------------------------------------\n"
                                    + "Request Info     : " + "\n" +
                                    "Car Model      : " + key.carModel + "\n" +
                                    "Preferred Date : " + key.preferredDate + "\n" +
                                    "Location       : " + key.location + "\n" +
                                    "----------------------------------------------------\n"
                                    + "Product Info     : " + "\n" +
                                    toString(key.product));

                            logger.info("Waiting for Installer response.");
                            break;


                        }
                    }
                }
            }
        }
        return done;
    }*/

    public static boolean handleCustomerRequests(String customerEmail) {
        Customer customer = findCustomerByEmail(customerEmail);
        if (customer == null || !getCusReq().containsValue(customer)) {
            return false;
        }

        logCustomerRequests(customer);
        return processRequestsForCustomer(customer);
    }

    private static Customer findCustomerByEmail(String email) {
        for (Customer customer : Customer.getCustomerList()) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    private static void logCustomerRequests(Customer customer) {
        for (Request key : getKeys(getCusReq(), customer)) {
            String logMessage = String.format("Request Info:%n%s", toString(key));
            logger.info(logMessage);
        }
    }

    private static boolean processRequestsForCustomer(Customer customer) {
        boolean done = false;
        for (Request key : getKeys(getCusReq(), customer)) {
            done = processSingleRequest(customer, key);
            if (done) break;
        }
        return done;
    }

    private static boolean processSingleRequest(Customer customer, Request key) {
        for (Installer installer : Installer.getInstallerList()) {
            boolean available = searchAvailable(installer, customer, key.preferredDate);
            if (!available) {
                updateRequestStatus(key,installer);
                sendRequestToInstaller(installer, customer, key);
                logger.info("Waiting for Installer response.");
                return true;
            }
        }
        return false;
    }

    private static void updateRequestStatus(Request key,Installer installer) {
        key.setStatus("Waiting for Installer response.");
        Admin.informInstallerMethod().put(key, installer); // Assuming installer is accessible
    }

    private static void sendRequestToInstaller(Installer installer, Customer customer, Request key) {
        // Construct and send email to the installer

        sendEmailToInstaller(installer.getEmail(),"New installation request","New installation request was submitted and waiting for your response :\n"
                + "Customer Info   : " + "\n" +
                "Name           : " + customer.getUsername() + "\n" +
                "Email          : " + customer.getEmail() + "\n" +
                "Phone Number   : " + customer.getPhone() + "\n" +
                "Address        : " + customer.getAddress() + "\n" +
                "----------------------------------------------------\n"
                + "Request Info     : " + "\n" +
                "Car Model      : " + key.carModel + "\n" +
                "Preferred Date : " + key.preferredDate + "\n" +
                "Location       : " + key.location + "\n" +
                "----------------------------------------------------\n"
                + "Product Info     : " + "\n" +
                toString(key.product));
    }

    private static String buildEmailContent(Customer customer, Request key) {
        // Construct email content here
        return "Email Content"; // Replace with actual content
    }


    private static Set<Request> getKeys(Map<Request, Customer> map, Customer value) {

        Set<Request> result = new HashSet<>();
        if (map.containsValue(value)) {
            for (Map.Entry<Request, Customer> entry : map.entrySet()) {
                if (Objects.equals(entry.getValue(), value)) {
                    result.add(entry.getKey());
                }
            }
        }
        return result;

    }




    private static boolean searchAvailable(Installer installer,Customer customer,String date) {
        installer.available=true;
        if(!installer.getReservaeddates().containsValue(date)){
            installer.available=false;
            installer.getReservaeddates().put(customer,date);
        }

        return installer.available;
    }






    public static String toString(Customer c) {
        return "Customer Info   : " + "\n" +
                "Name           : " + c.getUsername() + "\n" +
                "Email          : " + c.getEmail() + "\n" +
                "Phone Number   : " + c.getPhone() + "\n" +
                "Address        : " + c.getAddress() + "\n" ;

    }



    public static String toString(Product p) {
        return  "ID:            "+p.getId()+"\t"
                +"Name:         "+p.getName()+"\t"
                +"Description:  "+p.getDescription()+"\t"
                +"Price:        "+ p.getPrice()+ "\n";
    }



    public static String toString(Installer i) {
        return "Installer :     "+i.getEmail()+" \t "+i.getName()+" \t "+i.getPhone()+"\n"+i.getIdd()+"\n" ;

    }
    public static String toString(Request r) {


        return  "Car Model      : "+r.carModel+"\n" +
                "Preferred Date : "+r.preferredDate+"\n"+
                "Location       : "+r.location+"\n"+
                "Status         : "+r.getStatus();
    }




    public static Map<Request,Installer> informInstallerMethod() {return informInstallerr;}
    public static Map<Request,Customer> getCusReq() {return cusReq;}
    public static List<Admin> getAdminList() {return adminList;}
    public String getPassword() {return password;}
    public void logging(boolean t) { logState=t; }
    public boolean getLogState() { return logState; }
    public String getName() {return username;}
    public String getEmail(){return email;}


    public static void sendEmailToInstaller(String installerEmail, String subject, String body){
        EmailSender.sendEmail(installerEmail, subject, body);

    }


}
