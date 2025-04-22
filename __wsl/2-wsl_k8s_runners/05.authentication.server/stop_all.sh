#!/bin/bash
#echo "delete ConfigMap Defaults"
#microk8s kubectl delete -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "delete auth-server ConfigMap"
microk8s kubectl delete -f 00-auth-server-cmap.yml
#echo "delete auth-server PersistentVolumeClaim"
#microk8s kubectl delete -f 01-oauth2-clients-business-pvc.yml
echo "Start auth-server StateFulSet Pods"
microk8s kubectl delete -f 02-auth-server-statefulset.yml
#echo "delete Eure-Services Internal Service ClusterIP"
#microk8s kubectl delete -f 03-auth-server-clusterip-svc.yml
#echo "delete auth-server Nodeport"
#microk8s kubectl delete -f 04-auth-server-nodeport.yml


