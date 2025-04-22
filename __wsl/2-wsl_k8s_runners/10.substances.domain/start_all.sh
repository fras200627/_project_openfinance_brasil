#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create substances-domain ConfigMap"
microk8s kubectl apply -f 00-substances-domain-cmap.yml
echo "Create substances-domain PersistentVolumeClaim"
microk8s kubectl apply -f 01-substances-domain-pvc.yml
echo "Start substances-domain StateFulSet Pods"
microk8s kubectl apply -f 02-substances-domain-statefulset.yml
#echo "Create Eure-Services Internal Service ClusterIP"
#microk8s kubectl apply -f 03-substances-business-clusterip-svc.yml
#echo "Create substances-domain Nodeport"
#microk8s kubectl apply -f 04-substances-business-nodeport.yml


