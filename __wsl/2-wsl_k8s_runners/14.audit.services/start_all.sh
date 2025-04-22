#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create audit-services ConfigMap"
microk8s kubectl apply -f 00-audit-services-cmap.yml
echo "Create audit-services PersistentVolumeClaim"
microk8s kubectl apply -f 01-audit-services-pvc.yml
echo "Start audit-services StateFulSet Pods"
microk8s kubectl apply -f 02-audit-services-statefulset.yml
#echo "Create Eure-Services Internal Service ClusterIP"
#microk8s kubectl apply -f 03-audit-services-clusterip-svc.yml
#echo "Create audit-services Nodeport"
#microk8s kubectl apply -f 04-audit-services-nodeport.yml


