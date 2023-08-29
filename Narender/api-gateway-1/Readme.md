This API-Gateway uses Eureka server to get the url of other services

How to use this API Gateway to call other services?
1. Run the Eureka server at port 8761
2. Run the application(Microservice) which you want to call
3. Hit the URL: http://localhost:8086/[FISHERMAN-SERVICE]/[fisherman/get/all]
    *  [FISHERMAN-SERVICE] This service registered with Eureka Server
    *  [fisherman/get/all] This is the End point of the service api
