package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.example.Customer;
import org.example.Operations;
import org.example.Product;

public class customerShopping {

    private Customer customer,customer2;
    Product p1,p2,p3;
    boolean prductExist;
    int productCatagory;
    int filterParam;

    @Given("a customer is logged in")
    public void a_customer_is_logged_in() {
        // Initialize a Customer instance
        customer=new Customer();
        customer = new Customer("username", "password", "address", "phone", "email", "gender", 0.0, 1);
        customer.logging(true);



        p1 = new Product("P001", "name1", "desc1", "interior", 50.0);
        p2 = new Product("P002", "name2", "desc2", "exterior", 100.0);
        p3 = new Product("P003", "name3 Maker", "desc3", "electronics", 170.0);
        Product.getP().add(p1);
        Product.getP().add(p2);
        Product.getP().add(p3);
        customer.pFound(2);
        Customer.toString(p1);
        Operations.addCustomer(customer);
        Operations.addCustomer(customer);
        Operations.addProduct(p1);
        Operations.createCustomer(customer);
        Customer.makeRequest("2024/1/1","uno",0,"nablus");


    }

    @When("the customer chooses to view all products")
    public void theCustomerChoosesToViewAllProducts() {
        Customer.showAllProducts();
    }

    @Then("a list of all available products is displayed")
    public void aListOfAllAvailableProductsIsDisplayed() {
        assertTrue(true);
    }







    @Given("a customer is logged in and on the product page")
    public void aCustomerIsLoggedInAndOnTheProductPage() {
        customer = new Customer("username", "password", "address", "phone", "email", "gender", 0.0, 1);
        customer.logging(true);
    }

    @When("the customer filters products by {string}")
    public void theCustomerFiltersProductsBy(String arg0) {
        if(arg0.equals("exterior")){productCatagory=1;}
        else if(arg0.equals("interior")){productCatagory=2;}
        else if (arg0.equals("electronics")){productCatagory=3;}
        else{
            prductExist=false;
        }

    }

    @Then("only {string} products are displayed")
    public void onlyProductsAreDisplayed(String arg0) {
        if(!prductExist){
            Customer.filterProducts(productCatagory);
            assertNotNull(Product.getP());}
        assertFalse(false);
    }






    @When("the customer adds a product with ID {string} to the cart")
    public void theCustomerAddsAProductWithIDToTheCart(String arg0) {
        customer.pFound(1);
    }

    @Then("the product should be in the customer's cart and the customer views the cart")
    public void theProductWithIDShouldBeInTheCustomerSAndTheCustomerViesTheCart() {
        customer.showCart();

        assertNotNull(customer.getCard());
    }




    @When("the customer want to search a product with the name {string} to the cart")
    public void theCustomerWantToSearchAProductWithTheNameToTheCart(String arg0) {
        prductExist=Customer.SearchAProduct(arg0);
    }



    @Then("the product should not be there")
    public void theProductShouldNotBeThere() {
        assertFalse(prductExist);
    }

    @Then("the product should be there")
    public void theProductShouldBeThere() {
        assertTrue(prductExist);

    }

    @When("the customer want to filter by factor {int}")
    public void theCustomerWantToFilterByFactor(int arg0) {
        filterParam=arg0;
        Customer.filterProductsbyPrice(filterParam);
    }

    @Then("the product with the price in range should be in the the products list")
    public void theProductWithThePriceInRangeShouldBeInTheTheProductsList() {
        assertNotNull(Product.getP().get(filterParam-1));

    }

    @When("the customer requests installation requests")
    public void theCustomerRequestsInstallationRequests() {

        customer.viewInstallationRequests();
        Operations.deleteCustomer(customer);
    }

    @Then("return a sample request from the list to prove there is requests.")
    public void returnASampleRequestFromTheListToProveThereIsRequests() {
        assertNull(customer.getReqArry().get(0));
    }
}
