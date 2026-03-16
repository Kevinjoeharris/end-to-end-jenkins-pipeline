pipeline {
    agent Docker
    stages {
        stage('Checkout Code') { // Checkout code
            steps {
                sh 'git checkout https://github.com/Kevinjoeharris/end-to-end-jenkins-pipeline-infra-config.git'
            }
        }

        stage('Build') { // Build code
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Unit test') { // Unit test
            steps {
                sh 'make check'
                junit '**/target/*.xml'
            }
        }
        
        stage('Sonar') { // SAST with Sonar
            steps {
                sh 'make check'
                junit '**/target/*.xml'
            }
        }

        stage('Push to Artifactory') { // Push jar artifact to Artifactory
            steps {
                sh 'make check'
            }
        }

        stage('Docker Image') { // Build Docker Image
            steps {
                sh 'make check'
                junit '**/target/*.xml'
            }
        }

        stage('Trivy') { // Image vulnerability test
            steps {
                sh 'make check'
                junit '**/target/*.xml'
            }
        }

        stage('Push to Registry') { // Push to Image to Hub
            steps {
                sh 'make check'
                junit '**/target/*.xml'
            }
        }

        stage('Update K8 Image Tag') { // The 'Deploy' stage
            steps {
                sh 'make publish'
            }
        }
    }
    
    post { // Actions to run after the pipeline finishes
        always { // Runs regardless of the pipeline outcome
            echo 'Pipeline finished.'
            junit 'target/surefire-reports/*.xml'
        }
        failure { // Runs only if the pipeline fails
            echo 'Pipeline failed!'
        }
    }
}
