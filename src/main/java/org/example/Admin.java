package org.example;

import java.util.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.example.Logging.y;

public class Admin {

    public static final  String ENAME="Enter a new username:";
    public static final  String INVALID="Invalid choice. Please try again.";
    boolean logState;
    private String password;
    private int type;
    private String username;
    private String email;



    public static HashMap<Request,Customer> cusReq= new HashMap<>();//for admin usage,each customer with his request,make an installation request (customer fills it)
    protected static final List<Customer> newCustomers = new ArrayList<>() ;
    public static HashMap<Request,Installer> informInstallerr= new HashMap<>();
    protected static Scanner input = new Scanner (System.in);
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


    // lists
    public static final List<Admin> admin = new ArrayList<>() ;
    public static List<Admin> getAdmin() {
        return admin;
    }

    public String getPassword() {
        return password;
    }

    public Admin() {
        logState=false;
    }

    public Admin(String email, String name,String password, int type) {
       // super();
        this.password= password;
        this.type = type;
        this.username = name;
        this.email = email;
    }



    //check if the installer is available or not
    public static void listw ( List<Installer> list,String name) {// I think we need to delete this function
        for(int i=0;i<list.size();i++)
        {   if(list.get(i).getName().equalsIgnoreCase(name)) {
            if(list.get(i).available)
                list.get(i).available=false;
            break;
        }
        }
    }

    //admin features

