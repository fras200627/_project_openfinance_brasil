#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create substances-business ConfigMap"
microk8s kubectl apply -f 00-substances-business-cmap.yml
echo "Create substances-business PersistentVolumeClaim"
microk8s kubectl apply -f 01-substances-business-pvc.yml
echo "Start substances-business StateFulSet Pods"
microk8s kubectl apply -f 02-substances-business-statefulset.yml
#echo "Create Eure-Services Internal Service ClusterIP"
#microk8s kubectl apply -f 03-substances-business-clusterip-svc.yml
#echo "Create substances-business Nodeport"
#microk8s kubectl apply -f 04-substances-business-nodeport.yml


