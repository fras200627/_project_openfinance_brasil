#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create products-domain ConfigMap"
microk8s kubectl apply -f 00-products-domain-cmap.yml
echo "Create products-domain PersistentVolumeClaim"
microk8s kubectl apply -f 01-products-domain-pvc.yml
echo "Start products-domain StateFulSet Pods"
microk8s kubectl apply -f 02-products-domain-statefulset.yml
#echo "Create Eure-Services Internal Service ClusterIP"
#microk8s kubectl apply -f 03-audit-services-clusterip-svc.yml
#echo "Create products-domain Nodeport"
#microk8s kubectl apply -f 04-audit-services-nodeport.yml


