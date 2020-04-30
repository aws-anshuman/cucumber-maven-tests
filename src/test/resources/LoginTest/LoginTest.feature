Feature: Does the login page work?
    We want the login page to work when the login credentials are valid, and fail otherwise

    Background: A Login Page
        Given I navigate to the login page

    @fail
    Scenario: Login fails
        Given username is bad
        Then log out

    @pass
    Scenario: Login succeeds
        Given username is correct
        Then log out