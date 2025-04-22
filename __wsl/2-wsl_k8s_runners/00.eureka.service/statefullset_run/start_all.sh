#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../../00.config.map.defaults/00-config-map-defaults.yml
echo "create Eureka-Services ConfigMap"
microk8s kubectl apply -f 00-eureka-services-cmap.yml
echo "Create Eureka-Services PersistentVolumeClaim"
microk8s kubectl apply -f 01-eureka-services-pvc.yml
echo "Start Eureka-Services StateFulSet Pods"
microk8s kubectl apply -f 02-eureka-services-statefulset.yml
echo "Create Eure-Services Internal Service ClusterIP"
microk8s kubectl apply -f 03-eureka-services-clusterip-svc.yml
echo "Create Eureka-Services Nodeport"
microk8s kubectl apply -f 04-eureka-services-nodeport.yml


