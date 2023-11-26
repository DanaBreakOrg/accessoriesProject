package myPackage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Product;
import org.example.Operations;
import static org.junit.Assert.*;

public class productManagment {

    Product p;

    String ID , Name , Description ,Category ;
    double Price;
    String IDUpdate;
    boolean add=true;
    boolean delete=true;
    boolean update=true;


    @Given("a list of product with their state.")
    public void aListOfProductWithTheirState(DataTable dataTable) {
        String ID , Name , Description ,Category ,Price;

        for(int i=0; i< dataTable.height() ; i++){
            ID = dataTable.cell(i,0);
            Name = dataTable.cell(i,1);
            Description = dataTable.cell(i,2);
            Category = dataTable.cell(i,3);
            Price = dataTable.cell(i,4);
            double value = Double.parseDouble(Price);


            p= new Product (ID,Name,Description,Category,value);
            Product.getP().add(i, p);
        }
    }
    @Given("the admin is logged in")
    public void theAdminIsLoggedIn() {

    }
    @Given("there is a product with ID {string} , Name {string} , description {string},Category {string}, price {string} NIS")
    public void thereIsAProductWithIDNameDescriptionCategoryPriceNIS(String id, String name, String description, String category, String price) {
        ID = id;
        Name=name;
        Description=description;
        Category=category;
        Price=Double.parseDouble(price);

    }
    @Then("a product whose ID {string} , Name {string} , description {string},Category {string}, price {string} NIS was added")
    public void aProductWhoseIDNameDescriptionCategoryPriceNISWasAdded(String id, String name, String description, String category, String price) {
        Price = Double.parseDouble(price);
        p= new Product (id,name,description,category,Price);
        add=Operations.addP(p);
        assertTrue(add);
    }




    @Given("that you want to delete a product whose ID {string} , Name {string} , description {string},Category {string}, price {string} NIS")
    public void thatYouWantToDeleteAProductWhoseIDNameDescriptionCategoryPriceNIS(String id, String name, String description, String category, String price) {
        ID = id;
        Name=name;
        Description=description;
        Category=category;
        Price=Double.parseDouble(price);
    }

    @Then("product whose ID {string} , Name {string} , description {string},Category {string}, price {string} NIS was deleted")
    public void productWhoseIDNameDescriptionCategoryPriceNISWasDeleted(String id, String name, String description, String category, String price) {
        double value = Double.parseDouble(price);
        p=new Product(id,name,description,category,value);
        delete = Operations.deleteP(p);
        assertFalse(delete);
    }




    @Given("that you want to update a name to  ID {string} , Name {string} , description {string},Category {string}, price {string} NIS")
    public void thatYouWantToUpdateANameToIDNameDescriptionCategoryPriceNIS(String id, String name, String description, String category, String price) {
        ID = id;
        Name=name;
        Description=description;
        Category=category;
        Price=Double.parseDouble(price);
    }

    @When("its ID {string}")
    public void itsID(String string) {
        IDUpdate=string;
    }
    @Then("the information for a product was updated")
    public void theInformationForAProductWasUpdated() {

        update=Operations.updateP(IDUpdate,ID,Name,Description,Price,Category);
        assertFalse(update);
    }




}
