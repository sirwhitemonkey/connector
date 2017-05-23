#!/bin/bash

# remove a Kong ingress
kubectl delete -f kong-ingress.yaml --grace-period=0
