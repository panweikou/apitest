
## Overview
API Automation framework of project O2O, belong to BDD , based on Java, combination of Cucumber, Allure
## Repository structure
```
├─src
│  └─test
│      ├─java
│      │  └─O2O
│      │      ├─Bean  // Encapsulate javaBean
│      │      │  └─O2O 
│      │      ├─cucumber
│      │      │  └─stepDefinition  // Encapsulate step defination class of cucumber
│      │      ├─repository   // Encapsulate class of initial & temporary data  
│      │      ├─runner  // Running class 
│      │      ├─service  // Encapsulate API of service 
│      │      └─util  // Encapsulate util class
│      └─resources
│          ├─Features  // Store features of cucumber
│          ├─logs // Store logs
│          ├─O2O  // Store test data
│          │  ├─input  // Store input data
│          │  │  ├─address
│          │  │  └─login
│          │  └─output  // Store expected output data
│          │      └─address
│          └─runner  // Store running xml file of testNG 
```
## Feature 
- Test data support Json,CSV file
- Integrated log4j, can see log in console and log file
- Integrated Allure report
- Support multiple environments
- Support parallel running 
- Support softAssert (one step is failed, following steps will run either)

## Run type

### Local run by TestNG
` run E2E_Run.java or e2e.xml `
### local run by Maven
` mvn clean test -Dtest=E2E_Run `
