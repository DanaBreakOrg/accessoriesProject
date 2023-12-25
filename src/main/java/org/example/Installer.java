package org.example;

import org.main.Main;

import java.util.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.example.Admin.cusReq;
import static org.example.Admin.informInstallerr;
import static org.example.Logging.y;

public class Installer {

    private String name;
    private String email;
    private String address;
    private String phone;
    private String idd;
    boolean available;
    boolean addstate;
    boolean deletestate;
    boolean updatestate;
    @SuppressWarnings("unused")
    private int type;
    boolean logState;
    String pass;

    public static HashMap<Request,Installer> reqq= new HashMap<>();

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

    protected static final List<Installer> installer = new ArrayList<>() ;
    public HashMap<Customer,String> reservaedDates= new HashMap<>();

    public Installer() {
        logState=false;
        pass="worker123";

    }

    public static void viewInsReq() {
        for (Request key : getKeys(informInstallerr, getInstaller().get(y))) {
            logger.info(toString(key));
        }
    }


    public void logging(boolean t) {

        logState=t;
    }
    public boolean getLogState() {

        return logState;
    }


    public Installer(String email,String name,String password, String address, String phone, String iD, boolean available, int type) {
        super();
        this.pass = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.idd = iD;
        this.available = available;
        this.type= type;
        this.email=email;

    }


public static boolean installerAnswer(String x , Request key, int k)
{
    boolean flag=true;
    if(x.equals("yes")||x.equals("Yes")||x.equals("1")) {
        key.setStatus("Approved.");
        EmailSender.sendEmail(cusReq.get(key).getEmail(),"Installation request approval","Your installation request was approved by the installer :)");

        cusReq.remove(key);
        informInstallerr.remove(key);
        reqq.put(key, Installer.getInstaller().get(k));
        //inform customer that the installation was approved


    }

    else if(x.equals("No")||x.equals("no")||x.equals("2")){

        key.setStatus("Waiting for Installer response.");
        informInstallerr.remove(key);
        flag=false;
        return flag;
    }


    else logger.info("Invalid choice. Please try again.");
return flag;

}
    public static String toString(Request r) {
        return "Request Info : " +r.carModel+" "+r.location+" "+r.preferredDate;
    }


    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public static List<Installer> getInstaller() {
        return installer;
    }
    public HashMap<Customer,String> getReservaeddates() {
        return reservaedDates;
    }
    public HashMap<Request,Installer> getReq() {
        return reqq;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getID() {
        return idd;
    }
    public void setID(String iD) {
        idd = iD;
    }

    public static Set<Request> getKeys(Map<Request, Installer> map, Installer value) {

        Set<Request> result = new HashSet<>();
        if (map.containsValue(value)) {
            for (Map.Entry<Request, Installer> entry : map.entrySet()) {
                if (Objects.equals(entry.getValue(), value)) {
                    result.add(entry.getKey());
                }
                // we can't compare like this, null will throws exception
              /*(if (entry.getValue().equals(value)) {
                  result.add(entry.getKey());
              }*/
            }
        }
        return result;

    }

}
