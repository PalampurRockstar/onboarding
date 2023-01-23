# onboarding

Commands:
1.  docker build -t onboarding .
2.  docker tag onboarding us.gcr.io/main-aura-375015/onboarding:v1.0
3.  gcloud auth configure-docker
4.  docker push us.gcr.io/main-aura-375015/onboarding:v1.0
5.  <Edit student-app-api-deployment.yaml latest version>
6.  kubectl apply -f k8s/student-app-api-deployment.yaml