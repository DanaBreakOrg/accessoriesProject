Feature: Customer Profile Management

  Scenario: View Customer Profile
    Given a customer is logged in
    When the customer chooses to view their profile
    Then the customer's profile details are displayed

  Scenario: Update Customer Address
    Given a customer is logged in
    When the customer updates their address to "123 New Street"
    Then the new address "123 New Street" is saved in the customer profile

  Scenario: Update Customer Phone Number
    Given a customer is logged in
    When the customer updates their phone number to "1234567890"
    Then the new phone number "1234567890" is saved in the customer profile

  Scenario: Update Customer Email
    Given a customer is logged in
    When the customer updates their email to "newemail@example.com"
    Then the new email "newemail@example.com" is saved in the customer profile