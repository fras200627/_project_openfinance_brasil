#!/bin/bash
clear
cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.runners_k8s/1.discovery_services/
microk8s kubectl apply -f 02-tican-run-pod.yml

cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.runners_k8s/2.api_gateway/
microk8s kubectl apply -f 02-tican-run-pod.yml

cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.runners_k8s/3.auth_server/
microk8s kubectl apply -f 02-tican-run-pod.yml

cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.runners_k8s/8.users_domain/
microk8s kubectl apply -f 02-tican-run-pod.yml

cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.runners_k8s/7.users_business/
microk8s kubectl apply -f 02-tican-run-pod.yml

cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.runners_k8s/6.clients_domain/
microk8s kubectl apply -f 02-tican-run-pod.yml

cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.runners_k8s/5.clients_business/
microk8s kubectl apply -f 02-tican-run-pod.yml

cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/2-wsl_k8s_runners/00.runners_k8s/4.audit_services/
microk8s kubectl apply -f 02-tican-run-pod.yml




