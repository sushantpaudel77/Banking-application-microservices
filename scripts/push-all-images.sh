#!/bin/bash

# Docker Hub login
echo "Logging into Docker Hub..."
docker login

# List of fully qualified image names
images=(
  "sushantpaudel77/configserver"
  "sushantpaudel77/eurekaserver"
  "sushantpaudel77/cards"
  "sushantpaudel77/loans"
  "sushantpaudel77/accounts"
  "sushantpaudel77/gatewayserver"
)

echo "🚀 Starting push to Docker Hub..."

for image in "${images[@]}"; do
  echo "📤 Pushing ${image}:latest..."
  docker push "${image}:latest"

  if [ $? -eq 0 ]; then
    echo "✅ Successfully pushed ${image}"
  else
    echo "❌ Failed to push ${image}"
  fi
done

echo "All images pushed to Docker Hub!"

