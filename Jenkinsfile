pipeline {
  agent any

  environment {
    DOCKER_IMAGE = "23hamid/springdemo-app:latest" // replace with your Docker Hub username/repo
    DOCKER_REGISTRY_CREDENTIALS = "dockerhub-creds"   // Jenkins credential ID for Docker Hub
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build with Maven') {
      steps {
        sh 'mvn clean install -B'
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          sh "docker build -t ${DOCKER_IMAGE} ."
        }
      }
    }

    stage('Push Docker Image') {
      steps {
        script {
          docker.withRegistry('https://index.docker.io/v1/', "${DOCKER_REGISTRY_CREDENTIALS}") {
            sh "docker push ${DOCKER_IMAGE}"
          }
        }
      }
    }
  }

  post {
    success {
      echo "Pipeline finished successfully."
    }
    failure {
      echo "Pipeline failed. Check the logs."
    }
  }
}
