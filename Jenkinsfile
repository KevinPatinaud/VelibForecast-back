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
        sh 'nohup sh -c \'cd ${WORKSPACE}; ./mvnw spring-boot:run -pl batch\' &'
      }
    }

  }
}