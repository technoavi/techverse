#!/bin/bash

# Docker image name and tag
IMAGE_NAME="customer-service-img"
IMAGE_TAG="latest"

# Build the Docker image
docker build -t "$IMAGE_NAME:$IMAGE_TAG" .

# Run the Docker container
docker run -d -p 8082:8082 --name my_container "$IMAGE_NAME:$IMAGE_TAG"

# You can modify the options of 'docker run' as per your requirements.
# For example, if your application listens on a different port (e.g., 3000), use:
# docker run -d -p 3000:80 --name my_container "$IMAGE_NAME:$IMAGE_TAG"

# If you have any additional setup steps or environment variables to pass, you can include them in the 'docker run' command.

# Optionally, you can remove any old containers with the same name if they exist
# docker stop my_container
# docker rm my_container



