#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create oauth2-users-business ConfigMap"
microk8s kubectl apply -f 00-oauth2-users-business-cmap.yml
echo "Create oauth2-users-business PersistentVolumeClaim"
microk8s kubectl apply -f 01-oauth2-users-business-pvc.yml
echo "Start oauth2-users-business StateFulSet Pods"
microk8s kubectl apply -f 02-oauth2-users-business-statefulset.yml
#echo "Create Eure-Services Internal Service ClusterIP"
#microk8s kubectl apply -f 03-oauth2-users-business-clusterip-svc.yml
#echo "Create oauth2-users-business Nodeport"
#microk8s kubectl apply -f 04-oauth2-users-business-nodeport.yml


