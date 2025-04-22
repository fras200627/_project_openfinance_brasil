#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create auth-server ConfigMap"
microk8s kubectl apply -f 00-auth-server-cmap.yml
echo "Create auth-server PersistentVolumeClaim"
microk8s kubectl apply -f 01-auth-server-pvc.yml
echo "Start auth-server StateFulSet Pods"
microk8s kubectl apply -f 02-auth-server-statefulset.yml
#echo "Create Eure-Services Internal Service ClusterIP"
#microk8s kubectl apply -f 03-auth-server-clusterip-svc.yml
#echo "Create auth-server Nodeport"
#microk8s kubectl apply -f 04-auth-server-nodeport.yml


