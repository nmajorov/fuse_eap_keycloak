Spring WebService Route Deployment Camel EAP example
====================================
This project  deploys Camel routes using the Camel and Spring  Subsystems in EAP.
We use cxf WebServices [Apache CXF Web Services](https://cxf.apache.org/index.html) and disable JBoss EAP WS subsystem.
By enabling CXF servlet in WEB-INF/web.xml will activate spring subsystem to process the camel route.



### Requirements:
 * JBoss Fuse 6.3.0
 * JBoss EAP 6.4.0
 * Maven 3.0 or Greater (http://maven.apache.org/)
 * Java 8

Building
-----------------------
To build the project.

     mvn clean install

This will build the war including the dependencies.

Building and Deploying to JBoss EAP
-----------------------

To start up EAP browse to your EAP install directory. Then run

     /bin/standalone.sh

This will bring up EAP. Once you see logging like this, EAP is up:

     11:08:55,464 INFO  [org.jboss.as] (Controller Boot Thread) JBAS015874: JBoss EAP 6.4.0.GA started in 10870ms - 
     Started 151 of 189 services (56 services are lazy, passive or on-demand)

If you do not already have a user set up for the JBoss Management console you can set one up buy running `$EAP_HOME/bin/add-user.sh` in a separate window. It will walk you through the process. Select 'Management user' when given the option. One this is done and EAP is up, navigate to `http://localhost:9990`  and login with your newly created user. 

### To Deploy your war:

Run command 

     mvn clean install

and copy war file to the 

check web-service location:
[http://localhost:8080/spring-route-deployment-1.0-SNAPSHOT/services/EchoImpl?wsdl](http://localhost:8080/spring-route-deployment-1.0-SNAPSHOT/services/EchoImpl?wsdl)


run simple unsecure  test from project directory (need python and curl installed):

            ./noSecurityEchoServiceTest.sh


