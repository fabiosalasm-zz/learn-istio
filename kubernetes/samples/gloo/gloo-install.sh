#!/bin/bash
set -e
# Based on both Gloo's documentation (integration with Kind)
# and work from ML (disabling discovery upstream and functions)
cat <<EOF | glooctl install gateway --values -
discovery:
  enabled: false
gatewayProxies:
  gatewayProxy:
    service:
      type: NodePort
      httpPort: 31500
      httpsPort: 32500
      httpNodePort: 31500
      httpsNodePort: 32500
EOF