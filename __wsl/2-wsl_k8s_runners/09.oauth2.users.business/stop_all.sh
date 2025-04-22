#!/bin/bash
#echo "delete ConfigMap Defaults"
#microk8s kubectl delete -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "delete oauth2-users-business ConfigMap"
microk8s kubectl delete -f 00-oauth2-users-business-cmap.yml
#echo "delete oauth2-users-business PersistentVolumeClaim"
#microk8s kubectl delete -f 01-oauth2-users-business-pvc.yml
echo "Start oauth2-users-business StateFulSet Pods"
microk8s kubectl delete -f 02-oauth2-users-business-statefulset.yml
#echo "delete Eure-Services Internal Service ClusterIP"
#microk8s kubectl delete -f 03-oauth2-users-business-clusterip-svc.yml
#echo "delete oauth2-users-business Nodeport"
#microk8s kubectl delete -f 04-oauth2-users-business-nodeport.yml