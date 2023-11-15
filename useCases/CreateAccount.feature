Feature: User Registration
  description: a customr wants to create a new account

  Scenario: user created successfully
    Given Im in sign-up page
    And there is a customer with  username "shahd23" , password "12345"  , Address "Nablus" , Phone "0598741963" , email "shahd@gmail.com", Gender "Female"
    Then a customer whose username "shahd23" , password "12345"  , Address "Nablus" , Phone "0598741963" , email "shahd@gmail.com", Gender "Female" was added

  Scenario: user already exists
    Given Im in sign-up page
    And there is a customer with  username "shahd23" , password "12345"  , Address "Nablus" , Phone "0598741963" , email "shahd@gmail.com", Gender "Female"
    Then this customer already exists error message

  Scenario: username field is blank
    Given Im in sign-up page
    And there is a customer with  username "" , password "12345"  , Address "Nablus" , Phone "0598741963" , email "shahd@gmail.com", Gender "Female"
    Then username field is empty error message

  Scenario: password field is blank
    Given Im in sign-up page
    And there is a customer with  username "shahd23" , password ""  , Address "Nablus" , Phone "0598741963" , email "shahd@gmail.com", Gender "Female"
    Then password field is empty error message

  Scenario Outline: Invalid input
    Given Im in sign-up page
    And there is a customer with  username '<username>' , password '<password>' , Address '<address>' , Phone '<phone>' , email '<email>' , Gender '<gender>'
    Then user should see an error '<message>'

    Examples:
      |username|password|address|phone     |email           |gender|message|
      |shahd23 |12345   |Nablus |0599874563|shahd           |Female|invalid email|
      |shahd23 |12345   |Nablus |059987    |shahd@gmail.com |Female|invalid phone number|
      |shahd23 |12345   |7412   |0599874563|shahd@gmail.com |Female|invalid address|

