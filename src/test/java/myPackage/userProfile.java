package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Customer;
import org.example.Logging;
import org.example.Operations;

import static org.junit.Assert.*;

public class userProfile {

    Logging log;
    String oldParam;
    int paramT0Change;
    String newpass;
    boolean update;

    Customer c;
    private boolean change;

    public userProfile(){
        log=new Logging();
        log.password="123456";
        c = new Customer("ss","1234567","nablus","0599874562","nkjc@gmail.com","female",0.0,1);
        Customer.getCustomerList().add(c);

    }




    @Given("the user is signed in and wants to update {string}")
    public void theUserIsSignedInAndWantsToUpdate(String arg0) {
        c = new Customer("ss","1234567","nablus","0599874562","nkjc@gmail.com","female",0.0,1);
        Customer.getCustomerList().add(c);
        log.logState(true);
        if(arg0.equals("password")){
            paramT0Change=0;
        }
        else if(arg0.equals("email")){
            paramT0Change=1;
        }
        else if(arg0.equals("address")){
            paramT0Change=2;
        }
        else if(arg0.equals("phone")){
            paramT0Change=3;
        }
        else if(arg0.equals("name")){
            paramT0Change=4;
        }


    }

    @When("the user changes to {string}")
    public void theUserChangesTo(String arg0) {
        if(paramT0Change==0){
            update= Operations.updatePassword("ss",arg0);
        }
        else if(paramT0Change==1){
            update= Operations.updateEmail("ss",arg0);
        }
        else if(paramT0Change==2){
            update=  Operations.updateAddress("ss",arg0);

        }
        else if(paramT0Change==3){
            update=  Operations.updatePhone("ss",arg0);

        }
        else if(paramT0Change==4){
            update=  Operations.updateName("ss",arg0);

        }



    }

    @Then("change is successfull")
    public void changeIsSuccessfull() {
        assertFalse(update);
    }


}

