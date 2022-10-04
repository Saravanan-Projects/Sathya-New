@Project_Name__Sathya_Automation
@Tester_Name__Saravanan_A
@Module_Name__Airconditioner
@Sample_1
Feature: Clicking_Each_Product

  Scenario: Clicking_Each_Product

    #Testcase_Name: Entering into application with username and password
    Given I set my testcase id as 'TC_1'
    Given I am Logged into Sathya Application
#    And I enter username and password and log in account
#    Then I verify the username in the homepage

    #Testcase_Name: Clicking of the each product
    Given I set my testcase id as 'TC_2'
    And I navigate from "Home Appliances" to "Air Conditioner" and then to "Split AC"
    And I apply the following filters on the listed products
                  |PRICE| 80,000.00 |
                  |BRAND|   OG, LG |
                  |STAR_RATING|4 Star, 5 Star|
                  |AC_CAPACITY|1 Ton, 1.5 Ton   |
#                  |PRICE_TEXT_BOX| 10000 to 20000 |

    And I get total number of products listed in '1' pages
    And I navigate into each products and get the following details
                        |PRODUCT_NAME|
                        |PRODUCT_PRICE|
                        |PRODUCT_DISCOUNT|
                        |ADD_TO_CART_FACILITY|
                        |BRAND_NAME|
                        |AC_TYPE|
                        |STAR_RATING|
                        |AC_CAPACITY|
    And I write the gathered details into the following file
                  |file_name|file_type|sheet_name|
                  |1_AC_ALL_PRODUCT_XYZ|EXCEL|TESTING_XYZ|
#
    And I read the file as following details
                  |FILE_NAME|1_AC_ALL_PRODUCT_XYZ|
                  |FILE_TYPE|EXCEL|
                  |SHEET_NAME|TESTING_XYZ|
                  |COLUMNS|PRODUCT_NAME|

#    And I enter 'balvin' username for 'SG' entity
#    And I enter 'balvin' username

#    And I read the file as following details
#                  |FILE_NAME|Sample|
#                  |FILE_TYPE|TEXT|
