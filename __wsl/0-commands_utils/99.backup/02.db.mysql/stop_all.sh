#!/bin/bash
#echo "delete ConfigMap Defaults"
#microk8s kubectl delete -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "Remove db-mysql ConfigMap"
microk8s kubectl delete -f 00-db-mysql-cmap.yml
#echo "Remove db-mysql PersistentVolumeClaim"
#microk8s kubectl delete -f 01-mq-rabbit-pvc.yml
echo "Remove db-mysql StateFulSet Pods"
microk8s kubectl delete -f 02-db-mysql-statefulset.yml
echo "Remove Eure-Services Internal Service ClusterIP"
microk8s kubectl delete -f 03-db-mysql-clusterip-svc.yml
echo "Remove db-mysql Nodeport"
microk8s kubectl delete -f 04-db-mysql-nodeport.yml

