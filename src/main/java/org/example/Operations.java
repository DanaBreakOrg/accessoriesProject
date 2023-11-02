package org.example;

public class Operations {


    public class Operations {
        private Operations() {

        }

        public static void errorMsg(String errormsgg)
        {
            if(errormsgg=="1") {
                System.out.println("Username can't be empty");
            }
            else if(errormsgg=="2") {
                System.out.println("Password can't be empty");
            }
            else if(errormsgg=="3") {
                System.out.println("this user already exists");
            }
            else if(errormsgg=="invalid email") {
                System.out.println("Invalid email");
            }
            else if(errormsgg=="invalid phone number") {
                System.out.println("Invalid phone number , should be 10 numbers");
            }
            else if(errormsgg=="invalid address") {
                System.out.println("Invalid address, should be only letters");
            }
        }

        public static boolean createC(Customer c) {
            boolean add=true;
            for(int i=0; i< Customer.getC().size() ; i++) {
                if((Customer.getC().get(i).getEmail().equals(c.getEmail())) && (Customer.getC().get(i).getUsername().equals(c.getUsername())) && (Customer.getC().get(i).getAddress().equals(c.getAddress()))&& (Customer.getC().get(i).getPhone().equals(c.getPhone())))
                {
                    add = false;//exists
                    break;
                }
            }
            if(add) {
                Customer.getC().add(c);
                //Statistics.totalw();
            }
            return add;
        }


    }

}
