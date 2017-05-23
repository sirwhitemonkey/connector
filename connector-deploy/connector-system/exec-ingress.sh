#!/bin/bash

if [ "$1" != "" ] && [ "$2" != "" ]; then

 case "$2" in
        start)
        # ingress
        kubectl create -f ingress.yaml --namespace=$1
        ;;
        stop)
        # ingress
        kubectl delete -f ingress.yaml --namespace=$1 --grace-period=0
        ;;
        *)
        echo "Usage: {start|stop}"
        ;;
    esac

else
    echo "Error: syntax <namespace> <start|stop>"
fi

echo '*** Deployments ***'
kubectl get deployments --namespace=$1
echo '--------------------'
echo '*** Services ***'
kubectl get services --namespace=$1
echo '--------------------'
echo '*** Pods ***'
kubectl get pods --namespace=$1
echo '--------------------'
echo '*** Ingress ***'
kubectl get ingress --namespace=$1