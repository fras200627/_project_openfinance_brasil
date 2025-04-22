#!/bin/bash
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "create mq-rabbit ConfigMap"
microk8s kubectl apply -f 00-mq-rabbit-cmap.yml
echo "Create mq-rabbit PersistentVolumeClaim"
microk8s kubectl apply -f 01-mq-rabbit-pvc.yml
echo "Start mq-rabbit StateFulSet Pods"
microk8s kubectl apply -f 02-mq-rabbit-statefulset.yml
echo "Create Eure-Services Internal Service ClusterIP"
microk8s kubectl apply -f 03-mq-rabbit-clusterip-svc.yml
echo "Create mq-rabbit Nodeport"
microk8s kubectl apply -f 04-mq-rabbit-nodeport.yml


