## Cluster setup
The current instructions have been tested on a local kubernetes cluster using [Kind]() (Kubernetes in Docker) but there
shouldn't be any problem with other tools/platforms (Docker Desktop, Minikube).

In case of Kind, we have customized the kubernetes cluster with the following considerations
(you can see the configuration in kind-install.yaml and kind-install.sh):

- Kubernetes API version: **v1.16.15** 
  (in alignment with the latest stable version offered by GKE, which is **1.16.13-gke.401**)r K8s learning journey with hands-on training a
- Custom pod and service subnet (in alignment with defaults seen on GKE).
- Cluster with only 1 node (Kind can also be configured as a multicluster)
- Using [CALICO](https://docs.projectcalico.org/getting-started/kubernetes/) 
  as a network interface, instead of CNI (included by default in Kind).  
- Using **NodePort** instead of **LoadBalancer** because currently Kind doesn't fully support this feature. [^1]

[^1] TODO: check if this tweak has conflicts with Gloo.

All the configurations required for Kind are located 
  in the following files: `kubernetes\kind-install.yaml` and `kubernetes\kind-install.sh`

## Installing Istio
To install a particular version of Istio, run: 
```shell script
$ curl -L https://istio.io/downloadIstio | ISTIO_VERSION={VERSION} TARGET_ARCH=x86_64
```
Where *{VERSION}* is the desired version to work with. In this case, we'll use **1.6.8**

Next, move the installation folder to your work environment and make 
the bin sub-folder available to your **PATH** and make it available to your console
```shell script
$ mv ./istio-1.6.8 {CUSTOM_LOCATION}
export PATH="$PATH:{CUSTOM_LOCATION}/istio-1.6.8/bin"
```

Verify the installation:
```shell script
$ istioctl version
no running Istio pods in "istio-system"
1.6.8
```

Verify if our current cluster can run Istio without problems:
```shell script
$ istio experimental precheck
...
...
Install Pre-Check passed! The cluster is ready for Istio installation.
```

## Deploying Istio - Simplest way
Istio supports multiple configuration profiles as mentioned [here](https://istio.io/v1.6/docs/setup/additional-setup/config-profiles/)
. In the present tutorial we'll choose the **demo** profile to showcase addons such as *kiali* and *grafana*.

The simplest way to install a particular profile is by executing:
```shell script
$ istioctl manifest apply --set profile=demo
```

Wait for all pods to start (status changes to Running or Completed ). Note that this can
take a while as all images get downloaded and all containers started.
```shell script
$ kubectl get pods -n istio-system
NAME                                    READY   STATUS    RESTARTS   AGE
grafana-5dc4b4676c-7vlxs                1/1     Running   0          4h46m
istio-egressgateway-659dcf96bb-622gn    1/1     Running   0          4h46m
istio-ingressgateway-68fccd8656-nqlx7   1/1     Running   0          4h46m
istio-tracing-8584b4d7f9-5j8s2          1/1     Running   0          4h46m
istiod-7d69bf85c5-v7j5q                 1/1     Running   0          4h46m
kiali-6f457f5964-lkxqp                  1/1     Running   0          4h46m
prometheus-66b644f746-wbkwk             2/2     Running   0          4h46m
```

## Deploying Istio via IstioOperator
However, if we want to customize some parts of the installation, we should use IstioOperator to do that. 
As we're running the cluster locally and Kind, we want to change the following conditions:

- Consume as little memory as possible
- Configure the ingress gateway to use a service of type NodePort instead of LoadBalancer
- Enforce some policies in the mesh such as *block request to the outside*

For that, me need to initialize the Istio Operator:
```shell script
$ istioctl operator init
```
We also need to create the namespace for Istio:
```shell script
$ kubectl create ns istio-system
```
Finally, customize our installation via the operator:
```shell script
$ kubectl apply -f kind-istio-install.yaml
```
To monitor the installation:
```shell script
$ kubectl logs -f -n istio-operator $(kubectl get pods -n istio-operator -lname=istio-operator -o jsonpath='{.items[0].metadata.name}')
```
Check this [link](https://istio.io/v1.6/docs/setup/install/istioctl/#customizing-the-configuration.
) to learn more about the configuration parameters accepted in the Istio Operator.

## Injecting envoy proxy configuration to a deployment
In the first sample (*000-inject-sidecar-proxy*) we will see how **istioctl** can be used to modify deployment files
to include the configuration of envoy, which will act as a sidecar of our microservices.

To do that, run:
```shell script
$ istioctl kube-inject -f \
  ./000-inject-sidecar-proxy/deployment.yaml > ./000-inject-sidecar-proxy/deployment-with-injection.yaml
```



Uninstalling Istio
--------------------
$ istioctl manifest generate --set profile=demo | kubectl delete -f -
