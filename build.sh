#!/bin/bash

mvn clean install
docker build -t mariosergioas/desafio-back-votos .
docker tag mariosergioas/desafio-back-votos:latest 251839969735.dkr.ecr.us-east-2.amazonaws.com/desafio-back-votos:latest
docker push 251839969735.dkr.ecr.us-east-2.amazonaws.com/rabbitmq:latest

echo 'sucesso ao buildar aplicação'

pause 3