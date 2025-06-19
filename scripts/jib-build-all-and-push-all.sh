#!/bin/bash

# Define array of microservice directories (same as image names)
services=("configserver" "eurekaserver" "cards" "loans" "accounts" "gatewayserver")
dockerhub_username="sushantpaudel77"

echo "🔐 Logging into Docker Hub..."
docker login
if [ $? -ne 0 ]; then
  echo "❌ Docker login failed. Exiting."
  exit 1
fi

# Loop through each microservice
for service in "${services[@]}"; do
  echo "🔧 Building $service..."

  cd "$service" || { echo "❌ Failed to enter $service directory"; exit 1; }

  # Build Docker image using Jib
  mvn clean compile jib:dockerBuild -Dimage=$dockerhub_username/$service:latest
  if [ $? -ne 0 ]; then
    echo "❌ Jib build failed for $service"
    exit 1
  else
    echo "✅ $service built successfully"
  fi

  cd ..

  echo "📤 Pushing $dockerhub_username/$service:latest..."
  docker push "$dockerhub_username/$service:latest"

  if [ $? -eq 0 ]; then
    echo "✅ Successfully pushed $dockerhub_username/$service"
  else
    echo "❌ Failed to push $dockerhub_username/$service"
    exit 1
  fi
done

echo "🏁 All microservices built and pushed successfully!"
