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
        sh 'nohup sh -c \'./mvnw spring-boot:run -pl batch\' &'
      }
    }

  }
}