package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Customer;
import org.example.Operations;
import org.example.Order;
import org.example.Product;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.example.Logging.y;
import static org.junit.Assert.assertNotNull;

public class orderHistory {

    Product product7;


    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Customer c;
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    String formattedDateTime = currentDateTime.format(formatter);

    Order order;



    @When("the customer chooses to view order History")
    public void theCustomerChoosesToViewOrderHistory() {

    }
    @Then("display a list of all the orders this customer made")
    public void displayAListOfAllTheOrdersThisCustomerMade() { // not correct
        c.viewOrderHistory();
        String output = outputStream.toString();
        assertNotNull(output);
    }


    @Given("a customer has made orders")
    public void aCustomerHasMadeOrders() {
        c   =new Customer("shahd","111","QAM","02872228","jixag36030@usoplay.com","Male",0.0);

        product7 = new Product("3100","Roof racks3" ,"pack of 2 car roof rackkvkv", "electronics",300);
        Operations.addProduct(product7);
        c.getCard().add(product7);
        order  =new Order(c,c.getCard(),formattedDateTime);
        c.getCustomerOrders().add(order);

    }
}
