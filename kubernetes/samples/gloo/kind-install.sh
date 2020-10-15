#!/bin/bash
set -e
kind create cluster --config=kind-cluster.yaml --name=istio-gloo-sandbox
# Install Calico
curl https://docs.projectcalico.org/manifests/calico.yaml | kubectl apply -f -
# Install CoreDNS
kubectl scale deployment --replicas 1 coredns --namespace kube-system