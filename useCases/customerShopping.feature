Feature: Customer Shopping Experience

  Scenario: View All Products
    Given a customer is logged in
    When the customer chooses to view all products
    Then a list of all available products is displayed

  Scenario: Filter Products by Category
    Given a customer is logged in and on the product page
    When the customer filters products by "Interior Accessories"
    Then only "Interior Accessories" products are displayed

  Scenario: Add Product to Cart
    Given a customer is logged in and viewing products
    When the customer adds a product with ID "1001" to the cart
    Then the product with ID "1001" should be in the customer's cart

  Scenario: Checkout Cart
    Given a customer has products in their cart
    When the customer chooses to checkout
    Then the purchase is processed