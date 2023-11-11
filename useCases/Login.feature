Feature: User Login

  Scenario: User login successfully
    Given the User is on the login page
    When User enters the valid username "nasser" and enters the valid password "12345"
    Then the User is logged in


  Scenario: User login failed due to invalid password
    Given the User is on the login page
    When User enters correct username "nasser" and enters the password "1234"
    Then Invalid password error message
    And the User is not logged in

  Scenario: User login failed due to invalid username
    Given the User is on the login page
    When User enters the invalid username "dina"
    Then Invalid username error message
    And the User is not logged in

  Scenario: User logs out
    Given that the User is logged in
    When the User logs out
    Then the User is not logged in
