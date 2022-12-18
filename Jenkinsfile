pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'chmod +x -R ./'
        sh './mvnw install'
      }
    }

    stage('start batch') {
      parallel {
        stage('start batch') {
          steps {
            sh './mvnw spring-boot:run -pl batch -Dspring.profiles.active=prod > /var/SpringServer/log_batch.log'
          }
        }

        stage('start web') {
          steps {
            sh './mvnw spring-boot:run -pl web >  /var/SpringServer/log_web.log'
          }
        }

      }
    }

  }
}