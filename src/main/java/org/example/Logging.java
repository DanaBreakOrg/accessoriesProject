package org.example;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

public class Logging {



    public boolean successfulusername= false;
    public boolean successfulpassword= false;


    public String email;
    public String password;
    public boolean logState = false;
    public static int y;
    int type = -3;

    protected static Scanner input = new Scanner (System.in);
    public static HashMap<String,String> q= new HashMap<>();//how to make it protected
    final Logger logger = Logger.getLogger(Logging.class.getName());


    public int searchEmail(String email1){

        boolean flag = true;

        email = email1;

        for (int i = 0; i < Admin.getAdmin().size(); i++) {
            if (email.equals(Admin.getAdmin().get(i).getEmail())) {
                flag = false;
                successfulusername=true;
                type = 0;
                y=i;
                break;
            }}

        if (flag) {
            for (int j = 0; j < Customer.getCustomerList().size(); j++) {
                if (email.equals(Customer.getCustomerList().get(j).getEmail())) {
                    flag = false;
                    successfulusername=true;
                    type = 1;//////////////////////
                    y=j;
                    break;
                }
            }
        }

        if (flag) {
            for (int k = 0; k < Installer.getInstaller().size(); k++) {
                if (email.equals(Installer.getInstaller().get(k).getEmail())) {
                    flag = false;
                    successfulusername=true;
                    type = 2;
                    y=k;
                    break;
                }
            }
        }


        return type;

    }


    public int searchPassword(String password2){

        password = password2;

        String passw = password;
        String value = q.get(email);

        if (passw.equals(value)) {
            successfulpassword = true;
            //String f=String.format("Welcome:: %s",Customer.getC().get(y).getUsername());
            //logger.info(f);
            logState = true;
            return y;
        }
        else {
            successfulpassword = false;
            return -33;

        }


    }

    /*
    public int login(String username1,String password1) {

        int z = -1;
        int p = -33;

        z = searchUsername(username1);

        while(z == -1){

            logger.info("Please enter your username again : ");
            username1 = input.nextLine();
            z = searchUsername(username1);

        }


        p = searchPassword(password1);

        while(p == -33){
            logger.info("Please enter your password again");
            password1 = input.nextLine();
            p = searchPassword(password1);
        }

        int type = p;
        //index: 0->admin
        //       1->customer
        //       2->installer must enter case statement in main

        return type;


    }*/


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










