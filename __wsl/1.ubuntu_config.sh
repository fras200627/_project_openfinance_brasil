#!/bin/bash
clear

echo
echo
echo "START UBUNTU UPDATE"
echo "-------------------------------------------------------------------------------------------"
apt -y update
apt -y upgrade

echo
echo "SSH INSTALL"
echo "-------------------------------------------------------------------------------------------"
apt install openssh-server -y
apt install ufw -y
apt install net-tools -y

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
ufw allow 22:33000/tcp
ufw reload

echo
echo "-------------------------------------------------------------------------------------------"
echo "FINISH"
echo "-------------------------------------------------------------------------------------------"
echo
echo
