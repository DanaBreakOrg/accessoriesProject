package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import static org.example.Admin.*;
import static org.junit.Assert.*;
import org.example.Customer;
import org.example.Operations;
import org.example.Product;

import java.util.Map;

public class adminControl {

    Admin admin; Installer installer,installer2,tempInstaller; Request request; Customer customer; Product product;
    boolean installerDone,installerAnswer;


    @Given("the admin is signed in and has a collection of installers, products and requests.")
    public void theAdminIsSignedInAndHasACollectionOfInstallersProductsAndRequests() {
        admin=new Admin("test@example.com","admoona","Appleiphone5",0);
        Admin admin2 =new Admin();
        installer=new Installer("testInstaller@gmail.com","Installer1","Appleiphone5","nablus","0543","1313",true,2);
        installer2=new Installer("testInstaller2@gmail.com","Installer2","Appleiphone56","nablus","05434","13134",true,2);
        tempInstaller=new Installer();
        customer = new Customer("ss","1234567","nablus","0599874562","nkjc@gmail.com","female",0.0,1);
        product = new Product("P001", "name1", "desc1", "interior", 50.0);
        Operations.addCustomer(customer);
        Operations.addProduct(product);
        Installer.getInstaller().add(installer);
        Installer.getInstaller().add(installer2);
        Customer.makeRequest("2024-01-01","uno",product,"nablus");
        Customer.makeRequest("2024-01-01","uno",product,"nablus");

        //dum
        String p=installer.getPass();
        boolean log=installer.getLogState();
        String add=installer.getAddress();
        String name=installer.getName();
        String phone=installer.getPhone();
        installer.logging(log);
        installer.setName(name);
        installer.setAddress(add);
        installer.setPhone(phone);



    }

    @When("the admin checks info for a customer, installer and product.")
    public void theAdminChecksInfoForACustomerInstallerAndProduct() {

    }

    @Then("all returns strings containitng each info.")
    public void allReturnsStringsContainitngEachInfo() {

        assertTrue(!Admin.toString(installer).isEmpty()&&!Admin.toString(customer).isEmpty()&&!Admin.toString(product).isEmpty()&&!Admin.toString(customer.getReqArry().get(0)).isEmpty()&&!Installer.toString(customer.getReqArry().get(0)).isEmpty());

    }

    @When("the admin wants to handle customer requests with customer email of {string}")
    public void theAdminWantsToHandleCustomerRequestsWithCustomerEmailOf(String arg0) {

        installerDone=handleCustomerRequests(arg0);
        //     Customer.makeRequest("2024-01-01","uno",product,"nablus");


    }

    @Then("the installer rejects the request due to availablity.")
    public void theInstallerRejectsTheRequestDueToAvailablity() {

        Customer.makeRequest("2024-01-01","uno",product,"nablus");

        for (Map.Entry<Request, Installer> entry : informInstallerr.entrySet()) {

            installerAnswer=Installer.installerAnswer("yes", entry.getKey(),0);
            break;
        }


        for (Request key : Installer.getKeys(informInstallerr, Installer.getInstaller().get(1))) {

            Admin.toString(key);
        }
        Installer.printAllInstallers();
        Installer.viewInsReq();


        assertFalse(installerDone);


    }

    @Then("the installer accepts the request.")
    public void theInstallerAcceptsTheRequest() {
        //   installerAnswer=Installer.installerAnswer("yes",customer.getReqArry().get(0),0);


        Customer.makeRequest("2024-01-01","uno",product,"nablus");

        for (Map.Entry<Request, Installer> entry : informInstallerr.entrySet()) {

            installerAnswer=Installer.installerAnswer("no", entry.getKey(),0);
            break;
        }




        assertTrue(installerDone);

    }
}
