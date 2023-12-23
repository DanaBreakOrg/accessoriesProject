package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Customer;
import org.example.Logging;

import static org.junit.Assert.*;

public class userProfile {

Logging log;
String oldpass;
String newpass;

Customer c;
    private boolean change;

    public userProfile(){
    log=new Logging();
    log.password="123456";
    c = new Customer("ss","1234567","nablus","0599874562","nkjc@gmail.com","female",0.0,1);
    Customer.getCustomerList().add(c);

}

    @Given("the user is signed in and their old password {string}")
    public void theUserIsSignedInAndTheirOldPassword(String string) {
        log.logState(true);
        oldpass=string;

    }
    @When("the user changes the password to {string}")
    public void theUserChangesThePasswordTo(String string) {
        newpass=string;
    }
    @Then("the password is change successfully")
    public void thePasswordIsChangeSuccessfully() {
        change = oldpass.equals(c.getPassword());
        assertTrue(change);

    }


    @Then("changing password failed")
    public void changingPasswordFailed() {
        change = oldpass.equals(c.getPassword());
        assertFalse(change);
    }


}

