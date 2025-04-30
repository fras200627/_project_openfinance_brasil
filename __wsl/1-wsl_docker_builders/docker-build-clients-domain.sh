#!/bin/bash
clear

echo
echo "START BUILD AND PUSH COMPONENT"
echo "-------------------------------------------------------------------------------------------------"
echo "go a path project"
echo "-------------------------------------------------------------------------------------------------"
cd /mnt/c/_development/__projects/_project_tican_openapi/workspace/03.oauth2.registered.clients/project-tican-oauth2-registered-clients-domain

echo
echo "-------------------------------------------------------------------------------------------------"
echo "login in docker plataform"
echo "-------------------------------------------------------------------------------------------------"
docker login -u fras200627 -p @Xi057115

echo
echo "-------------------------------------------------------------------------------------------------"
echo "build a springboot project"
echo "-------------------------------------------------------------------------------------------------"
docker build -t fras200627/tican-oauth2-registered-clients-domain-api:2.0.0 .

echo
echo "-------------------------------------------------------------------------------------------------"
echo "push a image to docker platform"
echo "-------------------------------------------------------------------------------------------------"
docker push fras200627/tican-oauth2-registered-clients-domain-api:2.0.0

echo
echo "FINISH!"
echo
echo "-------------------------------------------------------------------------------------------------"
echo "go to a directory default"
echo "-------------------------------------------------------------------------------------------------"
cd /mnt/c/_development/__projects/_project_tican_openapi/__wsl/1-wsl_docker_builders/
echo "-------------------------------------------------------------------------------------------------"
echo
echo
