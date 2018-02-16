#!/bin/bash

#SERVER_IP="192.168.1.127"
SERVER_IP="localhost"

DATA="grant_type=password&client_id=wildlfly-swarm&username=niko&password=niko"

RESULT=`curl -v -X POST --data $DATA http://$SERVER_IP:8080/auth/realms/master/protocol/openid-connect/token`

TOKEN=`echo $RESULT | sed 's/.*access_token":"//g' | sed 's/".*//g'`

echo $TOKEN

curl http://$SERVER_IP:8181/hello -H "Authorization: bearer $TOKEN"
