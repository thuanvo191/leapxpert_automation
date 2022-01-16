#######################################################################################################################################
#-------------------------------------------Version & Requirement ----------------------------------------------------------------------
#				Version | 	 Author     |    Date    	|        Description															
#					0.1		|		Thuan Vo  	|	16/01/2022	| Initialization of the feature 									
#######################################################################################################################################
# Challenge_2 : User 1 requests the QR code from the Web Application to allow to log in the Mobile App

@Assignment2
Feature: Automated script using Selenium, feature file, cucumber, java

Scenario Outline: Get QR code from web application
    #Given Using data file "./data/Data-assignment2.json"
    Given Access the web application
    When  Enter the company name as "<companyName>"
    And   Click on Next button 
    And   Enter the Username  as "<user>"
    And   Enter the Password  as "<password>"
    And   Click on Login button
    And   Enter the security code as "<security>"
    Then  Verify Message Homepage display
    When  Click on profile icon to view My profile
    And   Click on Devices on My profile
    And   Click on Link Device 
    Then  Verify Link Device popin display
    And   Get activation code

    Examples: Page titles
        | companyName   | user               | password     | security |
        | auto_testing  | automation_auto_31 | Testing@123  | 111111   |


