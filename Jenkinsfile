#! /usr/bin/env groovy

pipeline {

    agent {
        kubernetes {
            label 'sample-app'
            defaultContainer 'jnlp'
            yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: ci
    image: 'gradle:jdk11'
    command:
    - cat
    tty: true
"""
        }
    }

    stages {

        stage('Source') {
            steps {
                container('ci') {
                    git credentialsId: 'GitHub', url: 'https://github.com/paulmckenzie/sparky'
                }
            }
        }

        stage('Test') {
            steps {
                container('ci') {
                    sh './gradlew clean test'
                }
            }
        }

        stage('Publish') {
            steps{
                container('ci') {
                    withCredentials([string(credentialsId: 'DockerHubToken', variable: 'DOCKER_REGISTRY_TOKEN'),
                                     string(credentialsId: 'DockerHubAccount', variable: 'DOCKER_REGISTRY_USERNAME')]) {
                        sh "./gradlew \
                        -Preckon.stage=final \
                        -Pdocker_registry_token=${DOCKER_REGISTRY_TOKEN} \
                        -Pdocker_registry_username=${DOCKER_REGISTRY_USERNAME} \
                        jib"

                    }
                }
            }
        }
    }
}
