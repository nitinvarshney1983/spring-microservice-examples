# spring-microservice-examples

This repository is created for the learners who want to learn microservices development using Spring boot.

I am covering things in different branches. 
Examples are not only limited to basic microservices. I have added databases to give some real ground flavour to the code. Though this is not very high standard code but you will get insight how things happen.

## Note: 
This example is highly inspired by https://www.youtube.com/@Java.Brains Spring microservice tutorials. Here i have taken book example instead of movies. And added couple of things on top of the knowlege gathered by those tutorials. 

## Branch

<h2> chapter-1-microservice-using-discovery-server-and-client:
</h2>
This branch contains:<br>
<h3>techwhisky-Description-Server:</h3> 
This is just a spring boot project enabled with Discovery server. <br>
<ul>
    <li>how discovery-server has been added to the project.</li> 
    <li>Configuration for discovery server in yaml file</li>
    <li> After running check UI on 8761 port</li>
</ul>

<h3>Book-Description-Service:</h3> 
It has crud services to Get/Add/update/Delete Books in mongo. You can check: <br>
<ul>
    <li>how discovery-client has been added to the project.</li> 
    <li>How mongo custom configuration has been done and mongo repository has been used</li>
</ul>

<h3>User-Book-Rating-Service:</h3> 
It has crud services to Get/Add/update/Delete User rating for books in mysql. You can check: <br>
<ul>
    <li>how discovery-client has been added to the project.</li> 
    <li>How mysql releation db has been configured and Spring data jpa has been used</li>
</ul>

<h3>Book-Details-Service:</h3> 
This Service is an aggregator of book-description-service and user-book-rating-service. It internally calls those components microservices and provides aggregated result <br>
<ul>
    <li>how discovery-client has been added to the project.</li> 
</ul>

<h3>Docker-Compose:</h3> 
A docker compose file has been given to run mongo and mysql instances on docker.

<h3>Postman-Collections:</h3>
Postman collection created to hit microservices of different components.

<h2> chapter-2-microservices-fault-tolerance-resilience:
</h2>
In this branch we will implement circut breaker pattern to make our service fault-tolerant and resilience.

Now a days Hystrix has been outdated and resilience4j is used with spring cloud. Spring cloud provides a wrapper on top of resilience4j
called spring-cloud-circut-breaker.

Circut Breaker is a microservice design pattern to make your application fault tolerant.

Case 1: 1 of the microservice got down?
Solution: Running multiple instances of same service. Discovery-server will route to the one of the instance.

Case 2: If external service is super slow?
Solution: 

    a) You can add timeout to your rest client. But it will partially handle the scenerio as after timeout you system will have to handle the exception and need to showcase error to end user. Also if requests are coming faster then your timeout, you will ended up with the same errors.

    b) Depending on the need, you can add Spring Retry using spring-cloud-circutbreaker. @Retry annotation is defined on top of the service. By this way you do not need to configure
    retry count at global level with your rest client. You can individially handle this at microservice level.

    c) If Cache can work(An outdated data can be shown) or some random data can be shown to the user, in that case fallback comes handy. 

    d) You can also define in that when a system will be considered failed and fallback method will be triggered.
    After how long time system will again start hitting actual webservice instead of responding using fallback.In fallback, You can define its own timeout as well.

    
    e) bulkhead is another microservice pattern where you define your thread pool for specific microservice. By that way, only a few worker threads are involved with one service. If that is down, it will not make impact on overall system or another microservice.



## Note
This repo is in development phase and can have broken code. Though i always try not to push a breaking code to it.