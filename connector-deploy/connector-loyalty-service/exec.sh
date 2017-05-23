#!/bin/bash


if [ "$1" != "" ] && [ "$2" != "" ]; then

    case "$2" in
    build)
        docker build --no-cache -t connector-loyalty .
        docker tag connector-loyalty asia.gcr.io/connectors-166702/connector-loyalty
        gcloud docker -- push asia.gcr.io/connectors-166702/connector-loyalty

    ;;
    start)
        export OAUTH2_SERVER_SVC=connector-oauth.$1.svc.cluster.local:2088
        kubectl create -f config.yaml --namespace=$1
                kubectl patch deployment -n $1 connector-loyalty -p \
                          '{"spec": {"template": {"spec":{"containers":[{"name": "connector-loyalty", "env":[{"name": "OAUTH2_SERVER", "value": '\"$OAUTH2_SERVER_SVC\"'}]}]}}}}'

    ;;
    stop)
       kubectl delete -f config.yaml --namespace=$1 --grace-period=0
     ;;
    *)
    echo "Usage: {build|start|stop}"
    ;;
    esac

    echo '*** Deployments ***'
    kubectl get deployments --namespace=$1
    echo '--------------------'
    echo '*** Services ***'
    kubectl get services --namespace=$1
    echo '--------------------'
        echo '*** Pods ***'
    kubectl get pods --namespace=$1

else
	 echo "Error: syntax <namespace> <buld|start|stop>"
fi
