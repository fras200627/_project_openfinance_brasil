#!/bin/bash
clear
echo "remove pod"
microk8s kubectl delete -f 02-tican-run-pod.yml
#echo
#echo
#echo "remove nodeport service"
#microk8s kubectl delete -f 04-tican-run-nodeport.yml
echo
echo
echo "remove ClusterIP Service"
microk8s kubectl delete -f 03-tican-run-svc.yml
echo
echo
echo "remove config map"
microk8s kubectl delete -f 00-tican-run-cmap.yml
#echo
#echo
#echo "remove persistent volume"
#microk8s kubectl delete -f 01-tican-run-pvc.yml
#echo
#echo