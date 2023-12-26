package org.example;

import java.util.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Admin {

    boolean logState;
    private String password;
    private int type;
    private String username;
    private String email;


    public static final List<Admin> admin = new ArrayList<>() ;
    public static HashMap<Request,Customer> cusReq= new HashMap<>();//for admin usage,each customer with his requests,make an installation request (customer fills it)
    public static HashMap<Request,Installer> informInstallerr= new HashMap<>();
    final static Logger logger = Logger.getLogger(Admin.class.getName());

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

    public Admin(String email, String name,String password, int type) {
        // super();
        this.password= password;
        this.type = type;
        this.username = name;
        this.email = email;
    }



    public static boolean handleCustomerRequests(String customerEmail) {

        boolean done=false;
        for (int k = 0; k < Customer.getCustomerList().size(); k++) {

            if (Customer.getCustomerList().get(k).getEmail().equals(customerEmail) && cusReq.containsValue(Customer.getCustomerList().get(k))) {//customer found

                //all his requests
                for (Request key : getKeys(cusReq, Customer.getCustomerList().get(k))) {
                    logger.info("Request Info   :\n" + toString(key));
                }

                for (Request key : getKeys(cusReq, Customer.getCustomerList().get(k))) {
                    for (int i = 0; i < Installer.getInstaller().size(); i++) {

                        done = searchAvailable(Installer.getInstaller().get(i), Customer.getCustomerList().get(k), key.preferredDate);

                        if (!done) {
                            //successful finding installer
                            //send to installer
                            key.setStatus("Waiting for Installer response.");
                            Admin.InformInstallerr().put(key, Installer.getInstaller().get(i));//list from admin to installer waiting
                            EmailSender.sendEmail(Installer.getInstaller().get(i).getEmail(), "New installation request", "New installation request was submitted and waiting for your response :\n"
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
        // printAllDates(installer);
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
        return "Installer :     "+i.getEmail()+" \t "+i.getName()+" \t "+i.getPhone()+"\n" ;

    }
    public static String toString(Request r) {


        return  "Car Model      : "+r.carModel+"\n" +
                "Preferred Date : "+r.preferredDate+"\n"+
                "Location       : "+r.location+"\n"+
                "Status         : "+r.getStatus();
    }




    public static HashMap<Request,Installer> InformInstallerr() {return informInstallerr;}
    public static List<Admin> getAdmin() {return admin;}
    public String getPassword() {return password;}
    public void logging(boolean t) { logState=t; }
    public boolean getLogState() { return logState; }
    public String getName() {return username;}
    public String getEmail(){return email;}



}
