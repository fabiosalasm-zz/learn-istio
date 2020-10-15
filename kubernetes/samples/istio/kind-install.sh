#!/bin/bash
set -e
kind create cluster --config=kind-cluster.yaml --name=istio-sandbox
# Install Calico
curl https://docs.projectcalico.org/manifests/calico.yaml | kubectl apply -f -
# Install CoreDNS
kubectl scale deployment --replicas 1 coredns --namespace kube-system
# Metrics Server
#helm repo add stable https://kubernetes-charts.storage.googleapis.com
#helm repo update
#helm upgrade metrics-server --install --set "args={--kubelet-insecure-tls, --kubelet-preferred-address-types=InternalIP}" stable/metrics-server --namespace kube-system