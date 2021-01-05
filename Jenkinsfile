pipeline {
    agent any
    environment {
        REPOSITORY = "muhammedshaheer"
        IMAGE_NAME = "emarketplace-catalog-service"
        NAMESPACE = "backend"
        SERVICE_NAME = "catalog-service"
    }
    stages{
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
        stage {
            steps {
                sh 'docker push $REPOSITORY/$IMAGE_NAME:${BUILD_NUMBER}'
            }
        }
        stage {
            steps {
                sh 'helm upgrade --install $SERVICE_NAME ./helm -n $NAMESPACE --set image.repository=$REPOSITORY/$IMAGE_NAME --set image.tag=${BUILD_NUMBER}'
            }
        }
        stage('Pruning') {
            steps {
                sh 'docker rmi $REPOSITORY/$IMAGE_NAME:${BUILD_NUMBER}'
            }
        }
    }
}