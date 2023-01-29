# onboarding

Commands:
1.  docker build -t us.gcr.io/main-aura-375015/onboarding:v1.0 --no-cache .
2.  gcloud auth configure-docker
3.  docker push us.gcr.io/main-aura-375015/onboarding:v1.0
4.  <Edit pet-onboarding-deployment.yaml latest version>
5.  <Optional> kubectl delete deploy pet-onboarding
6.  kubectl apply -f k8s/pet-onboarding-deployment.yaml