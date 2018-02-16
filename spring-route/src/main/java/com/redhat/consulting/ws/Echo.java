package com.redhat.consulting.ws;


import javax.jws.WebService;

@WebService(targetNamespace="http://ws.consulting.redhat.com") 
public interface Echo {
    String echo(String text);
}
