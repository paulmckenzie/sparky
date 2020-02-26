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
    image 'gradle:jdk11'
    command:
    - cat
    tty: true
"""
        }
    }

    stages {

        stage('Source') {
            container('ci') {
                steps {
                    git credentialsId: 'GitHub', url: 'https://github.com/paulmckenzie/sparky'
                }
            }
        }

        stage('Test') {
            container('ci') {
                steps {
                    sh './gradlew clean test'
                }
            }
        }

        stage('Publish') {
            container('ci') {
                steps{
                    sh './gradlew -Preckon.stage=final jib'
                }
            }
        }
    }
}
