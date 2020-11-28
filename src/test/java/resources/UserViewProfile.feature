Feature:  Users Can View Profile
  Scenario: User Student successfully view his profile
    Given Student with a username "jesus.student"
    When go to profile option and call to api is made
    Then response status is 200
    And all student data is retrieved

  Scenario: User Teacher successfully view his profile
    Given Teacher with a username "albert.teacher"
    When go to profile option and call to api is made
    Then response status is 200
    And all teacher data is retrieved

  Scenario: Admin succesfully retrieve all users
    Given Admin with username "jose.admin"
    When goes to user option and call to api is made
    Then response status of request is 200
    And all users are listed

