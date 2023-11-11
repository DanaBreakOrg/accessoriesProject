Feature: Product catalog
  Actor: customer



  Scenario: View Product Details
    Given the user is on the product catalog page
    When the user enters a specifc product number
    Then the product details should appear


