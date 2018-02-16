#!/bin/sh

docker run -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=Geneva2018!  -p 8080:8080 jboss/keycloak

