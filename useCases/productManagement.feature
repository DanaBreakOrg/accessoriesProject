Feature: product management (add,delete,update)
  actor: admin

  Background:
    Given a list of product with their state.
      | 1 | car seat     | waterproof car seats    | interior    | 50  |
      | 2 | Car mats     | black rubber car mats   | interior    | 70  |
      | 3 | Roof racks   | pack of 2 car roof rack | exterior    | 100 |
      | 4 | Car chargers | fast car charger        | electronics | 80  |

  Scenario: A product was added succefully
    Given the admin is logged in
    And there is a product with ID "5" , Name "Car blinds" , description "black car blinds",Category "interior", price "80" NIS
    Then a product whose ID "5" , Name "Car blinds" , description "black car blinds",Category "interior", price "80" NIS was added

  Scenario: delete a product
    Given that you want to delete a product whose ID "2" , Name "Car mats" , description "black rubber car mats",Category "interior", price "70" NIS
    And the admin is logged in
    Then product whose ID "2" , Name "Car mats" , description "black rubber car mats",Category "interior", price "70" NIS was deleted


  Scenario: Update a product information
    Given that you want to update a name to  ID "3" , Name "Roof racks" , description "pack of 2 car roof rack",Category "exterior", price "100" NIS
    And the admin is logged in
    When its ID "3"
    Then the information for a product was updated
