#!/bin/bash

# Define array of microservice directories
services=("eurekaserver" "configserver" "accounts" "cards" "loans" "gatewayserver")

# Loop through each and build with Jib
for service in "${services[@]}"; do
  echo "🔧 Building $service..."
  cd "$service" || { echo "Failed to enter $service"; exit 1; }

  mvn clean compile jib:dockerBuild
  if [ $? -ne 0 ]; then
    echo "Jib build failed for $service"
    exit 1
  else
    echo "$service built successfully"
  fi

  cd ..
done

