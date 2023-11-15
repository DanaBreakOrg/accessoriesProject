package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
public class Customer {



        boolean logState;
        private String pass;
        final static Logger logger = Logger.getLogger(Customer.class.getName());
        private String username;
        private String address;
        private String phone;
        private String email;
        private String gender;
        private int type;

        boolean addstate;

        protected static final List<Customer> C = new ArrayList<>() ;
        protected static Scanner input = new Scanner (System.in);


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
                    + "1. Exterior Accessories.\r\n"
                    + "2. Interior Accessories.\r\n"
                    + "3. Electronics.\r\n"
                    + "4. Search.\r\n"
                    + "5. Back.\r\n"
            );
            int choice = input.nextInt();

            switch (choice) {

                case 1: logger.info("Add to cart:.\r\n"
                        + "1. .\r\n"
                        + "2. .\r\n"
                        + "3. .\r\n"
                        + "4. .\r\n"
                        + "5. Back.\r\n"
                );


                case 2:logger.info("Add to cart:.\r\n"
                        + "1. .\r\n"
                        + "2. .\r\n"
                        + "3. .\r\n"
                        + "4. .\r\n"
                        + "5. Back.\r\n"
                );



                case 3:logger.info("Add to cart:.\r\n"
                        + "1. .\r\n"
                        + "2. .\r\n"
                        + "3. .\r\n"
                        + "4. .\r\n"
                        + "5. Back.\r\n"
                );



                case 4:


            }
        }}

    public void logging(boolean t) {
            logState=t;
        }
        public boolean getLogState() {
            return logState;
        }


        public Customer(String username,String password, String address, String phone, String email,String Gender,int type) {
            super();
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
