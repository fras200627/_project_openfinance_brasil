#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create oauth2-users-domain ConfigMap"
microk8s kubectl apply -f 00-oauth2-users-domain-cmap.yml
echo "Create oauth2-users-domain PersistentVolumeClaim"
microk8s kubectl apply -f 01-oauth2-users-domain-pvc.yml
echo "Start oauth2-users-domain StateFulSet Pods"
microk8s kubectl apply -f 02-oauth2-users-domain-statefulset.yml
#echo "Create Eure-Services Internal Service ClusterIP"
#microk8s kubectl apply -f 03-oauth2-users-domain-clusterip-svc.yml
#echo "Create oauth2-users-domain Nodeport"
#microk8s kubectl apply -f 04-oauth2-users-domain-nodeport.yml


