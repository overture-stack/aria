def gitHubRepo = "overture-stack/aria"
def commit = "UNKNOWN"
def version = "UNKNOWN"


pipeline {
    agent {
        kubernetes {
            label 'aria'
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: maven
    command: ['cat']
    tty: true
    image: maven:3.6.3-openjdk-11
"""
        }
    }
    stages {
        stage('Prepare') {
            steps {
                script {
                    commit = sh(returnStdout: true, script: 'git describe --always').trim()
                }
                script {
                    version = readMavenPom().getVersion()
                }
            }
        }
        stage('Test') {
            steps {
                container('maven') {
                    sh "./mvnw test"
                }
            }
        }
        stage('Build Artifact & Publish') {
             when {
                anyOf {
                    branch "main"
                    branch "develop"
                }
            }
            steps {
                container('maven') {
                    configFileProvider(
                        [configFile(fileId: '01ae7759-03a9-47c0-9db6-925aebb50ae1', variable: 'MAVEN_SETTINGS')]) {
                        sh './mvnw -s $MAVEN_SETTINGS clean package deploy'
                    }
                }
            }
        }
    }
}
