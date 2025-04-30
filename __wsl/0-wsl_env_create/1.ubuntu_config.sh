#!/bin/bash
clear

echo
echo
echo "START UBUNTU UPDATE"
echo "-------------------------------------------------------------------------------------------"
apt -y update
apt -y upgrade

echo
echo "SSH and TOOLS INSTALL"
echo "-------------------------------------------------------------------------------------------"
apt install openssh-server -y
apt install ufw -y
apt install net-tools -y
addgroup --system net-tools
adduser $USER net-tools
apt-get install netcat
addgroup --system netcat
adduser $USER netcat
snap install nmap
addgroup --system nmap
adduser $USER nmap

echo
echo "SSH CONFIG"
echo "-------------------------------------------------------------------------------------------"
addgroup --system ssh
addgroup --system ufw
addgroup --system net-tools
adduser $USER ssh
adduser $USER ufw
systemctl daemon-reload
systemctl enable ssh
systemctl start ssh
service ssh start
ufw enable
ufw allow 22
#ufw allow 3000:8000/tcp
#ufw allow 30000:33000/tcp
ufw reload

echo
echo "-------------------------------------------------------------------------------------------"
echo "FINISH"
echo "-------------------------------------------------------------------------------------------"
echo
echo
