#!/bin/bash
set -e

echo "Applying namespace configuration..."
kubectl apply -f ns.yml

echo "Applying ConfigMaps..."
kubectl apply -f cm.yml

echo "Applying Secrets..."
kubectl apply -f secret.yml

echo "Applying Old Database configurations..."
kubectl apply -f old-db/

echo "Applying New Database configurations..."
kubectl apply -f new-db/

echo "Applying Identity Provider configurations..."
kubectl apply -f idp/

echo "All configurations have been applied successfully!"