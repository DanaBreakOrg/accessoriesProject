package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.example.Customer;

public class customerProfile {

    private Customer customer;

    @Given("customer is logged in")
    public void customerIsLoggedIn() {

        customer = new Customer("username", "password", "address", "phone", "email", "gender", 0.0, 1);
        customer.logging(true);

    }


    @When("the customer chooses to view their profile")
    public void the_customer_chooses_to_view_their_profile() {
        // Code to simulate viewing the customer profile
        // This would typically involve retrieving the customer's details
    }

    @Then("the customer's profile details are displayed")
    public void the_customer_profile_details_are_displayed() {
        // Assert that the profile details are not null or as expected
        assertNotNull(customer.getUsername());
        assertNotNull(customer.getAddress());
        assertNotNull(customer.getPhone());
        assertNotNull(customer.getEmail());
    }

    @When("the customer updates their address to {string}")
    public void the_customer_updates_their_address(String newAddress) {
        customer.setAddress(newAddress);
    }

    @Then("the new address {string} is saved in the customer profile")
    public void the_new_address_is_saved_in_the_customer_profile(String expectedAddress) {
        assertEquals(expectedAddress, customer.getAddress());
    }

    @When("the customer updates their phone number to {string}")
    public void the_customer_updates_their_phone_number(String newPhoneNumber) {
        customer.setPhone(newPhoneNumber);
    }

    @Then("the new phone number {string} is saved in the customer profile")
    public void the_new_phone_number_is_saved_in_the_customer_profile(String expectedPhoneNumber) {
        assertEquals(expectedPhoneNumber, customer.getPhone());
    }

    @When("the customer updates their email to {string}")
    public void the_customer_updates_their_email(String newEmail) {
        customer.setEmail(newEmail);
    }

    @Then("the new email {string} is saved in the customer profile")
    public void the_new_email_is_saved_in_the_customer_profile(String expectedEmail) {
        assertEquals(expectedEmail, customer.getEmail());
    }


}