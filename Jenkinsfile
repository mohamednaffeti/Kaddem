pipeline {
    agent any

    environment {
        // Define any environment variables, like JDK version
        JAVA_HOME = tool name: 'JDK 11', type: 'Tool'  // Adjust if necessary
    }

    stages {
        // Stage 1: Checkout the code from your Git repository
        stage('Checkout') {
            steps {
                git 'https://github.com/mohamednaffeti/Kaddem.git'
            }
        }

        // Stage 2: Build the project
        stage('Build') {
            steps {
                script {
                    // Run Maven to clean, compile, and test the Spring Boot project
                    sh "'${tool name: 'Maven 3', type: 'Tool'}/bin/mvn' clean install"
                }
            }
        }

        // Stage 3: Run tests (optional but highly recommended)
        stage('Test') {
            steps {
                script {
                    // Run Maven tests if you have test cases
                    sh "'${tool name: 'Maven 3', type: 'Tool'}/bin/mvn' test"
                }
            }
        }

        // Stage 4: Package the Spring Boot application
        stage('Package') {
            steps {
                script {
                    // Create the .jar file (Spring Boot packaging)
                    sh "'${tool name: 'Maven 3', type: 'Tool'}/bin/mvn' package"
                }
            }
        }

        // Optional: Deploy stage (can be skipped or customized based on your needs)
        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                // Add your deployment scripts here (e.g., Docker, Kubernetes, or any server deployment)
            }
        }
    }

    post {
        success {
            echo 'Build and test succeeded.'
        }
        failure {
            echo 'Build or test failed.'
        }
    }
}
