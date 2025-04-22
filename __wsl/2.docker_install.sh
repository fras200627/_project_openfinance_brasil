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

echo
echo "DOCKER INSTALL"
echo "-------------------------------------------------------------------------------------------"
apt install docker-ce -y
apt install docker-compose -y
apt install swarm -y
apt install docker-buildx-plugin
docker buildx install

echo
echo "PORTAINER INSTALL"
echo "-------------------------------------------------------------------------------------------"
docker volume create portainer_data
docker run -d -p 8000:8000 -p 9443:9443 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:2.21.5

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
