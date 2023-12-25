Feature: user profile operations

  Scenario: password change
    Given the user is signed in and wants to update "password"
    When the user changes to "New pass"
    Then change is successfull




  Scenario: name change
    Given the user is signed in and wants to update "name"
    When the user changes to "New pass"
    Then change is successfull

  Scenario: email change
    Given the user is signed in and wants to update "email"
    When the user changes to "New pass"
    Then change is successfull

  Scenario: address change
    Given the user is signed in and wants to update "address"
    When the user changes to "New pass"
    Then change is successfull

  Scenario: phone change
    Given the user is signed in and wants to update "phone"
    When the user changes to "New pass"
    Then change is successfull



