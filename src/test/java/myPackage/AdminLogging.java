package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Admin;
import org.example.Logging;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdminLogging {


    String name;
    Logging log;
    Admin admin;
    public String password;

    public AdminLogging() {
        log=new Logging();
        log.password="123456";
        admin= new Admin(name,"123456",0);
        Admin.getAdmin().add(admin);

    }

    @Given("that the admin is not logged in")
    public void thatTheAdminIsNotLoggedIn() {
        log.logState(false);
    }
    @Given("the password is {string}")
    public void thePasswordIs(String string) {
        name=admin.getName();
        password = string;
    }
    @Then("the login operation succeeds")
    public void theLoginOperationSucceeds() {
        assertTrue(log.login(password));
    }


    @Then("the login operation fails")
    public void theLoginOperationFails() {
        assertFalse(log.login(password));
    }
    @Then("the admin is not logged in")
    public void theAdminIsNotLoggedIn() {
        log.logState(false);
    }



    @Given("that the admin  name {string} is logged in")
    public void thatTheAdminNameIsLoggedIn(String string) {
        name=string;
    }
    @When("the admin logs out")
    public void theAdminLogsOut() {
        log.logout();
    }







}
