package org.example;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

public class Logging {

    protected static Scanner input = new Scanner (System.in);

    public static HashMap<String,String> q= new HashMap<>();//how to make it protected

    public boolean successfulusername= false;
    public boolean successfulpassword= false;


    public String username;
    public String password;
    public boolean logState = false;
    protected static int y;
    int index = -3;
    final Logger logger = Logger.getLogger(Logging.class.getName());

    public int searchUsername(String username1){

        boolean flag = true;

        username = username1;

        for (int i = 0; i < Admin.getAdmin().size(); i++) {
            if (username.equals(Admin.getAdmin().get(i).getName())) {
                flag = false;
                successfulusername=true;
                index = 0;
                break;
            }}

        if (flag) {
            for (int j = 0; j < Customer.getC().size(); j++) {
                if (username.equals(Customer.getC().get(j).getUsername())) {
                    flag = false;
                    successfulusername=true;
                    index = 1;
                    y=j;//////what is the usage????????
                    break;
                }
            }
        }

        if (flag) {
            for (int k = 0; k < Installer.getW().size(); k++) {
                if (username.equals(Installer.getW().get(k).getName())) {
                    flag = false;
                    successfulusername=true;
                    index = 2;
                    break;
                }
            }
        }


        return index;

    }


    public int searchPassword(String password2){

        password = password2;

        String passw = password;
        String value = q.get(username);

            if (passw.equals(value)) {
                successfulpassword = true;
                String f=String.format("Welcome:: %s",username);
                logger.info(f);
                logState = true;
                return index;
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
            logger.info("successfull log in");
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










