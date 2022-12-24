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
        script {
          withEnv(['JENKINS_NODE_COOKIE =dontkill']) {
            sh 'mv ${WORKSPACE}/batch/target/batch.jar /var/SpringServer/batch.jar'
            sh 'mv ${WORKSPACE}/web/target/web.jar /var/SpringServer/web.jar'
            sh 'java -Dhudson.util.ProcessTree.disable=true -Duser.dir=/var/SpringServer -jar -Dspring.profiles.active=prod /var/SpringServer/batch.jar &'

          }
        }

      }
    }

  }
}