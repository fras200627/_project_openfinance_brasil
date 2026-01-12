#!/bin/bash
echo "remove eureka pod"
microk8s kubectl delete -f 03a-nginx-pod-pod.yml
echo "remove eureka nodeport service"
microk8s kubectl delete -f 02-nginx-pod-nodeport.yml
echo "remove eureka ClusterIP Service"
microk8s kubectl delete -f 01-nginx-pod-svc.yml
echo "remove eureka config map"
microk8s kubectl delete -f 00-nginx-pod-cmap.yml

