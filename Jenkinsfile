pipeline {
    agent any

    environment {
        // Optional: specify Java 21 if needed.
        JAVA_HOME = "/usr/lib/jvm/java-21"
        DOCKER_IMAGE = "myapp:${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from the branch that triggered the build
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Build Spring Boot app
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Run unit/integration tests
                sh './mvnw test'
            }
        }

        stage('Docker Build') {
            steps {
                // Build Docker image
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Push Docker Image..') {
            when {
                branch 'main'  // Only push images from main branch
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                        docker push ${DOCKER_IMAGE}
                    """
                }
            }
        }

        stage('Deploy to Kubernetes') {
            when {
                anyOf {
                    branch 'main'
                    branch 'develop'
                }
            }
            steps {
                script {
                    def envFolder = (env.BRANCH_NAME == 'main') ? 'prod' : 'dev'
                    sh "kubectl apply -f k8s/${envFolder}/"
                }
            }
        }
    }

    post {
        success {
            echo "Build and deployment succeeded for branch ${env.BRANCH_NAME}"
        }
        failure {
            echo "Build or deployment failed for branch ${env.BRANCH_NAME}"
        }
    }
}
