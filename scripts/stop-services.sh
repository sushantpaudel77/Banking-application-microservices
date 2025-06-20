#!/bin/bash

# Stop all Spring Boot services started with spring-boot:run (background)

echo "🛑 Stopping all Spring Boot services started by spring-boot:run..."

# Find all java processes running spring-boot:run and kill them
PIDS=$(pgrep -f 'spring-boot:run')

if [ -z "$PIDS" ]; then
  echo "No spring-boot:run processes found."
else
  echo "Killing PIDs: $PIDS"
  kill $PIDS

  # Wait a few seconds to ensure processes stop
  sleep 5

  # Check if any are still running and force kill
  STILL_RUNNING=$(pgrep -f 'spring-boot:run')
  if [ ! -z "$STILL_RUNNING" ]; then
    echo "Force killing remaining PIDs: $STILL_RUNNING"
    kill -9 $STILL_RUNNING
  fi

  echo "✅ All Spring Boot services stopped."
fi
