#!/bin/bash
clear

echo
echo "START DOCKER PRE-INSTALL"
echo "-------------------------------------------------------------------------------------------"
apt install apt-transport-https ca-certificates curl software-properties-common -y

echo
echo "-------------------------------------------------------------------------------------------"
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | \
sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

echo
echo "-------------------------------------------------------------------------------------------"
echo "deb [arch=$(dpkg --print-architecture) \
signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] \
https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | \
sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

echo \
"deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
$(. /etc/os-release && echo "${UBUNTU_CODENAME:-$VERSION_CODENAME}") stable" | \
sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
chmod a+r /etc/apt/keyrings/docker.asc

echo
echo "DOCKER VERIFY AND UNINSTALL"
echo "-------------------------------------------------------------------------------------------"
apt-get remove docker-ce-cli -y
apt-get remove containerd.io -y
apt-get remove docker-buildx-plugin -y
apt-get remove docker-compose-plugin -y
apt-get remove docker-compose -y

echo
echo "DOCKER INSTALL"
echo "-------------------------------------------------------------------------------------------"
apt -y update
apt -y upgrade
apt-get install docker-ce -y
apt-get install docker-ce-cli -y
apt-get install containerd.io -y
apt-get install docker-buildx-plugin -y
apt-get install docker-compose-plugin -y
apt-get install docker-compose -y
docker buildx install

echo
echo "-------------------------------------------------------------------------------------------"
echo systemctl status docker ufw stat
usermod -aG docker ${USER}

echo
echo "-------------------------------------------------------------------------------------------"
echo "FINISH"
echo "-------------------------------------------------------------------------------------------"
echo
echo
