Feature: admin control operations

  Scenario: printing contents
    Given the admin is signed in and has a collection of installers, products and requests.
    When the admin checks info for a customer, installer and product.
    Then all returns strings containitng each info.




  Scenario: handling customer requests, installer not available
    Given the admin is signed in and has a collection of installers, products and requests.
    When the admin wants to handle customer requests with customer email of "nkjc@gmail.com"
    Then the installer rejects the request due to availablity.

  Scenario: handling customer requests, installer available
    Given the admin is signed in and has a collection of installers, products and requests.
    When the admin wants to handle customer requests with customer email of "nkjc@gmail.com"
    Then the installer accepts the request.






