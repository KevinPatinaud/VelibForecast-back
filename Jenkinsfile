pipeline {
  agent any
  stages {
    stage('clean') {
      steps {
        sh 'echo ${WORKSPACE}'
        sh 'kill -9 `ps -ef | grep "jar -Dspring.profiles.active=prod" | grep -v grep | awk \'{ print $2 }\'` &'
        sleep 10
        sh 'kill -9 `ps -ef | grep "jar -Dspring.profiles.active=prod" | grep -v grep | awk \'{ print $2 }\'` &'
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
            sh 'mv ${WORKSPACE}/batch/target/batch.jar /var/SpringServer/batch.jar'
            sh 'java  -Duser.dir=/var/SpringServer -jar -Dspring.profiles.active=prod /var/SpringServer/batch.jar  2 > /var/SpringServer/log_batch.txt &'
          }
        }

        stage('start web') {
          steps {
            sh 'mv ${WORKSPACE}/web/target/web.jar /var/SpringServer/web.jar'
            sh 'java -Duser.dir=/var/SpringServer -jar -Dspring.profiles.active=prod /var/SpringServer/web.jar  2 > /var/SpringServer/log_web.txt &'
          }
        }

      }
    }

  }
}