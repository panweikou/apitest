Feature: O2O Address Lifecycle

  Scenario Outline: Address lifecycle management
    Given User login of "<caseID>"
    When Add address of "<caseID>"
    And Query address list
    And Update address
    And Query address detail of "<caseID>"
    And Delete address
    And All steps are successful
    Examples:
    | caseID |
    | o2o_AM_addAddress001 |
    | o2o_AM_addAddress002 |
