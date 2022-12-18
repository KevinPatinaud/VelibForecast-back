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
        sh 'mv ${WORKSPACE}/batch/target/batch.jar /var/SpringServer/velib_batch.jar'
        sh './mvnw spring-boot:run -pl batch  >  /var/SpringServer/log.log'
      }
    }

  }
}