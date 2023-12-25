package org.example;

public class Operations {
// line 190 + 171 need to be checked


    private Operations() {

    }

    public static void errorMsg(String errorMessage)
    {
        if(errorMessage=="1") {
            System.out.println("Username can't be empty");
        }
        else if(errorMessage=="2") {
            System.out.println("Password can't be empty");
        }
        else if(errorMessage=="3") {
            System.out.println("this user already exists");
        }
        else if(errorMessage=="invalid email") {
            System.out.println("Invalid email");
        }
        else if(errorMessage=="invalid phone number") {
            System.out.println("Invalid phone number , should be 10 numbers");
        }
        else if(errorMessage=="invalid address") {
            System.out.println("Invalid address, should be only letters");
        }
    }


    //customer operations

    public static boolean createCustomer(Customer c) {
        boolean add=true;
        for(int i = 0; i< Customer.getCustomerList().size() ; i++) {
            if((Customer.getCustomerList().get(i).getEmail().equals(c.getEmail()))||((Customer.getCustomerList().get(i).getEmail().equals(c.getEmail())) && (Customer.getCustomerList().get(i).getUsername().equals(c.getUsername())) && (Customer.getCustomerList().get(i).getAddress().equals(c.getAddress()))&& (Customer.getCustomerList().get(i).getPhone().equals(c.getPhone()))))
            {
                add = false;//exists
                break;
            }
        }
        if(add) {
            Customer.getCustomerList().add(c);
            //Statistics.totalw();
        }
        return add;
    }

    public static boolean deleteCustomer(Customer c) {
        boolean delete =true;

        int index=-1;
        for(int i = 0; i< Customer.getCustomerList().size() ; i++) {
            if((Customer.getCustomerList().get(i).getEmail().equals(c.getEmail()))||((Customer.getCustomerList().get(i).getEmail().equals(c.getEmail())) && (Customer.getCustomerList().get(i).getUsername().equals(c.getUsername())) && (Customer.getCustomerList().get(i).getAddress().equals(c.getAddress()))&& (Customer.getCustomerList().get(i).getPhone().equals(c.getPhone()))))
            {
                index=i;
                delete = false;
                break;
            }
        }
        if(!delete) {
            Customer.getCustomerList().remove(index);
            //Statistics.totalwo();
        }
        return delete;
    }

    public static boolean addCustomer(Customer c) {
        boolean add=true;
        for(int i = 0; i< Customer.getCustomerList().size() ; i++) {
            if((Customer.getCustomerList().get(i).getEmail().equals(c.getEmail()))||((Customer.getCustomerList().get(i).getEmail().equals(c.getEmail())) && (Customer.getCustomerList().get(i).getUsername().equals(c.getUsername())) && (Customer.getCustomerList().get(i).getAddress().equals(c.getAddress()))&& (Customer.getCustomerList().get(i).getPhone().equals(c.getPhone()))))
            {
                add = false;
                break;
            }
        }
        if(add) {
            Customer.getCustomerList().add(c);
            // Statistics.totalw();///////////////////
        }
        return add;
    }

    public static int searchCustomer(String name) {
        int index=-1;
        for(int i = 0; i< Customer.getCustomerList().size() ; i++) {
            if(name.equals(Customer.getCustomerList().get(i).getUsername())) {
                index=i;
                break;
            }
        }
        return index;
    }



    // update functions

    public static boolean updateName(String oldname,String name) {

        boolean update=true;
        int index;
        index=searchCustomer(oldname);
        if(index>=0) {
            update = false;
            Customer.getCustomerList().get(index).setUsername(name);}
        return update;
    }
    public static boolean updateAddress(String oldname,String address) {

        boolean update=true;
        int index;
        index=searchCustomer(oldname);
        if(index>=0) {
            update = false;
            Customer.getCustomerList().get(index).setAddress(address);}
        return update;
    }
    public static boolean updatePhone(String oldname,String phone) {
        boolean update=true;
        int index;
        index=searchCustomer(oldname);
        if(index>=0) {
            update = false;
            Customer.getCustomerList().get(index).setPhone(phone);}
        return update;

    }
    public static boolean updatePassword(String oldname,String password) {
        boolean update=true;
        int index;
        index=searchCustomer(oldname);
        if(index>=0) {
            update = false;
            Customer.getCustomerList().get(index).setPassword(password);}
        return update;

    }

    public static boolean updateEmail(String oldname,String email) {
        boolean update=true;
        int index;
        index=searchCustomer(oldname);
        if(index>=0) {
            update = false;
            Customer.getCustomerList().get(index).setEmail(email);}
        return update;
    }







    // products operations
    public static boolean addProduct(Product p) {
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
            //Statistics.totalpr();///////////////////////////////////////////////////////////////////
        }
        return add;
    }

    public static boolean deleteProduct(Product p) {
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
            //Statistics.totalpro();////////////////////////////////////////////////////////////////
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
                update = false;//product exists I will update
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


