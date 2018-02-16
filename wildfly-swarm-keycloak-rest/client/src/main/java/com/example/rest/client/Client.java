package com.example.rest.client;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.rest.oidc.AccessToken;
import com.example.rest.oidc.OpenIDClient;;

/**
 * simple client for RESTful webservice
 * 
 * @author <a href="mailto:nmajorov@redhat.com">Nikolaj Majorov</a>
 *
 */
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public static final void main(String[] args) throws Exception {
        String result = null;

        String url = System.getProperty("url");
        
        String tockenUrl = System.getProperty("token_url");
        
        final  String CLIENT_ID="wildlfly-swarm";
        
        
        if (url == null || url.trim().equals("")) {
           
            url = "http://localhost:8080/hello";

        }
        
        
        if (tockenUrl ==null ||tockenUrl .trim().equals("")) {
            throw new RuntimeException("token_url system property is not set");
        }
        

        logger.info("service url: " + url);
        logger.info("token url: " + tockenUrl);
        

        OpenIDClient openIDclient = new OpenIDClient(tockenUrl,CLIENT_ID);
        AccessToken accessToken = openIDclient.getAccessToken("niko", "niko");
        String token = accessToken.getAccessToken();
       
        logger.info("got accesss token: " + token);
        // request.header("Authorization", "Bearer " +token);


        
        
        // logger.info("MediaType: " + mediaType.toString());

        // Using the RESTEasy libraries, initiate a client request
        ResteasyClient client = new ResteasyClientBuilder().build();

        // Set url as target
        ResteasyWebTarget target = client.target(url);

        // Be sure to set the mediatype of the request
        target.request(MediaType.TEXT_PLAIN);

        Invocation.Builder request= target.request();

        //set token
        request.header("Authorization","Bearer " + token);

        // Request has been made, now let's get the response
        Response response = request.get();
        result = response.readEntity(String.class);
        response.close();

        // Check the HTTP status of the request
        // HTTP 200 indicates the request is OK
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed request with HTTP status: " + response.getStatus());
        }

        // We have a good response, let's now read it
        logger.info("\n*** Response from Server ***\n");
        logger.info(result);

    }
}
