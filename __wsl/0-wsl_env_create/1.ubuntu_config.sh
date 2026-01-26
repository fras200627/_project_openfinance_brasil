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
ufw allow 5000
ufw allow 5001
ufw allow 8080
ufw allow 8081
ufw allow 8083
ufw allow 1621
ufw allow 13303
ufw allow 16672
ufw allow 1721
ufw allow 23303
ufw allow 5762
ufw allow 5773
ufw allow 17672
ufw allow 13306
ufw allow 1523
ufw allow 8000
ufw allow 8004
ufw allow 9443
ufw allow 9445

ufw reload

echo
echo "-------------------------------------------------------------------------------------------"
echo "FINISH"
echo "-------------------------------------------------------------------------------------------"
echo
echo
