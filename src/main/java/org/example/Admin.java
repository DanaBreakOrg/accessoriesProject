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


    public static HashMap<Customer,Installer> req= new HashMap<>();
    public static HashMap<Customer,Request> cusReq= new HashMap<>();
    protected static final List<Customer> newCustomers = new ArrayList<>() ;

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
                    for (int i = 0; i < Customer.getC().size(); i++) {
                        String ff = String.format("%d-", i + 1);
                        logger.info(ff);
                        logger.info(Customer.getC().get(i).getUsername() + "   " + Customer.getC().get(i).getAddress() + "   " + Customer.getC().get(i).getPhone() + "\r\n");

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
                    for (int i = 0; i < Customer.getC().size(); i++) {
                        if (Customer.getC().get(i).getUsername().equals(z1)) {
                            index = i;
                        }
                    }
                    if (index == -1) {
                        logger.info("A customer you want to delete doesn't exist");
                    } else {
                        boolean delete = Operations.deleteCustomer(Customer.getC().get(index));
                        if (!delete)
                            logger.info("A customer was deleted");
                    }
                    break;
                }

                case 5:
                    Scanner customerEmail = new Scanner(System.in);
                    logger.info("Enter the customer email you want to schedule installation appointment for :");
                    String ce = customerEmail.next();

                    for (int k = 0; k < Customer.getC().size(); k++) {
                        if (Customer.getC().get(k).getEmail().equals(ce)) {
               //////////////////////////////////////////////////////////       //      searchAvailable
                            break;
                        }
                    }
/*Customer c=new Customer()
c.createrequest(make obj from request...parameters)


                    for (Customer temp : Customer.getC()) {

                        if (.contains(temp)) {

                            // Since present, add it to list3
                            list3.add(temp);
                        }
                    }*/
                    Customer.getC();
                    Installer mn=new Installer("woroud@gmail.com","woroud","123123","RAM","0568725598","122",true,2);
                    //Installer n1=new Installer("ahmad","123","nablus","0568665598","123",true,2);
                    //Installer n2=new Installer("leen","321","SAM","0568722198","124",false,2);

                    Installer.getInstaller().add(mn);
                    //Installer.getInstaller().add(n1);
                    //Installer.getInstaller().add(n2);


                    Customer ii=new Customer("shahod","222","QAM","02872228","shahd@gmail.com","Male",0.0,1);
                    //Customer i2=new Customer("dana","555","DAM","028725323","99","Male",0.0,1);
                    Customer k=new Customer("dana","222","QAM","02872228","dana@gmail.com","Male",0.0,1);

                    Customer.getC().add(ii);
                    Customer.getC().add(k);

                    k.setRequest("24/10/2023","kia");
                    cusReq.put(k,k.setRequest("24/10/2023","kia"));

                    //Customer.getC().add(i2);
                  /*  for (int i = 0; i < Customer.getC().size(); i++) {

                        if(Customer.getC().get(i).onHold){
                            //cusReq.put(Customer.getC().get(i),Request.);
                        }

                    }*/
                    mn.getReservaeddates().put("dana@","24/10/2002");
                    mn.getReservaeddates().put("shahd@","10/12/2002");
                    mn.getReservaeddates().put("leen@","1/1/2002");
                    searchAvailable(mn,ii,"28/1/2002");
                    req.put(ii,mn);
                    req.put(k,mn);


System.out.println(toString(ii,mn));
                    ///n1.getReservaeddates().put(1,"1/11/2023");
                    //n2.getReservaeddates().put(1,"24/10/2021");
                    //n2.getReservaeddates().put(2,"4/3/2021");
                    //printAllDates(mn);
                    //printAllDates(n1);
                    //printAllDates(n2);


                    break;

/*
                    String state;
                    String namee;

                    for(int i = 0; i < Customer.customerDates.size(); i++){
                        //String date=Customer.customerDates.get();
                        System.out.println(Customer.customerDates);



                    }
                    for (int j = 0; i < Installer.getInstaller().size(); j++) {

                        printAllDates(Installer.getInstaller().get(i));
                        String xx=String.format(DEC, i + 1);
                        logger.info(xx);
                        if(Installer.getInstaller().get(i).available)state="Available";
                        else state="Not Available";
                        String fw=String.format(" %s         %s  \r%n" ,Installer.getInstaller().get(i).getName(),state);

                        logger.info(fw);
                    }


                    logger.info("Write a name of Installer you want to give him a job");


                    distributeTasks(HashMap<String,String> q,Installer.getInstaller())
                    Scanner ppp = new Scanner(System.in);
                    namee= ppp.nextLine();


                    boolean xx=Operations.listInstaller(Installer.getInstaller(), namee);
                    if(xx==true) {
                    //operation
                        logger.info("Reservation Done");
                        break;
                    }






                    }*/





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
                    boolean add = Operations.addP(prod);
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
                    for (int i = 0; i < Customer.getC().size(); i++) {
                        if (Product.getP().get(i).getName().equals(z1)) {
                            index = i;
                        }
                    }
                    if (index == -1) {
                        logger.info("The product you want to delete doesn't exist");
                    } else {
                        boolean delete = Operations.deleteP(Product.getP().get(index));
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

                    break;*/



                case 9: running=false;
                    break;




                case 10:
                    System.exit(0);
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



    private static void printAllDates(Installer installer) {
        logger.info(installer.getName()+" \t ");

        for (Map.Entry<String, String> entry : installer.getReservaeddates().entrySet()) {

            logger.info(entry.getKey() + ": " + entry.getValue());
        }
    }
    private static void searchAvailable(Installer installer,Customer customer,String date) {
        installer.available=true;
        printAllDates(installer);
        if(!installer.getReservaeddates().containsValue(date)){
            installer.available=false;
            installer.getReservaeddates().put(customer.getEmail(),date);
        }

        printAllDates(installer);
        System.out.println("availablity state : "+installer.available);

    }
    public HashMap<Customer,Installer> getReq() {
        return req;
    }
    public static List<Customer> getNewCus() {
        return newCustomers;
    }
    //@Override
    public static String toString(Customer c, Installer i) {
        return c.getEmail() + "   +    " + i.getName() ;
    }
}
