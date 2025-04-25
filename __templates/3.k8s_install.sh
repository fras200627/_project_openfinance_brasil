#!/bin/bash
clear

echo
echo "START MICROK8S INSTALL"
echo "-------------------------------------------------------------------------------------------"
apt update -y

echo
echo "-------------------------------------------------------------------------------------------"
snap install microk8s --classic
microk8s status --wait-ready
usermod -a -G microk8s $USER
chown -f -R $USER ~/.kube

echo microk8s enable dashboard dns ingress
microk8s enable dashboard dns registry istio

microk8s kubectl get all --all-namespaces
microk8s dashboard-proxy

echo
echo "-------------------------------------------------------------------------------------------"
echo "FINISH"
echo "-------------------------------------------------------------------------------------------"
echo
echo


apt  install docker.io

echo
echo "PORTAINER INSTALL"
echo "-------------------------------------------------------------------------------------------"
docker volume create portainer_data
docker run -d -p 8000:8000 -p 9443:9443 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:2.21.5

echo
echo "-------------------------------------------------------------------------------------------"
echo systemctl status docker ufw stat
usermod -aG docker ${USER}

microk8s.refresh-certs --cert ca.crt
microk8s.refresh-certs --cert server.crt

echo
echo "-------------------------------------------------------------------------------------------"
echo "FINISH"
echo "-------------------------------------------------------------------------------------------"
echo
echo
