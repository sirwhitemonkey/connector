#!/bin/bash

if [ "$1" != "" ] && [ "$2" != "" ]; then

 case "$2" in
        start)
        export CLUSTERDNS=$(kubectl get svc kube-dns -n kube-system --template {{.spec.clusterIP}})
        export POSTGRESSVC=postgres.$1.svc.cluster.local

        kubectl create -f kong-system.yaml --namespace=$1
        kubectl patch deployment -n $1 kong -p \
          '{"spec": {"template": {"spec":{"containers":[{"name": "kong", "env":[{"name": "KONG_PG_HOST", "value": '\"$POSTGRESSVC\"'},{"name": "KONG_DNS_RESOLVER", "value": '\"$CLUSTERDNS\"'}]}]}}}}'

        # kong ingress
        kubectl create -f kong-ingress.yaml --namespace=$1

        ;;
        stop)
        kubectl delete -f kong-system.yaml --namespace=$1 --grace-period=0

        # kong ingress
        kubectl delete -f kong-ingress.yaml --namespace=$1 --grace-period=0

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