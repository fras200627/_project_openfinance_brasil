#!/bin/bash
#echo "delete ConfigMap Defaults"
#microk8s kubectl delete -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "Remove tican-api-gateway ConfigMap"
microk8s kubectl delete -f 00-api-gateway-cmap.yml
echo "Remove tican-api-gateway PersistentVolumeClaim"
microk8s kubectl delete -f 01-api-gateway-pvc.yml
echo "Remove tican-api-gateway StateFulSet Pods"
microk8s kubectl delete -f 02-api-gateway-statefulset.yml
echo "Remove Eure-Services Internal Service ClusterIP"
microk8s kubectl delete -f 03-api-gateway-clusterip-svc.yml
echo "Remove tican-api-gateway Nodeport"
microk8s kubectl delete -f 04-api-gateway-nodeport.yml

