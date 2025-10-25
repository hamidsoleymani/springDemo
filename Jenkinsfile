pipeline {
    agent any

    environment {
        // Optional: specify Java 21 if needed.
        JAVA_HOME = "/usr/lib/jvm/java-21"
        DOCKER_IMAGE = "myapp:${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
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







    post {
        success {
            echo "Build and deployment succeeded for branch ${env.BRANCH_NAME}"
        }
        failure {
            echo "Build or deployment failed for branch ${env.BRANCH_NAME}"
        }
    }
}
