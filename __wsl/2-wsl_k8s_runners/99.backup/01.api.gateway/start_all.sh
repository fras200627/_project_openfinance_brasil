#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../../2-wsl_k8s_runners/00.config.map.defaults/00-config-map-defaults.yml
echo "create tican-api-gateway ConfigMap"
microk8s kubectl apply -f 00-api-gateway-cmap.yml
echo "Create tican-api-gateway PersistentVolumeClaim"
microk8s kubectl apply -f 01-api-gateway-pvc.yml
echo "Start tican-api-gateway StateFulSet Pods"
microk8s kubectl apply -f 02-api-gateway-statefulset.yml
echo "Create Eure-Services Internal Service ClusterIP"
microk8s kubectl apply -f 03-api-gateway-clusterip-svc.yml
echo "Create tican-api-gateway Nodeport"
microk8s kubectl apply -f 04-api-gateway-nodeport.yml


