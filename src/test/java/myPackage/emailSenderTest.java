package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.EmailSender;

import static org.junit.Assert.*;

public class emailSenderTest {
    String to,subject,msg;
    @Given("the user is signed in and wants to send an email to {string}")
    public void theUserIsSignedInAndWantsToSendAnEmailTo(String arg0) {
        to=arg0;
    }

    @When("the user send it with subject {string} and msg {string}")
    public void theUserSendItWithSubjectAndMsg(String arg0, String arg1) {
        subject=arg0;
        msg=arg1;
    }

    @Then("email is sent")
    public void emailIsSent() {
        assertTrue(EmailSender.sendEmail(to,subject,msg));
    }
}
