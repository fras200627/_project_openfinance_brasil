#!/bin/bash
#echo "delete ConfigMap Defaults"
#microk8s kubectl delete -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "delete substances-domain ConfigMap"
microk8s kubectl delete -f 00-substances-domain-cmap.yml
#echo "delete substances-domain PersistentVolumeClaim"
#microk8s kubectl delete -f 01-substances-business-pvc.yml
echo "Start substances-domain StateFulSet Pods"
microk8s kubectl delete -f 02-substances-domain-statefulset.yml
#echo "delete Eure-Services Internal Service ClusterIP"
#microk8s kubectl delete -f 03-substances-business-clusterip-svc.yml
#echo "delete substances-domain Nodeport"
#microk8s kubectl delete -f 04-substances-business-nodeport.yml