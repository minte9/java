#!/bin/sh

echo "========================================"
echo "Stopping Microservices..."
echo "========================================"

for SERVICE in product-service review-service recommendation-service product-composite-service
do
  echo "Stopping $SERVICE..."
  pkill -f "$SERVICE"
done

echo "========================================"
echo "All services stopped."
echo "========================================"
