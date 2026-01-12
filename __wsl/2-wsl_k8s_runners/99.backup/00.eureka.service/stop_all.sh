#!/bin/bash
#echo "Remove ConfigMap Defults"
#microk8s kubectl delete -f ../../00.config.map.defaults/00-config-map-defaults.yml
echo "Remove Eureka-Services ConfigMap"
microk8s kubectl delete -f 00-eureka-services-cmap.yml
#echo "Remove Eureka-Services PersistentVolumeClaim"
#microk8s kubectl delete -f 01-tican-run-pvc.yml
echo "Remove Eureka-Services StateFulSet Pods"
microk8s kubectl delete -f 02-eureka-services-statefulset.yml
echo "Remove Eure-Services Internal Service ClusterIP"
microk8s kubectl delete -f 03-eureka-services-clusterip-svc.yml
echo "Remove Eureka-Services Nodeport"
microk8s kubectl delete -f 04-eureka-services-nodeport.yml

