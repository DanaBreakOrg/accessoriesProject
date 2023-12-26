package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Customer;
import org.example.Order;
import org.example.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductOrder {

    private Customer customer;
    private List<Product> products;
    private Order order;

    @Given("a customer exists")
    public void givenACustomerExists() {
        customer = new Customer("John Doe", "123456", "Address", "1234567890", "john@example.com", "Male", 0.0, 1);
    }

    @When("the customer makes an order with products")
    public void whenTheCustomerMakesAnOrderWithProducts() {
        products = new ArrayList<>();
        products.add(new Product("001", "Product 1", "Description 1", "Category", 100.0));
        products.add(new Product("002", "Product 2", "Description 2", "Category", 200.0));
        order = new Order(customer, products, "2023-01-01 12:00:00");
    }

    @Then("the order should be created successfully")
    public void thenTheOrderShouldBeCreatedSuccessfully() {
        Customer customer1=order.getCustomer();

        List<Product> products1=order.getProducts();

        assertNotNull(order);
        assertEquals(customer, order.getCustomer());
        assertEquals(products, order.getProducts());
    }

    @Then("the order details should be retrievable")
    public void thenTheOrderDetailsShouldBeRetrievable() {
        assertNotNull(order.getCustomer());
        assertNotNull(order.getProducts());
    }
}
