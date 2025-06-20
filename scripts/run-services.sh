#!/bin/bash

# Exit immediately if any command fails
set -e

# Function to run a service
run_service() {
  SERVICE_NAME=$1
  echo "▶️ Starting $SERVICE_NAME with tests skipped..."
  cd $SERVICE_NAME
  ./mvnw spring-boot:run -DskipTests &
  cd ..
  sleep 10  # wait for service to start
}

# STEP-BY-STEP BOOT ORDER
run_service configserver
run_service eurekaserver
run_service accounts
run_service cards
run_service loans
run_service gatewayserver

echo "✅ All services started in order (with tests skipped)."
