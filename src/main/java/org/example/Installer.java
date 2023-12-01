package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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



    final Logger logger = Logger.getLogger(Installer.class.getName());

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
    public HashMap<String,String> reservaedDates= new HashMap<>();

    public Installer() {
        logState=false;
        pass="worker123";

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

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public static List<Installer> getInstaller() {
        return installer;
    }
    public HashMap<String,String> getReservaeddates() {
        return reservaedDates;
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



}
