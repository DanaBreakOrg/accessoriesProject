/*package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Customer;
import org.example.Logging;
import org.example.Order;
import org.example.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ProductOrder {

    Product product;
    Order order;
    Logging C;
    String IDp , namep , descriptionp ,categoryp ,price;
    Customer c;
    String IDc , namec , address ,phonec,emailc;
    Calendar date1;
    String date2;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public String password;
    public ArrayList<Product> products;



    public ProductOrder() {
        C=new Logging();
        C.password="customer123";
        date1 = Calendar.getInstance();
        date1.add(Calendar.DAY_OF_MONTH, 7);
        date2=  sdf.format(date1.getTime());
        products =new ArrayList<Product>();
    }


    @Given("that the customer with username {string}, Address {string} , Phone {string} , email {string} is logged in")
    public void thatTheCustomerWithUsernameAddressPhoneEmailIsLoggedIn(String string, String string2, String string3, String string4) {
        c = new Customer (string,"147",string2 , string3 ,string4 ,"",0.0,1);
        //C.logState(false);
    }
    @When("the customer selects a  product with ID {string} , Name {string} , Description {string} Category {string} price {string} NIS")
    public void theCustomerSelectsAProductWithIDNameDescriptionCategoryPriceNIS(String string, String string2, String string3, String string4, String string5) {
        double value = Double.parseDouble(string5);
        product= new Product(string,string2,string3,string4,value);
        products.add(product);
    }
    @Then("the system generates an invoice for the customer and updates the order status to {string}")
    public void theSystemGeneratesAnInvoiceForTheCustomerAndUpdatesTheOrderStatusTo(String string) {
        order=new Order(c,products,date2);
        //String s= order.getStatus();
        //assertEqual("waiting",string);
    }

    private void assertEqual(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", but got: " + actual);
        }
    }



}*/
