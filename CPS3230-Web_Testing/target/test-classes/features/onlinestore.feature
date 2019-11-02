Feature: Online Store Functionality

  These scenarios test online store functionality.

  Scenario: Valid Login
    Given I am a user on the website
    When I log in using valid credentials "cps3230test@gmail.com" and "Qwerty123"
    Then I should be logged in "Hi, CPS3230"

  Scenario: Invalid Login
    Given I am a user on the website
    When I log in using invalid credentials "cps3230@gmail.com" and "123456"
    Then I should not be logged in "Login failed"

  Scenario: Product Search
    Given I am a logged in user on the website "cps3230test@gmail.com" and "Qwerty123" and "Hi, CPS3230"
    When I search for a product "Sabaton"
    And I select the first product in the list
    Then I should see the product details

  Scenario: Add product to cart
    Given I am a logged in user on the website "cps3230test@gmail.com" and "Qwerty123" and "Hi, CPS3230"
    And my shopping cart is empty
    When I view the details of a product "Sabaton"
    And I choose to buy the product
    Then my shopping cart should contain 1 item

  Scenario: Add multiple products to cart
    Given I am a logged in user on the website "cps3230test@gmail.com" and "Qwerty123" and "Hi, CPS3230"
    And my shopping cart is empty
    When I add <num-products> products to my shopping cart
    Then my shopping cart should contain <num-products> items
    #Values for num-products: 3,5,10

  Scenario: Removing a product from cart
    Given I am a logged in user on the website "cps3230test@gmail.com" and "Qwerty123" and "Hi, CPS3230"
    And my shopping cart has 2 products
    When I remove the first product in my cart
    Then my shopping cart should contain 1 item