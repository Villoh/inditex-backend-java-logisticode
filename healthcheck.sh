#!/bin/bash

# Define the maximum number of retries, interval between retries, and healthcheck URL
MAX_RETRIES=3
RETRY_INTERVAL=30
URL="http://localhost:3000/health"

# Loop through retry attempts
for ((i=1; i<=MAX_RETRIES; i++)); do
    # Attempt to check the health endpoint using wget
    wget --quiet --spider $URL

    # If wget returns a success code (0), exit with success
    if [ $? -eq 0 ]; then
        echo "Healthcheck passed."
        exit 0
    fi

    # If wget fails, print the attempt and wait before retrying
    echo "Attempt $i failed. Retrying in $RETRY_INTERVAL seconds..."
    sleep $RETRY_INTERVAL
done

# If all attempts fail, print failure message and exit with error code
echo "Healthcheck failed after $MAX_RETRIES attempts."
exit 1