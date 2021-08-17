#!/bin/zsh

echo "Performing a clean Maven build"
./mvnw clean package -DskipTests=true

echo "Setting the default builder for pack"
pack config default-builder cnbs/sample-builder:bionic

echo "Packing backend"
./mvnw package && java -jar target/pokedex-0.1.0.jar

echo "Instantiating mail server"
maildev