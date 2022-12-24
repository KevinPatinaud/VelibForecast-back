pipeline {
  agent any
  stages {
    stage('clean') {
      steps {
        sh 'echo ${WORKSPACE}'
        sh '/var/SpringServer/stop.sh'
      }
    }

    stage('build') {
      steps {
        sh 'chmod +x -R ./'
        sh './mvnw install'
      }
    }

    stage('start') {
      steps {
        sh 'mv ${WORKSPACE}/batch/target/batch.jar /var/SpringServer/batch.jar'
        sh 'mv ${WORKSPACE}/batch/target/web.jar /var/SpringServer/web.jar'
        sh '/var/SpringServer/start.sh'
      }
    }

  }
}