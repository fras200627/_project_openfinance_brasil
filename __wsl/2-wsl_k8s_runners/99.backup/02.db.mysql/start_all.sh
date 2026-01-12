#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create db-mysql ConfigMap"
microk8s kubectl apply -f 00-db-mysql-cmap.yml
echo "Create db-mysql PersistentVolumeClaim"
microk8s kubectl apply -f 01-db-mysql-pvc.yml
echo "Start db-mysql StateFulSet Pods"
microk8s kubectl apply -f 02-db-mysql-statefulset.yml
echo "Create Eure-Services Internal Service ClusterIP"
microk8s kubectl apply -f 03-db-mysql-clusterip-svc.yml
echo "Create db-mysql Nodeport"
microk8s kubectl apply -f 04-db-mysql-nodeport.yml


