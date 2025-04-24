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

echo "STEP 06:    MICROK8S: INSTALL NGINX FOR TESTS"
echo "-------------------------------------------------------------------------------------------"
kubectl run nginx-pod --image=nginx --restart=Never --port=80 -n default
kubectl expose pod nginx-pod --type=NodePort --port=80 --name=nginx-service
echo
echo

echo "-------------------------------------------------------------------------------------------"
echo "MICROK8S INSTALL FINISH"
echo "-------------------------------------------------------------------------------------------"
echo
echo

echo '********************************************************************************************'
echo '                                VERIFY MICROK8S COMMANDS'
echo '********************************************************************************************'
echo 'FOR GET ALL STATUS: '
echo 'microk8s status --wait-ready'
echo '--------------------------------------------------------------------------------------------'
echo 'FOR GET ALL NAMESPACES: '
echo 'microk8s kubectl get all --all-namespaces'
echo '--------------------------------------------------------------------------------------------'
echo 'FOR GET STATUS A DASHBOARD and SHOW URL AND TOKEN ACCESS: '
echo 'microk8s dashboard-proxy'
echo '--------------------------------------------------------------------------------------------'
echo 'FOR GET TOKEN FOR USE IN DASHBOARD: '
echo 'token=$(microk8s kubectl -n kube-system get secret | grep default-token | cut -d " " -f1)'
echo 'microk8s kubectl -n kube-system describe secret $token'
echo
echo 'microk8s kubectl create token default'
echo '--------------------------------------------------------------------------------------------'
echo
echo '********************************************************************************************'
echo
echo
