#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create db-oracle ConfigMap"
microk8s kubectl apply -f 00-db-oracle-cmap.yml
echo "Create db-oracle PersistentVolumeClaim"
microk8s kubectl apply -f 01-db-oracle-pvc.yml
echo "Start db-oracle StateFulSet Pods"
microk8s kubectl apply -f 02-db-oracle-statefulset.yml
echo "Create Eure-Services Internal Service ClusterIP"
microk8s kubectl apply -f 03-db-oracle-clusterip-svc.yml
echo "Create db-oracle Nodeport"
microk8s kubectl apply -f 04-db-oracle-nodeport.yml


