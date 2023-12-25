Feature: Customer Shopping Experience

  Scenario: View All Products
    Given a customer is logged in
    When the customer chooses to view all products
    Then a list of all available products is displayed

  Scenario: Filter Products by Category
    Given a customer is logged in and on the product page
    When the customer filters products by "interior"
    Then only "Interior Accessories" products are displayed

  Scenario: Filter Products by Category
    Given a customer is logged in and on the product page
    When the customer filters products by "exterior"
    Then only "exterior Accessories" products are displayed


  Scenario: Filter Products by Category
    Given a customer is logged in and on the product page
    When the customer filters products by "electronics"
    Then only "electronics Accessories" products are displayed

  Scenario: Filter Products by Category
    Given a customer is logged in and on the product page
    When the customer filters products by "non existant catagory"
    Then only "nothing, warning msg" products are displayed


  Scenario: Add Product to Cart
    Given a customer is logged in and on the product page
    When the customer adds a product with ID "1001" to the cart
    Then the product should be in the customer's cart and the customer views the cart



  Scenario: search a product and find it
    Given a customer is logged in and on the product page
    When the customer want to search a product with the name "name1" to the cart
    Then the product should be there


  Scenario: search a product and dont find it
    Given a customer is logged in and on the product page
    When the customer want to search a product with the name "name7" to the cart
    Then the product should not be there



  Scenario: filtering products
    Given a customer is logged in and on the product page
    When the customer want to filter by factor 1
    Then the product with the price in range should be in the the products list

  Scenario: filtering products
    Given a customer is logged in and on the product page
    When the customer want to filter by factor 2
    Then the product with the price in range should be in the the products list

  Scenario: filtering products
    Given a customer is logged in and on the product page
    When the customer want to filter by factor 3
    Then the product with the price in range should be in the the products list


  Scenario: view installation requests
    Given a customer is logged in and on the product page
    When the customer requests installation requests
    Then return a sample request from the list to prove there is requests.



