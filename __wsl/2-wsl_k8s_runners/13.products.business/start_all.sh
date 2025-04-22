#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create products-business ConfigMap"
microk8s kubectl apply -f 00-products-business-cmap.yml
echo "Create products-business PersistentVolumeClaim"
microk8s kubectl apply -f 01-products-business-pvc.yml
echo "Start products-business StateFulSet Pods"
microk8s kubectl apply -f 02-products-business-statefulset.yml
#echo "Create Eure-Services Internal Service ClusterIP"
#microk8s kubectl apply -f 03-audit-services-clusterip-svc.yml
#echo "Create products-business Nodeport"
#microk8s kubectl apply -f 04-audit-services-nodeport.yml


