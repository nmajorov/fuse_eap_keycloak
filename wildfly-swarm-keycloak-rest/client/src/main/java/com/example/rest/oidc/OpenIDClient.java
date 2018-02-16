package com.example.rest.oidc;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenID client
 * 
 * @author <a href="nmajorov@redhat.com">Nikolaj Majorov</a>
 *
 */
public class OpenIDClient {

    String url = "";
    String clientID = "";

    protected MultivaluedMap<String, Object> queryParameters = new MultivaluedMapImpl<String, Object>();

    private static final Logger LOG = LoggerFactory.getLogger(OpenIDClient.class);

    public OpenIDClient(String url, String clientID) {
        this.url = url;
        this.clientID = clientID;
        LOG.debug("Create openid client with url:" + url + " clientID: " + this.clientID);
    }

    /**
     * get access token form realm
     * 
     * @param userName
     *            the user login
     * @param password
     *            the user password
     * @return json OpenID token
     */
    public AccessToken getAccessToken(String userName, String password) {

        ResteasyClient client = new ResteasyClientBuilder().build();

        //queryParameters.add("grant_type", "id_token");
        queryParameters.add("grant_type","password");
        queryParameters.add("username", userName);
        queryParameters.add("password", password);
        queryParameters.add("client_id", clientID);
        //queryParameters.add("secret", "7812f8e6-3d9d-4013-a253-9cbbe0b4fb54");

        Response response = client.target(this.url).request()
                .accept(MediaType.APPLICATION_JSON).post(Entity.entity(queryParameters, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        LOG.debug("get accesstoken response status: " + response.getStatus());
        
        AccessToken accessToken = response.readEntity(AccessToken.class);
        response.close();

        return accessToken;
    }
}
