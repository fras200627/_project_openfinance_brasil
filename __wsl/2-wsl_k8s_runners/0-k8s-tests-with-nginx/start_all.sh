#!/bin/bash
echo "start eureka config map"
microk8s kubectl apply -f 00-nginx-pod-cmap.yml
echo "start eureka pod"
microk8s kubectl apply -f 03a-nginx-pod-pod.yml
echo "start eureka ClusterIP Service"
microk8s kubectl apply -f 01-nginx-pod-svc.yml
echo "start eureka nodeport service"
microk8s kubectl apply -f 02-nginx-pod-nodeport.yml


