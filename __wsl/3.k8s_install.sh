#!/bin/bash
clear

echo
echo "START MICROK8S INSTALL"
echo "-------------------------------------------------------------------------------------------"
apt update -y

echo
echo "-------------------------------------------------------------------------------------------"
snap install microk8s --classic
usermod -a -G microk8s $USER
chown -f -R $USER ~/.kube
microk8s enable dashboard dns ingress
microk8s kubectl get all --all-namespaces

echo
echo "-------------------------------------------------------------------------------------------"
echo "FINISH"
echo "-------------------------------------------------------------------------------------------"
echo
echo
