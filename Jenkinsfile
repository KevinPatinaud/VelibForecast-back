pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'chmod +x -R ./'
        sh './mvnw install'
      }
    }

    stage('deployement') {
      steps {
        sh 'mv ${WORKSPACE}/target//var/SpringServer/target/'
        sh './mvnw spring-boot:run -pl batch  >  /var/SpringServer/log.log'
      }
    }

  }
}