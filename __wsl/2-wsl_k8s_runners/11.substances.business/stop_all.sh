#!/bin/bash
#echo "delete ConfigMap Defaults"
#microk8s kubectl delete -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "delete substances-business ConfigMap"
microk8s kubectl delete -f 00-substances-business-cmap.yml
#echo "delete substances-business PersistentVolumeClaim"
#microk8s kubectl delete -f 01-substances-business-pvc.yml
echo "Start substances-business StateFulSet Pods"
microk8s kubectl delete -f 02-substances-business-statefulset.yml
#echo "delete Eure-Services Internal Service ClusterIP"
#microk8s kubectl delete -f 03-substances-business-clusterip-svc.yml
#echo "delete substances-business Nodeport"
#microk8s kubectl delete -f 04-substances-business-nodeport.yml