#!/bin/bash
#echo "delete ConfigMap Defaults"
#microk8s kubectl delete -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "Remove mq-rabbit ConfigMap"
microk8s kubectl delete -f 00-mq-rabbit-cmap.yml
#echo "Remove mq-rabbit PersistentVolumeClaim"
microk8s kubectl delete -f 01-mq-rabbit-pvc.yml
echo "Remove mq-rabbit StateFulSet Pods"
microk8s kubectl delete -f 02-mq-rabbit-statefulset.yml
echo "Remove Eure-Services Internal Service ClusterIP"
microk8s kubectl delete -f 03-mq-rabbit-clusterip-svc.yml
echo "Remove mq-rabbit Nodeport"
microk8s kubectl delete -f 04-mq-rabbit-nodeport.yml

