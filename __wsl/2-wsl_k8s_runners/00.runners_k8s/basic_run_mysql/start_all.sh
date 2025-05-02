#!/bin/bash
clear
echo "create ConfigMap Defaults"
microk8s kubectl apply -f ../../00.config.map.defaults/00-config-map-defaults.yml
echo
echo
echo "start run config map"
microk8s kubectl apply -f 00-tican-run-cmap.yml
#echo
#echo
#echo "start run pvc"
#microk8s kubectl apply -f 01-tican-run-pvc.yml
echo
echo
echo "start run pod"
microk8s kubectl apply -f 02-tican-run-pod.yml
echo
echo
echo "start run ClusterIP Service"
microk8s kubectl apply -f 03-tican-run-svc.yml
echo
echo
echo "start run nodeport service"
microk8s kubectl apply -f 04-tican-run-nodeport.yml
echo
echo
