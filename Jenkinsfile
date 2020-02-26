#! /usr/bin/env groovy

pipeline {

    stages {

        stage('Source') {
            steps {
                git credentialsId: 'GitHub', url: 'https://github.com/paulmckenzie/sparky'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew clean test'
            }
        }

        stage('Publish') {
            steps{
                sh './gradlew -Preckon.stage=final jib'
            }
        }
    }
}
