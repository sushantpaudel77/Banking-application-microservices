#!/usr/bin/env bash

# Exit immediately if a command exits with a non-zero status
set -e

# Docker Hub login
echo "🔐 Logging into Docker Hub..."
if ! docker login; then
  echo "❌ Docker login failed. Exiting..."
  exit 1
fi

# Tag (default to 'latest' if not passed as an argument)
TAG="${1:-latest}"

# List of fully qualified image names
images=(
  "sushantpaudel77/configserver"
  "sushantpaudel77/eurekaserver"
  "sushantpaudel77/cards"
  "sushantpaudel77/loans"
  "sushantpaudel77/accounts"
  "sushantpaudel77/gatewayserver"
)

echo -e "\n🚀 Starting push to Docker Hub with tag: \033[1;32m$TAG\033[0m"
start_time=$(date +%s)

for image in "${images[@]}"; do
  echo -e "\n📤 Pushing \033[1;34m${image}:${TAG}\033[0m..."
  if docker push "${image}:${TAG}"; then
    echo -e "✅ \033[1;32mSuccessfully pushed ${image}:${TAG}\033[0m"
  else
    echo -e "❌ \033[1;31mFailed to push ${image}:${TAG}\033[0m"
  fi
done

end_time=$(date +%s)
echo -e "\n🏁 All pushes completed in $((end_time - start_time)) seconds."
