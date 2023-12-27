# spring-microservice-examples

This repository is created for the learners who want to learn microservices development using Spring boot.

I am covering things in different branches. 
Examples are not only limited to basic microservices. I have added databases to give some real ground flavour to the code. Though this is not very high standard code but you will get insight how things happen.

## Note: 
This example is highly inspired by https://www.youtube.com/@Java.Brains Spring microservice tutorials. Here i have taken book example instead of movies. And added couple of things on top of the knowlege gathered by those tutorials. 

## Branch

<b> chapter-1-microservice-using-discovery-server-and-client:
</b>
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

## Note
This repo is in development phase and can have broken code. Though i always try not to push a breaking code to it.