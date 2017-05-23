#!/bin/bash


if [ "$1" != "" ] && [ "$2" != "" ]; then

    case "$2" in
    build)
        docker build -t connector-service .
        docker tag connector-service asia.gcr.io/connectors-166702/connector-service
        gcloud docker -- push asia.gcr.io/connectors-166702/connector-service

    ;;
    start)
        kubectl create -f config.yaml --namespace=$1
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

