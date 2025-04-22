#!/bin/bash
#echo "delete ConfigMap Defaults"
#microk8s kubectl delete -f ../00.config.map.defaults/00-config-map-defaults.yml
echo "delete products-domain ConfigMap"
microk8s kubectl delete -f 00-products-domain-cmap.yml
#echo "delete products-domain PersistentVolumeClaim"
#microk8s kubectl delete -f 01-audit-services-pvc.yml
echo "Start products-domain StateFulSet Pods"
microk8s kubectl delete -f 02-products-domain-statefulset.yml
#echo "delete Eure-Services Internal Service ClusterIP"
#microk8s kubectl delete -f 03-audit-services-clusterip-svc.yml
#echo "delete products-domain Nodeport"
#microk8s kubectl delete -f 04-audit-services-nodeport.yml