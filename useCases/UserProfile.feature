Feature: user profile operations

    Scenario: password change
      Given the user is signed in and their old password "1234567"
      When the user changes the password to "New pass"
      Then the password is change successfully

      Scenario: password change fails
        Given the user is signed in and their old password "wrong old pass"
        Then  changing password failed
