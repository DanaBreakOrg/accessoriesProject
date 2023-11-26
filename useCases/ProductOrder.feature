Feature: Product Order
  Description: The customer requests makes an order
  Actor: customer

  Scenario: make Order
    Given that the customer with username "ahmad tone", Address "QAM" , Phone "02872228" , email " mohammahd@gmail.com" is logged in
    When the customer selects a  product with ID "1" , Name "car seat" , Description "waterproof car seats" Category "interior" price "50" NIS
    Then the system generates an invoice for the customer and updates the order status to "waiting"