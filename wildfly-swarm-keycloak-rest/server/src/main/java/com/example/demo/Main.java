package com.example.demo;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import com.example.demo.rest.HelloWorldEndpoint;
import org.wildfly.swarm.keycloak.Secured;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;

/**
 * 
 * main class for the swarm
 * @author Nikolaj Majorov
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Swarm container = new Swarm();  
        JAXRSArchive deployment = ShrinkWrap.create( JAXRSArchive.class);
        deployment.addResource(HelloWorldEndpoint.class);
        deployment.addAllDependencies();
       
        deployment.as(Secured.class)
                .protect()
                .withMethod( "GET" )
                .withRole( "users" );
        container.start().deploy(deployment);
    } 
}

   