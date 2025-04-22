#!/bin/bash
echo "start eureka config map"
microk8s kubectl apply -f 00-tican-eureka-cmap.yml
echo "start eureka deployment"
microk8s kubectl apply -f 03-tican-eureka-deploy.yml
echo "start eureka ClusterIP Service"
microk8s kubectl apply -f 01-tican-eureka-svc.yml
echo "start eureka nodeport service"
microk8s kubectl apply -f 02-tican-eureka-nodeport.yml


