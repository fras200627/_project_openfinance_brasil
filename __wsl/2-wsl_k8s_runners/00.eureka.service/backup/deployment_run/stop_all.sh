#!/bin/bash
echo "remove eureka deployment"
microk8s kubectl delete -f 03-tican-discovery-services-deploy.yml
echo "remove eureka ClusterIP Service"
microk8s kubectl delete -f 01-tican-discovery-services-svc.yml
echo "remove eureka nodeport service"
microk8s kubectl delete -f 02-tican-discovery-services-nodeport.yml
echo "remove eureka config map"
microk8s kubectl delete -f 00-tican-discovery-services-cmap.yml

