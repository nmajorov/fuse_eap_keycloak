package com.example.rest.oidc;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Oauth token representation of JSON request
 * 
 * @author <a href="nmajorov@redhat.com">Nikolaj Majorov</a>
 *
 */

 @JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -686364096756911569L;

    //@JsonProperty("statusCode")
    private int statusCode;

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("refresh_expires_in")
    private int refreshExpiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;


    public int getStatusCode() {
        return statusCode;
    }

    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
  
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getTokenType() {
        return tokenType;
    }
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    public int getExpiresIn() {
        return expiresIn;
    }
    
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
    
    public int getRefreshExpiresIn() {
        return refreshExpiresIn;
    }
    
    public void setRefreshExpiresIn(int refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }
    
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
