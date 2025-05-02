#!/bin/bash
clear
cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.backup/run_discovery_services/
echo
echo
./echo "remove discovery services"
microk8s kubectl delete -f 01-tican-eureka-pod.yml
echo
echo
cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.backup/basic_run_gateway
echo "remove api gateway"
microk8s kubectl delete -f 01-tican-eureka-pod.yml

echo
echo
cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.backup/basic_run_auth_server
echo "remove api gateway"
microk8s kubectl delete -f 01-tican-eureka-pod.yml
