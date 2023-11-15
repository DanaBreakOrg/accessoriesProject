package org.example;

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


    //customer operations

    public static boolean createC(Customer c) {
            boolean add=true;
            for(int i=0; i< Customer.getC().size() ; i++) {
                if((Customer.getC().get(i).getUsername().equals(c.getUsername()))||((Customer.getC().get(i).getEmail().equals(c.getEmail())) && (Customer.getC().get(i).getUsername().equals(c.getUsername())) && (Customer.getC().get(i).getAddress().equals(c.getAddress()))&& (Customer.getC().get(i).getPhone().equals(c.getPhone()))))
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


    public static boolean addCustomer(Customer c) {
        boolean add=true;
        for(int i=0; i< Customer.getC().size() ; i++) {
            if((Customer.getC().get(i).getUsername().equals(c.getUsername()))||((Customer.getC().get(i).getEmail().equals(c.getEmail())) && (Customer.getC().get(i).getUsername().equals(c.getUsername())) && (Customer.getC().get(i).getAddress().equals(c.getAddress()))&& (Customer.getC().get(i).getPhone().equals(c.getPhone()))))
            {
                add = false;
                break;
            }
        }
        if(add) {
            Customer.getC().add(c);
           // Statistics.totalw();///////////////////
        }
        return add;
    }

    public static int searchCustomer(String name) {
        int index=-1;
        for(int i=0; i< Customer.getC().size() ; i++) {
            if(name.equals(Customer.getC().get(i).getUsername())) {
                index=i;
                break;
            }
        }
        return index;
    }

    public static boolean updateName(String oldname,String name) {

            boolean update=true;
            int index;
            index=searchCustomer(oldname);
            if(index>=0) {
                update = false;
                Customer.getC().get(index).setUsername(name);}
            return update;
    }
    public static boolean updateAddress(String oldname,String address) {

        boolean update=true;
        int index;
        index=searchCustomer(oldname);
        if(index>=0) {
            update = false;
            Customer.getC().get(index).setAddress(address);}
        return update;
    }
    public static boolean updatePhone(String oldname,String phone) {
        boolean update=true;
        int index;
        index=searchCustomer(oldname);
        if(index>=0) {
            update = false;
            Customer.getC().get(index).setPhone(phone);}
        return update;

    }
    public static boolean updatePassword(String oldname,String password) {
        boolean update=true;
        int index;
        index=searchCustomer(oldname);
        if(index>=0) {
            update = false;
            Customer.getC().get(index).setPassword(password);}
        return update;

    }

    public static boolean updateEmail(String oldname,String email) {
        boolean update=true;
        int index;
        index=searchCustomer(oldname);
        if(index>=0) {
            update = false;
            Customer.getC().get(index).setEmail(email);}
        return update;
    }


    public static boolean deleteCustomer(Customer c) {
        boolean delete =true;

        int index=-1;
        for(int i=0; i< Customer.getC().size() ; i++) {
            if((Customer.getC().get(i).getUsername().equals(c.getUsername()))||((Customer.getC().get(i).getEmail().equals(c.getEmail())) && (Customer.getC().get(i).getUsername().equals(c.getUsername())) && (Customer.getC().get(i).getAddress().equals(c.getAddress()))&& (Customer.getC().get(i).getPhone().equals(c.getPhone()))))
            {
                index=i;
                delete = false;
                break;
            }
        }
        if(!delete) {
            Customer.getC().remove(index);
            //Statistics.totalwo();
        }
        return delete;
    }

    public static boolean addP(Product p) {
        boolean add=true;
        for(int i=0; i< Product.getP().size() ; i++) {
            if((Product.getP().get(i).getId().equals(p.getId())) && (Product.getP().get(i).getName().equals(p.getName())) && (Product.getP().get(i).getDescription().equals(p.getDescription()))&& (Product.getP().get(i).getPrice()==p.getPrice())&& (Product.getP().get(i).getCategory().equals(p.getCategory())))
            {
                add = false;//exists
                break;
            }
        }
        if(add) {
            Product.getP().add(p);
            //Statistics.totalpr();
        }
        return add;
    }

    public static boolean deleteP(Product p) {
        boolean delete =true;

        int index=-1;
        for(int i=0; i< Product.getP().size() ; i++) {
            if((Product.getP().get(i).getId().equals(p.getId())) && (Product.getP().get(i).getName().equals(p.getName())) && (Product.getP().get(i).getDescription().equals(p.getDescription()))&& (Product.getP().get(i).getPrice()==p.getPrice())&& (Product.getP().get(i).getCategory().equals(p.getCategory())))
            {
                index=i;
                delete = false;//deleted
                break;
            }
        }
        if(!delete) {
            Product.getP().remove(index); //delete index
            //Statistics.totalpro();
        }
        return delete;
    }

    public static boolean updateP(String idupdate,String id,String name,String description,double price,String Category) {
        boolean update=true;
        int index=-1;
        for(int i=0; i< Product.getP().size() ; i++) {
            if((Product.getP().get(i).getId().equals(idupdate)))
            {
                index=i;
                update = false;//product exists i will update
                break;
            }
        }
        if(!update) {
            Product.getP().get(index).setId(id);
            Product.getP().get(index).setName(name);
            Product.getP().get(index).setDescription(description);
            Product.getP().get(index).setPrice(price);
            Product.getP().get(index).setCategory(Category);

        }



        return update;
    }




}


