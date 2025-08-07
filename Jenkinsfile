pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6' // Define in Jenkins tools config
    }

    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=.m2/repository"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/hamidsoleymani/springDemo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Publish to Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    }
}
