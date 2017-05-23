#!/bin/bash

if [ "$1" != "" ] && [ "$2" != "" ] ; then

    #connector.service
    curl -i -X DELETE "$2"/apis/connector.service
    upstream_url="upstream_url=http://connector-service.$1.svc.cluster.local:3002"
    curl -i -X POST \
    --url "$2"/apis/ \
    --data 'name=connector.service' \
    --data "$upstream_url" \
    --data 'uris=' \
    --data 'methods=POST,GET,PUT,DELETE,OPTIONS'
    
    #connector.oauth
    curl -i -X DELETE "$2"/apis/connector.oauth
    upstream_url="upstream_url=http://connector-oauth.$1.svc.cluster.local:2088/service"
    curl -i -X POST \
    --url "$2"/apis/ \
    --data 'name=connector.oauth' \
    --data "$upstream_url" \
    --data 'uris=/service' \
    --data 'methods=POST,GET,PUT,DELETE,OPTIONS'
    
    #connector.payment
    curl -i -X DELETE "$2"/apis/connector.payment
    upstream_url="upstream_url=http://connector-payment.$1.svc.cluster.local:2090/payment"
    curl -i -X POST \
    --url "$2"/apis/ \
    --data 'name=connector.payment' \
    --data "$upstream_url" \
    --data 'uris=/payment' \
    --data 'methods=POST,GET,PUT,DELETE,OPTIONS'
    
    #connector.catalogue
    curl -i -X DELETE "$2"/apis/connector.catalogue
    upstream_url="upstream_url=http://connector-catalogue.$1.svc.cluster.local:2092/catalogue"
    curl -i -X POST \
    --url "$2"/apis/ \
    --data 'name=connector.catalogue' \
    --data "$upstream_url" \
    --data 'uris=/catalogue' \
    --data 'methods=POST,GET,PUT,DELETE,OPTIONS'
    
    #connector.correspondence
    curl -i -X DELETE "$2"/apis/connector.correspondence
    upstream_url="upstream_url=http://connector-correspondence.$1.svc.cluster.local:2094/correspondence"
    curl -i -X POST \
    --url "$2"/apis/ \
    --data 'name=connector.correspondence' \
    --data "$upstream_url" \
    --data 'uris=/correspondence' \
    --data 'methods=POST,GET,PUT,DELETE,OPTIONS'
    
    #connector.shipping
    curl -i -X DELETE "$2"/apis/connector.shipping
    upstream_url="upstream_url=http://connector-shipping.$1.svc.cluster.local:2096/shipping"
    curl -i -X POST \
    --url "$2"/apis/ \
    --data 'name=connector.shipping' \
   --data "$upstream_url" \
    --data 'uris=/shipping' \
    --data 'methods=POST,GET,PUT,DELETE,OPTIONS'
    
    #connector.tax
    curl -i -X DELETE "$2"/apis/connector.tax
    upstream_url="upstream_url=http://connector-tax.$1.svc.cluster.local:2098/tax"
    curl -i -X POST \
    --url "$2"/apis/ \
    --data 'name=connector.tax' \
    --data "$upstream_url" \
    --data 'uris=/tax' \
    --data 'methods=POST,GET,PUT,DELETE,OPTIONS'
    
    #connector.loyalty
    curl -i -X DELETE "$2"/apis/connector.loyalty
    upstream_url="upstream_url=http://connector-loyalty.$1.svc.cluster.local:3000/loyalty"
    curl -i -X POST \
    --url "$2"/apis/ \
    --data 'name=connector.loyalty' \
    --data "$upstream_url" \
    --data 'uris=/loyalty' \
    --data 'methods=POST,GET,PUT,DELETE,OPTIONS'
    
    
    #plugins
    curl -i -X POST \
    --url "$2"/plugins/ \
    --data 'name=cors' \
    --data 'config.origins=*' \
    --data 'config.methods=GET,POST,DELETE,PUT' \
    --data 'config.headers=X-Frame-Options,Access-Control-Allow-Origin, Access-Control-Allow-Headers, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Auth-Token, Host' \
    --data 'config.exposed_headers=X-Frame-Options,Access-Control-Allow-Origin, Access-Control-Allow-Headers, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Auth-Token, Host' \
    --data 'config.credentials=true' \
    --data 'config.max_age=3600' \
    --data 'config.preflight_continue=false'
    
    #curl -i -X POST \
    #--url "$2"/plugins/ \
    #--data 'name=oauth2' \
    #--data 'config.enable_authorization_code=true'

    #remove kong-admin

else
     echo "Error: syntax <namespace> <kong-admin>"
fi