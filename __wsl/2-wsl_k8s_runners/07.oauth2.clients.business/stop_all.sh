#!/bin/bash
#echo "delete ConfigMap Defaults"
#microk8s kubectl delete -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "delete clients-business ConfigMap"
microk8s kubectl delete -f 00-oauth2-clients-business-cmap.yml
#echo "delete clients-business PersistentVolumeClaim"
#microk8s kubectl delete -f 01-oauth2-oauth2-clients-business-pvc.yml
echo "Start clients-business StateFulSet Pods"
microk8s kubectl delete -f 02-oauth2-clients-business-statefulset.yml
#echo "delete Eure-Services Internal Service ClusterIP"
#microk8s kubectl delete -f 03-oauth2-clients-business-clusterip-svc.yml
#echo "delete clients-business Nodeport"
#microk8s kubectl delete -f 04-oauth2-clients-business-nodeport.yml


