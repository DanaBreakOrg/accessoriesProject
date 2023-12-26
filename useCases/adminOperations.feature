Feature: admin operations
  Background:
    Given an admin is logged in

  Scenario: View All Customers

    When the admin chooses to view all costumers
    Then display a list of all registered customers and their details

  Scenario: Delete a Customer
    When the admin chooses to delete a customer by "shahd"
    Then the customer is deleted successfully
