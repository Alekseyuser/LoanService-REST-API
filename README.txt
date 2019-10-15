# LoanService-REST-API
Application provides REST API. It uses embadded application server and H2 -> Easy to deploy.

#### To run in Windows
1. unzip project
2. open cmd
3. go to pom.xml catalog
4. mvn clean package
5. java -jar target/LoanService-0.0.1-SNAPSHOT.war
-----------------------
#### REST APi cover only business requirements(Not whole CRUD).
Only 3 validations have been implemented:
1. Check does client exist when loan requested.
2. Check number of requests from single IP address per day.
3. Check risk time for loan (00:00-06:00).
When risk is surrounding client receive meaningful rejection message for each type of problem.
----------------------
Client must be created before process loan request.

#### Examples for localhost:

URL: http://localhost:8080/client
JSON REQUEST (POST): {"firstName":"Alex","lastName":"Jakson","passportNumber":"12444","citizenship":"Czech"}

URL:http://localhost:8080/loan
JSON REQUEST(POST): {"transactionTime":"2019-11-21T06:48:00.973","amount":"15552","monthTerm":"222","client":{"firstName":"Alex","lastName":"Jakson","passportNumber":"12444","citizenship":"Czech","clientId":"1"}}

URL(GET): http://localhost:8080/loanhistory/Alex+Jakson+12444

