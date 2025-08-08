pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9' // Install via Jenkins -> Global Tool Configuration
        jdk 'JDK 17'        // Install via Jenkins -> Global Tool Configuration
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/hamidsoleymani/springDemo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
