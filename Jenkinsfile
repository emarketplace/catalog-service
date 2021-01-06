pipeline {
    agent any
    tools {
        maven 'maven'
    }
    environment {
        REPOSITORY = "muhammedshaheer"
        IMAGE_NAME = "emarketplace-catalog-service"
        NAMESPACE = "backend"
        SERVICE_NAME = "catalog-service"
    }
    stages {
        stage('Test') {
            steps {
//                 sh 'rm ./src/main/resources/application.yaml'
//                 sh 'mv ./src/main/resources/application.qa ./src/main/resources/application.yaml'
                sh 'mvn clean test'
            }
        }
        stage('Maven Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $REPOSITORY/$IMAGE_NAME:${BUILD_NUMBER} .'
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withDockerRegistry([ credentialsId: "dockerhub", url: "" ]) {
                    sh 'docker push $REPOSITORY/$IMAGE_NAME:${BUILD_NUMBER}'
                }
            }
        }
        stage('Deploy App') {
            steps {
                withKubeConfig([credentialsId: 'kubeconfig', serverUrl: '']) {
                    sh 'helm upgrade --install $SERVICE_NAME ./helm -n $NAMESPACE --set image.repository=$REPOSITORY/$IMAGE_NAME --set image.tag=${BUILD_NUMBER}'
                }
            }
        }
        stage('Pruning') {
            steps {
                sh 'docker rmi $REPOSITORY/$IMAGE_NAME:${BUILD_NUMBER}'
            }
        }
    }
}