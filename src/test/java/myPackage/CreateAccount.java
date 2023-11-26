package myPackage;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.example.Operations;
import org.example.Customer;

public class CreateAccount {



    String username,address,phone,email,password,gender;
    Customer c;
    boolean create=true;
    String errornum; //1 blank username 2 blank password 3exists



    @Given("Im in sign-up page")
    public void imInSignUpPage() {
        //idontknow
    }
    @Given("there is a customer with  username {string} , password {string}  , Address {string} , Phone {string} , email {string}, Gender {string}")
    public void thereIsACustomerWithUsernamePasswordAddressPhoneEmailGender(String string, String string2, String string3, String string4, String string5, String string6) {
        username=string;
        address=string3;
        phone=string4;
        email=string5;
        password=string2;
        gender=string6;
    }
    @Then("a customer whose username {string} , password {string}  , Address {string} , Phone {string} , email {string}, Gender {string} was added")
    public void aCustomerWhoseUsernamePasswordAddressPhoneEmailGenderWasAdded(String string, String string2, String string3, String string4, String string5, String string6) {
        c=new Customer(username,password,address,phone,email,gender,0.0,1);
        create=Operations.createC(c);
        assertTrue(create);
    }




    @Then("this customer already exists error message")
    public void thisCustomerAlreadyExistsErrorMessage() {
        errornum="3";
        Operations.errorMsg(errornum);
        assertFalse(!create);
    }



    @Then("username field is empty error message")
    public void usernameFieldIsEmptyErrorMessage() {
        errornum="1";
        Operations.errorMsg(errornum);
        assertFalse(!create);
    }



    @Then("password field is empty error message")
    public void passwordFieldIsEmptyErrorMessage() {
        errornum="2";
        Operations.errorMsg(errornum);
        assertFalse(!create);
    }




    @Given("there is a customer with  username {string} , password {string} , Address {string} , Phone {string} , email {string} , Gender {string}")
    public void thereIsACustomerWithUsernamePasswordAddressPhoneEmailGenderInvalid(String string, String string2, String string3, String string4, String string5, String string6) {
        username=string;
        address=string3;
        phone=string4;
        email=string5;
        password=string2;
        gender=string6;
    }

    @Then("user should see an error {string}")
    public void userShouldSeeAnError(String string) {
        Operations.errorMsg(string);
        assertFalse(!create);

    }



}
