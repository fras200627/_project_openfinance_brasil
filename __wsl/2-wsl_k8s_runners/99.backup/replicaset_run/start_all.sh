#!/bin/bash
echo "start eureka config map"
microk8s kubectl apply -f 00-tican-discovery-services-cmap.yml
echo "start eureka ClusterIP Service"
microk8s kubectl apply -f 01-tican-discovery-services-svc.yml
echo "start eureka nodeport service"
microk8s kubectl apply -f 02-tican-discovery-services-nodeport.yml
echo "start eureka replicaset"
microk8s kubectl apply -f 03b-tican-discovery-services-rset.yml

