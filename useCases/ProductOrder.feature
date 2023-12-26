Feature: Order Management

  Scenario: Create and retrieve an order
    Given a customer exists
    When the customer makes an order with products
    Then the order should be created successfully
    And the order details should be retrievable

