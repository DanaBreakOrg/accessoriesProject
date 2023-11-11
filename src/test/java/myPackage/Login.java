package myPackage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.example.Admin.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Admin;
import org.example.Logging;

import java.util.HashMap;
public class Login {

   String username;
   String password;


    Logging user;

    int x;
    Admin a;

    public Login() {
        user = new Logging();
        a= new Admin("nasser","12345",0);
        Admin.getAdmin().add(a);
        user.q.put("nasser", "12345");

    }

    @Given("the User is on the login page")
    public void the_user_is_on_the_login_page() {

        user.setLogState(false);
    }

    @When("User enters the valid username {string} and enters the valid password {string}")
    public void user_enters_the_valid_username_and_enters_the_valid_password(String string, String string2) {

      username = string;
      password = string2;
      int x = user.searchUsername(username);
      int z = user.searchPassword(password);

      System.out.println(x+"   "+z);

    }

    @Then("the User is logged in")
    public void the_user_is_logged_in() {

        System.out.println("username and password are valid");
        System.out.println("successfulusername   "+user.successfulusername);
        System.out.println("successfulpassword   "+user.successfulpassword);

     assertTrue(user.getLogState());
     assertTrue(user.successfulpassword);
     assertTrue(user.successfulusername);


    }

    @When("User enters correct username {string} and enters the password {string}")
    public void user_enters_correct_username_and_enters_the_password(String string, String string2) {


        username = string;
        password = string2;
        int x = user.searchUsername(username);
        int z = user.searchPassword(password);
        System.out.println(x+"   "+z);
    }

    @Then("Invalid password error message")
    public void invalid_password_error_message() {

        System.out.println("Wrong password case");
        System.out.println("successfulusername   "+user.successfulusername);
        System.out.println("successfulpassword   "+user.successfulpassword);

        assertTrue(user.successfulusername);
        assertFalse(user.successfulpassword);
    }

    @Then("the User is not logged in")
    public void the_user_is_not_logged_in() {

        assertFalse(user.getLogState());
    }

    @When("User enters the invalid username {string}")
    public void user_enters_the_invalid_username(String string) {

        username = string;
        password = "1";
        int x = user.searchUsername(username);
        int z = user.searchPassword(password);
        System.out.println(x+"   "+z);

    }

    @Then("Invalid username error message")
    public void invalid_username_error_message() {

        System.out.println("Wrong username case");
        System.out.println("successfulusername   "+user.successfulusername);
        System.out.println("successfulpassword   "+user.successfulpassword);
        assertFalse(user.successfulusername);
    }

    @Given("that the User is logged in")
    public void that_the_user_is_logged_in() {

        user.setLogState(true);
    }

    @When("the User logs out")
    public void the_user_logs_out() {

        user.logout();
    }




}
