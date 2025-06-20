#!/usr/bin/env bash

# Define array of microservice directories
services=("eurekaserver" "configserver" "accounts" "cards" "loans" "gatewayserver")

build_service() {
  local service="$1"
  echo "🔧 Building $service..."
  cd "$service" || { echo "❌ Failed to enter $service"; exit 1; }

  mvn clean compile jib:dockerBuild
  if [ $? -ne 0 ]; then
    echo "❌ Jib build failed for $service"
    exit 1
  else
    echo "✅ $service built successfully"
  fi

  cd ..
}

start_time=$(date +%s)

for service in "${services[@]}"; do
  build_service "$service"
done

end_time=$(date +%s)
echo "🏁 All services built in $((end_time - start_time)) seconds."
