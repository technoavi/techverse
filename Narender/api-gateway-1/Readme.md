This API-Gateway uses Eureka server to get the url of other services

How to use this API Gateway to call other services?
1. Run Eureka server at port 8761
2. Run the application(Microservice) which you want to call
3. Hit the url: http://localhost:8086/<FISHERMAN-SERVICE>/<fisherman/get/all>
    *  <FISHERMAN-SERVICE> This service registered Eureka Server
    *  <fisherman/get/all> This the End point of the application URL
