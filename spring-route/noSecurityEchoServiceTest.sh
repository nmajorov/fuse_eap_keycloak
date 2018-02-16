#!/usr/bin/env bash

BASEDIR=$(dirname "$0")
echo "$BASEDIR"

curl -X POST -d @"$BASEDIR/src/test/resources/echoSimpleRequest.xml"  \
      -v\
      -H "Content-Type: text/xml;charset=UTF-8" -H "SOAPAction: \"\"" \
    http://localhost:8080/spring-route-deployment-1.0-SNAPSHOT/services/EchoImpl \
     | python -c 'import sys;import xml.dom.minidom;s=sys.stdin.read();print xml.dom.minidom.parseString(s).toprettyxml()'
echo 
echo "Done"


