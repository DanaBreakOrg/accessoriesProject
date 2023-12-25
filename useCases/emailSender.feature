Feature: Email sending

  Scenario: default email
    Given the user is signed in and wants to send an email to "lolmoaker@gmail.com"
    When the user send it with subject "New pass" and msg "msgtest"
    Then email is sent




