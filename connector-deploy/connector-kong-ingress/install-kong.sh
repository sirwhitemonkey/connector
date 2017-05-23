#!/bin/bash

export CLUSTERDNS=$(kubectl get svc kube-dns -n kube-system --template {{.spec.clusterIP}})

# install a Kong system
kubectl create -f kong.yaml
kubectl patch deployment -n kong-system kong -p \
  '{"spec": {"template": {"spec":{"containers":[{"name": "kong", "env":[{"name": "KONG_DNS_RESOLVER", "value": '\"$CLUSTERDNS\"'}]}]}}}}'

