#!/bin/bash
clear

echo
echo '********************************************************************************************'
echo '                                START MICROK8S INSTALL'
echo '********************************************************************************************'
echo
echo

echo "STEP 01:    MICROK8S: UPDATE/UPGRADE UBUNTU"
echo "-------------------------------------------------------------------------------------------"
apt-get -y update
apt-get -y upgrade
echo
echo

echo "STEP 02:    MICROK8S: INSTALL CLASSIC VERSION"
echo "-------------------------------------------------------------------------------------------"
snap install microk8s --classic
usermod -a -G microk8s $USER
chown -f -R $USER ~/.kube
echo
echo

echo "STEP 03:    MICROK8S: SETTINGS UFW/ROUTES"
echo "-------------------------------------------------------------------------------------------"
ufw allow in on cni0 && sudo ufw allow out on cni0
ufw default allow routed
echo
echo

echo "STEP 04:    MICROK8S: INSTALL CERTIFICATES"
echo "-------------------------------------------------------------------------------------------"
microk8s.refresh-certs --cert ca.crt
microk8s.refresh-certs --cert server.crt
echo
echo

echo "STEP 05:    MICROK8S: SETTINGS ACCESS"
echo "-------------------------------------------------------------------------------------------"
#microk8s disable dashboard /
#microk8s disable dns /
#microk8s disable hostpath-storage /
#microk8s disable host-access /
#microk8s disable registry /
#microk8s disable ingress
microk8s enable dashboard
microk8s enable dns
microk8s enable hostpath-storage
microk8s enable host-access
microk8s enable registry
microk8s enable storage
echo
echo

echo "-------------------------------------------------------------------------------------------"
echo "MICROK8S INSTALL FINISH"
echo "-------------------------------------------------------------------------------------------"
echo
echo