    public static void adminActivities() {
        boolean running = true;
        while (running) {
            logger.info("\n Welcome Admin "+ Admin.getAdmin().get(y).getName() +" , to the Car accessories company.\r\n"
                    +"------------------------------------------------------------.\r\n"
                    +"Select an option:.\r\n"//add update delete view customers
                    +"1.  Show all customers.\r\n"
                    +"2.  Add a new customer.\r\n"
                    +"3.  Update a customer.\r\n"
                    +"4.  Delete a customer.\r\n"
                    +"5.  Schedule and manage installation appointments.\r\n"//not done
                    +"6.  Add a product.\r\n"
                    +"7.  Delete a product.\r\n"
                    +"8.  Update a product.\r\n"
                    +"9.  Logout.\r\n"
                    +"Enter your choice : ");
            int choice = Main.scanner();

            switch (choice) {
                case 1: {//show all customers done
                    for (int i = 0; i < Customer.getCustomerList().size(); i++) {
                        String ff = String.format("%d-", i + 1);
                        logger.info(ff);
                        logger.info(Customer.getCustomerList().get(i).getUsername() + "   " + Customer.getCustomerList().get(i).getAddress() + "   " + Customer.getCustomerList().get(i).getPhone() + "\r\n");

                    }
                    break;
                }

                case 2: {//add a new customer done
                    Scanner pass = new Scanner(System.in);
                    Scanner pp = new Scanner(System.in);
                    Scanner nn = new Scanner(System.in);
                    Scanner nn2 = new Scanner(System.in);


                    logger.info("Enter customer username : ");
                    String n = pp.nextLine();
                    logger.info("Enter customer Email : ");
                    String s = pass.next();
                    logger.info("Enter customer Gender : ");
                    String k = nn2.nextLine();
                    logger.info("Enter customer Phone number : ");
                    String j = nn.nextLine();
                    logger.info("Enter customer address : ");
                    String h = nn.nextLine();
                    logger.info("Enter customer password : ");
                    String d = nn2.nextLine();
                    Customer customer = new Customer(n, d, h, j, s, k,0.0, 1);
                    boolean add = Operations.addCustomer(customer);
                    if (add)
                        logger.info("A new Customer added");
                    else
                        logger.info("A Customer is already exist");
                    break;
                }

                case 3: {//update customer done
                    Scanner pass1 = new Scanner(System.in);
                    Scanner pp1 = new Scanner(System.in);
                    Scanner nn1 = new Scanner(System.in);
                    Scanner ppp1 = new Scanner(System.in);

                    logger.info("Enter the customer username you want to update:");
                    String z = pass1.next();

                    logger.info("\n Enter the number of the information you want to update: \r\n"
                                    + "------------------------------------------------------------.\r\n"
                                    + "1. Update customer username.\r\n"
                                    + "2. Update customer password.\r\n"
                                    + "3. Update customer Email.\r\n"
                                    + "4. Update customer phone number.\r\n"
                                    + "5. Update customer address.\r\n"
                                    + "6. Back.\r\n"
                            //update type?????????????????????????????
                    );
                    int updateChoice = input.nextInt();
                    boolean update = true;

                    switch (updateChoice) {

                        case 1:
                            logger.info(ENAME);
                            String n1 = pp1.nextLine();
                            update = Operations.updateName(z, n1);
                            break;

                        case 2:
                            logger.info("Enter a new password : ");
                            String d1 = ppp1.nextLine();
                            update = Operations.updatePassword(z, d1);
                            break;

                        case 3:
                            logger.info("Enter a new Email : ");
                            String s1 = pass1.next();
                            update = Operations.updateEmail(z, s1);
                            break;

                        case 4:
                            logger.info("Enter a new Phone number : ");
                            String j1 = nn1.nextLine();
                            update = Operations.updatePhone(z, j1);
                            break;

                        case 5:
                            logger.info("Enter a new address:");
                            String h1 = nn1.nextLine();
                            update = Operations.updateAddress(z, h1);
                            break;


                        case 6: break;

                        default:
                            logger.info(INVALID);
                            input.nextLine();
                            break;

                    }

                    if (!update)
                        logger.info("A Customer was updated");
                    else
                        logger.info("Customer doesn't exist");
                    break;
                }

                case 4: {//delete customer
                    int index = -1;
                    Scanner pass2 = new Scanner(System.in);
                    logger.info("Enter the customer username you want to delete:");//username or smth else??
                    String z1 = pass2.next();
                    for (int i = 0; i < Customer.getCustomerList().size(); i++) {
                        if (Customer.getCustomerList().get(i).getUsername().equals(z1)) {
                            index = i;
                        }
                    }
                    if (index == -1) {
                        logger.info("A customer you want to delete doesn't exist");
                    } else {
                        boolean delete = Operations.deleteCustomer(Customer.getCustomerList().get(index));
                        if (!delete)
                            logger.info("A customer was deleted");
                    }
                    break;
                }

                case 5: {


                    Set<Request> resulttt = new HashSet<>();
                    //print all the requests on hold (need to be sent by admin)=3 2 for same cus
                    for (Map.Entry< Request,Customer> entry : cusReq.entrySet()) {//do we need to know the product?
                        logger.info("Request Info   :\n"+toString(entry.getKey()) + " \n " + "Customer Info   :\n"+toString(entry.getValue()));//////////////
                    }
                    logger.info("\n");
                    logger.info("\n");

                    for (int n = 0; n < Installer.getInstaller().size(); n++) {

                        logger.info(toString(Installer.getInstaller().get(n)));//////woroud only, 3 reser
                        //System.out.println();
                    }
                    Scanner customerEmail = new Scanner(System.in);
                    logger.info("Enter the customer email you want to schedule installation appointment for :");
                    String ce = customerEmail.next();


                    for (int k = 0; k < Customer.getCustomerList().size(); k++) {

                        if (Customer.getCustomerList().get(k).getEmail().equals(ce)&&cusReq.containsValue(Customer.getCustomerList().get(k))){//customer found

                            //all his requests
                                for (Request key : getKeys(cusReq, Customer.getCustomerList().get(k))) {
                                    logger.info("Request Info   :\n"+toString(key));
                                }


                            resulttt=getKeys(cusReq, Customer.getCustomerList().get(k));//all the requests of certain customer

                            for (Request key : getKeys(cusReq, Customer.getCustomerList().get(k))) {
                                for(int i=0; i<Installer.getInstaller().size(); i++){
                                    boolean done;
                                    done=searchAvailable(Installer.getInstaller().get(i),Customer.getCustomerList().get(k),key.preferredDate);

                                    if(!done){
                                        //successful finding installer
                                        //send to installer
                                        key.setStatus("Waiting for Installer response.");
                                        informInstallerr.put(key,Installer.getInstaller().get(i));//list from admin to installer waiting
                                        EmailSender.sendEmail(Installer.getInstaller().get(i).getEmail(),"New installation request","New installation request was submitted and waiting for your response :\n"
                                                + "Customer Info   : "+"\n"+
                                                "Name           : "+Customer.getCustomerList().get(k).getUsername()+"\n" +
                                                "Email          : "+Customer.getCustomerList().get(k).getEmail()+"\n"+
                                                "Phone Number   : "+Customer.getCustomerList().get(k).getPhone()+"\n"+
                                                "Address        : "+Customer.getCustomerList().get(k).getAddress()+"\n"+
                                                "----------------------------------------------------\n"
                                                + "Request Info     : "+"\n"+
                                                "Car Model      : "+key.carModel+"\n" +
                                                "Preferred Date : "+key.preferredDate+"\n"+
                                                "Location       : "+key.location+"\n"+
                                                "----------------------------------------------------\n"
                                                + "Product Info     : "+"\n"+
                                                toString(key.product));




                                        logger.info("Waiting for Installer response.");
                                        break;
                                    }


                                }}



                        }
                    }


                    break;


                }


                case 6: //add a product
                    Customer.showAllProducts();

                    Scanner pId=new Scanner(System.in);
                    Scanner pName=new Scanner(System.in);
                    Scanner pDescription=new Scanner(System.in);
                    Scanner pCategory=new Scanner(System.in);
                    Scanner pPrice=new Scanner(System.in);

                    logger.info("Enter product ID : ");
                    String id = pName.nextLine();
                    logger.info("Enter product name : ");
                    String name = pName.nextLine();
                    logger.info("Enter product description : ");
                    String description = pDescription.nextLine();
                    logger.info("Enter product category (interor/exterior/electronics) : ");
                    String category = pCategory.nextLine();
                    logger.info("Enter product price : ");
                    String price = pPrice.nextLine();
                    double value = Double.parseDouble(price);;
                    Product prod = new Product(id,name, description, category, value);
                    boolean add = Operations.addProduct(prod);
                    if (add)
                        logger.info("A new product is added");
                    else
                        logger.info("This product already exists");

                    break;

                case 7:
                    Customer.showAllProducts();
                    int index = -1;
                    Scanner pid = new Scanner(System.in);
                    logger.info("Enter the product name you want to delete:");
                    String z1 = pid.next();
                    for (int i = 0; i < Customer.getCustomerList().size(); i++) {
                        if (Product.getP().get(i).getName().equals(z1)) {
                            index = i;
                        }
                    }
                    if (index == -1) {
                        logger.info("The product you want to delete doesn't exist");
                    } else {
                        boolean delete = Operations.deleteProduct(Product.getP().get(index));
                        if (!delete)
                            logger.info("The product was deleted");
                    }
                    break;

                case 8:

                    Customer.showAllProducts();
                    Scanner pId1=new Scanner(System.in);
                    Scanner pName1=new Scanner(System.in);
                    Scanner pDescription1=new Scanner(System.in);
                    Scanner pCategory1=new Scanner(System.in);
                    Scanner pPrice1=new Scanner(System.in);

                    logger.info("Enter the product id you want to update:");
                    String z = pId1.next();

                    logger.info("Enter a new name:");
                    String pnamenew = pName1.next();
                    logger.info("Enter a new description:");
                    String pdescnew = pDescription1.nextLine();
                    logger.info("Enter a new category:");
                    String pcatnew = pCategory1.nextLine();
                    logger.info("Enter a new price:");
                    String ppricenew = pPrice1.nextLine();
                    double v = Double.parseDouble(ppricenew);
                    boolean update1 =Operations.updateP(z,z,pnamenew,pdescnew,v,pcatnew);
                    if(!update1) {
                        logger.info("A Product was updated");
                    } else {
                        logger.info("Your message was sent successfully");
                    }

                    break;


                case 9: running=false;
                    break;


                default:
                    logger.info(INVALID);

                    break;
            }
        }

    }


