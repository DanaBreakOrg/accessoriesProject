Feature: order History
  actor: customer
  Background:
    Given a customer has made orders
  Scenario: View order History

    When the customer chooses to view order History
    Then display a list of all the orders this customer made
