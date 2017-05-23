#!/bin/bash
# remove a Kong Server
kubectl delete -f kong-server.yaml --grace-period=0