    public void logging(boolean t) { logState=t; }
    public boolean getLogState() { return logState; }
    public String getName() {
        return username;
    }
    public String getEmail(){return email;}



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


    private static String printAllDates(Installer installer) {
        String m="Busy on : \n";

     //   logger.info(installer.getName()+" \t ");

        for (Map.Entry< Customer,String> entry : installer.getReservaeddates().entrySet()) {//do we need to know the product?
            m+=  entry.getValue()+"\n";
           // logger.info(toString(entry.getKey()) + " \n " + entry.getValue());//////////////

        }
return m;
    }
    private static boolean searchAvailable(Installer installer,Customer customer,String date) {
        installer.available=true;
       // printAllDates(installer);
        if(!installer.getReservaeddates().containsValue(date)){
            installer.available=false;
            installer.getReservaeddates().put(customer,date);
        }

       // printAllDates(installer);
     //   System.out.println("availablity state : "+installer.available);
return installer.available;
    }


    public static String toString(Customer c) {
        return "Customer : " +c.getEmail()+" "+c.getUsername()+" "+c.getPhone()+" "+c.getAddress() ;
    }

    public static String toString1(Customer c) {
        return "Customer Info   : " + "\n" +
                "Name           : " + c.getUsername() + "\n" +
                "Email          : " + c.getEmail() + "\n" +
                "Phone Number   : " + c.getPhone() + "\n" +
                "Address        : " + c.getAddress() + "\n" ;

    }

    public static String toString(Product p) {
        return  "ID:            "+p.getId()+"\n"
                +"Name:         "+p.getName()+"\n"
                +"Description:  "+p.getDescription()+"\n"
                +"Price:        "+ p.getPrice()+ "\n";
    }

    public static String toString1(Product p) {
        return  "ID:            "+p.getId()+"\t"
                +"Name:         "+p.getName()+"\t"
                +"Description:  "+p.getDescription()+"\t"
                +"Price:        "+ p.getPrice()+ "\n";
    }


    public static String toStringWithBusyDays(Installer i) {
        return "Installer :     "+i.getEmail()+" \t "+i.getName()+" \t "+i.getPhone()+"\n" +
                "Busy On  :     "+"\n"
                +printAllDates(i);

    }
    public static String toString(Installer i) {
        return "Installer :     "+i.getEmail()+" \t "+i.getName()+" \t "+i.getPhone()+"\n" ;

    }
    public static String toString(Request r) {


        return  "Car Model      : "+r.carModel+"\n" +
                "Preferred Date : "+r.preferredDate+"\n"+
                "Location       : "+r.location+"\n";
    }





}
