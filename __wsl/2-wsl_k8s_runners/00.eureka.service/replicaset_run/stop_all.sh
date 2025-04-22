#!/bin/bash
echo "remove eureka replicaset"
microk8s kubectl delete -f 03b-tican-eureka-rset.yml
echo "remove eureka nodeport service"
microk8s kubectl delete -f 02-tican-eureka-nodeport.yml
echo "remove eureka ClusterIP Service"
microk8s kubectl delete -f 01-tican-eureka-svc.yml
echo "remove eureka config map"
microk8s kubectl delete -f 00-tican-eureka-cmap.yml

