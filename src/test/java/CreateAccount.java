import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.example.Operations;
import org.example.Customer;




public class CreateAccount {





    public class CreateAccount {

        String username,address,phone,email,password;
        Customer c;
        boolean create=true;
        String errornum; //1 blank username 2 blank password 3exists







        @Given("Im in sign-up page")
        public void imInSignUpPage() {
            //idontknow
        }
        @Given("there is a customer with  username {string} , password {string}  , Address {string} , Phone {string} , email {string}")
        public void thereIsACustomerWithUsernamePasswordAddressPhoneEmail(String user, String pass, String addr, String phoneNumber, String mail) {
            username=user;
            address=addr;
            phone=phoneNumber;
            email=mail;
            password=pass;
        }
        @Then("a customer whose username {string} , password {string}  , Address {string} , Phone {string} , email {string} was added")
        public void aCustomerWhoseUsernamePasswordAddressPhoneEmailWasAdded(String user, String pass, String address, String phone, String email) {
            c=new Customer(user,pass,address,phone,email);
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




        @Given("there is a customer with  username {string} , password {string} , Address {string} , Phone {string} , email {string}")
        public void thereIsACustomerWithUsernamePasswordAddressPhoneEmailInvalid(String string, String string2, String string3, String string4, String string5) {

        }
        @Then("user should see an error {string}")
        public void userShouldSeeAnError(String errormsg) {
            Operations.errorMsg(errormsg);
            assertFalse(!create);



        }













    }

}
