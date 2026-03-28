#!/usr/bin/env bash

echo "========================================"
echo "Starting Microservices..."
echo "========================================"

start_service () {
  SERVICE_NAME=$1
  SERVICE_DIR=$2

  echo "Starting $SERVICE_NAME..."
  (
    cd "$SERVICE_DIR" || exit
    #./gradlew bootRun
    ./gradlew bootRun -x test
  ) &
}

start_service "product-service" "product-service"
sleep 5

start_service "review-service" "review-service"
sleep 5

start_service "recommendation-service" "recommendation-service"
sleep 5

start_service "product-composite-service" "product-composite-service"

echo "========================================"
echo "All services started."
echo "========================================"
echo "Press Ctrl+C to stop all services."
