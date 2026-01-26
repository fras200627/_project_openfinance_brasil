#!/bin/bash
clear

echo
echo "START BUILD AND PUSK COMPONENT"
echo "-------------------------------------------------------------------------------------------------"
echo "go a path project"
echo "-------------------------------------------------------------------------------------------------"
cd /mnt/c/_development/__projects/_project_openfinance_brasil/workspace/01.ofb.cloud.services/sboot-atom-ofb-discovery-services

echo
echo "-------------------------------------------------------------------------------------------------"
echo "login in docker plataform"
echo "-------------------------------------------------------------------------------------------------"
docker login -u fras200627 -p @Xi057115

echo
echo "-------------------------------------------------------------------------------------------------"
echo "build a springboot project"
echo "-------------------------------------------------------------------------------------------------"
docker build -t fras200627/sboot-atom-ofb-discovery-services:1.0.0 .

echo
echo "-------------------------------------------------------------------------------------------------"
echo "push a image to docker platform"
echo "-------------------------------------------------------------------------------------------------"
docker push fras200627/sboot-atom-ofb-discovery-services:1.0.0

echo
echo "FINISH!"
echo
echo "-------------------------------------------------------------------------------------------------"
echo "go to a directory default"
echo "-------------------------------------------------------------------------------------------------"
cd /mnt/c/_development/__projects/_project_openfinance_brasil/__wsl/1-wsl_docker_runners/
echo "-------------------------------------------------------------------------------------------------"
echo
echo
