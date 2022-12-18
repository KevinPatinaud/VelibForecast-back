pipeline {
  agent any
  stages {
    stage('clean') {
      steps {
        sh 'echo ${WORKSPACE}'
        sh 'kill -9 `ps -ef | grep java | grep tomcat | grep -v grep | awk \'{ print $2 }\'` &'
        sleep 10
        sh 'kill -9 `ps -ef | grep java | grep tomcat | grep -v grep | awk \'{ print $2 }\'` &'
        sh 'rm -rf /var/SpringServer/log*'
      }
    }

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
            sh './mvnw spring-boot:run -pl batch -Dspring-boot.run.profiles=prod > /var/SpringServer/log_batch.log'
          }
        }

        stage('start web') {
          steps {
            sh './mvnw spring-boot:run -pl web -Dspring-boot.run.profiles=prod >  /var/SpringServer/log_web.log'
          }
        }

      }
    }

  }
}