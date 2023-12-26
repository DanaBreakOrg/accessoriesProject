package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Logging {


    public boolean isSuccessfulusername() {
        return successfulusername;
    }

    private boolean successfulusername= false;

    public boolean isSuccessfulpassword() {
        return successfulpassword;
    }

    private boolean successfulpassword= false;



    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    private boolean logState = false;

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        Logging.y = y;
    }

    private static int y;
    int type = -3;

    protected static Map<String,String> q= new HashMap<>();//how to make it protected
    final Logger logger = Logger.getLogger(Logging.class.getName());


    public int searchEmail(String email1){

        boolean flag = true;

        email = email1;

        for (int i = 0; i < Admin.getAdminList().size(); i++) {
            if (email.equals(Admin.getAdminList().get(i).getEmail())) {
                flag = false;
                successfulusername=true;
                type = 0;
                y=i;
                break;
            }}

        if (flag) {
            flag = isCustomer(flag);
        }

        if (flag) {
           isInstaller(flag);
        }


        return type;

    }

    public boolean isInstaller(boolean flag) {

        for (int k = 0; k < Installer.getInstallerList().size(); k++) {
            if (email.equals(Installer.getInstallerList().get(k).getEmail())) {
                flag = false;
                successfulusername=true;
                type = 2;
                y=k;
                break;
            }
        }
        return flag;
    }

    public boolean isCustomer(boolean flag) {
        for (int j = 0; j < Customer.getCustomerList().size(); j++) {
            if (email.equals(Customer.getCustomerList().get(j).getEmail())) {
                flag = false;
                successfulusername=true;
                type = 1;
                y=j;
                break;
            }
        }
        return flag;
    }


    public int searchPassword(String password2){

        password = password2;

        String passw = password;
        String value = q.get(email);

        if (passw.equals(value)) {
            successfulpassword = true;

            logState = true;
            return y;
        }
        else {
            successfulpassword = false;
            return -33;

        }


    }



    public static Map<String,String> getQ(){
        return q;

    }
    public void logState(boolean t) {

        logState=t;
    }

    public boolean login(String password) {

        if(this.password.equals(password)) {
            logger.info("successfully logged in");
            logState=true;
            return true;

        }
        else {
            logger.info("wrong password");
            return false;
        }
    }

    public void setLogState(boolean t) {

        logState=t;
    }
    public boolean getLogState() {
        return logState;
    }
    public void logout() {

        logState = false;
    }


}










