#!/bin/bash
#echo "delete ConfigMap Defaults"
#microk8s kubectl delete -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "delete audit-services ConfigMap"
microk8s kubectl delete -f 00-audit-services-cmap.yml
#echo "delete audit-services PersistentVolumeClaim"
#microk8s kubectl delete -f 01-audit-services-pvc.yml
echo "Start audit-services StateFulSet Pods"
microk8s kubectl delete -f 02-audit-services-statefulset.yml
#echo "delete Eure-Services Internal Service ClusterIP"
#microk8s kubectl delete -f 03-audit-services-clusterip-svc.yml
#echo "delete audit-services Nodeport"
#microk8s kubectl delete -f 04-audit-services-nodeport.yml