Feature: sample mmock test script
  Background:
    * url 'http://localhost:8083'

  Scenario: test mmock api for gatling performance test
    Given pth 'a/body/form'
    When method post
    Then status 200