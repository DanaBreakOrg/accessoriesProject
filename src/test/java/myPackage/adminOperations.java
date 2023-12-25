package myPackage;

import java.io.ByteArrayOutputStream;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Admin;
import org.example.Customer;

import static org.junit.Assert.assertNotNull;

public class adminOperations {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
String cusname;
    Admin a= new Admin("nasser@gmail.com","nasser","12345",0);

    @Given("an admin is logged in")
    public void anAdminIsLoggedIn() {
        a.logging(true);
    }
    @When("the admin chooses to view all costumers")
    public void theAdminChoosesToViewAllCostumers() {

    }
    @Then("display a list of all registered customers and their details")
    public void displayAListOfAllRegisteredCustomersAndTheirDetails() {
        Customer.showAllCustomers();
        String output = outputStream.toString();
        assertNotNull(output);
    }


    @When("the admin chooses to delete a customer by {string}")
    public void theAdminChoosesToDeleteACustomerBy(String string) {
        cusname=string;
    }
    @Then("the customer is deleted successfully")
    public void theCustomerIsDeletedSuccessfully() {
        Customer.deleteCustomerUsingUsername(cusname);
    }


}
