
@tag
Feature: Error Validation
  I want to use this template for my feature file

 Background: 
 Given I landed on Ecommerce Page
 
  @ErrorValidation
  Scenario Outline: Login Error Validation
    Given Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed 

    Examples: 
      | name             | password  | 
      | wafatl@gmail.com | dghjkloi | 
      