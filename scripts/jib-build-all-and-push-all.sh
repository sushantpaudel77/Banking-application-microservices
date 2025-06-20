#!/usr/bin/env bash

# Microservices to build and push
services=("configserver" "eurekaserver" "cards" "loans" "accounts" "gatewayserver")
dockerhub_username="sushantpaudel77"
tag="${1:-latest}"  # Accept optional tag (default: latest)

echo "🔐 Logging into Docker Hub..."
if ! docker login; then
  echo -e "❌ \033[1;31mDocker login failed. Exiting.\033[0m"
  exit 1
fi

start_time=$(date +%s)

# Loop through services
for service in "${services[@]}"; do
  echo -e "\n🔧 Building \033[1;34m$service\033[0m..."

  if ! cd "$service"; then
    echo -e "❌ \033[1;31mFailed to enter $service directory\033[0m"
    exit 1
  fi

  if mvn clean compile jib:dockerBuild -Dimage="$dockerhub_username/$service:$tag"; then
    echo -e "✅ \033[1;32m$service built successfully\033[0m"
  else
    echo -e "❌ \033[1;31mJib build failed for $service\033[0m"
    exit 1
  fi

  cd ..

  echo -e "📤 Pushing \033[1;34m$dockerhub_username/$service:$tag\033[0m..."
  if docker push "$dockerhub_username/$service:$tag"; then
    echo -e "✅ \033[1;32mSuccessfully pushed $dockerhub_username/$service:$tag\033[0m"
  else
    echo -e "❌ \033[1;31mFailed to push $dockerhub_username/$service:$tag\033[0m"
    exit 1
  fi
done

end_time=$(date +%s)
duration=$((end_time - start_time))
echo -e "\n🚀 \033[1;32mAll microservices built and pushed successfully in $duration seconds!\033[0m"
