#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../../00.config.map.defaults/00-config-map-defaults.yml
echo "start eureka config map"
microk8s kubectl apply -f 00-tican-discovery-services-cmap.yml
echo "start eureka pod"
microk8s kubectl apply -f 03a-tican-discovery-services-pod.yml
echo "start eureka ClusterIP Service"
microk8s kubectl apply -f 01-tican-discovery-services-svc.yml
echo "start eureka nodeport service"
microk8s kubectl apply -f 02-tican-discovery-services-nodeport.yml


