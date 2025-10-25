pipeline {
  agent {
    docker {
      image 'maven:3.9-jdk-17'
      args '-v $HOME/.m2:/root/.m2'
    }
  }
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Build') {
      steps {
        sh 'mvn clean install -B'
      }
    }
  }
}
