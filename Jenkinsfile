pipeline {
  agent any
  stages {
    stage('code coverage') {
      steps {
        sh 'mvn clean cobertura:cobertura'
      }
    }

    stage('build') {
      steps {
        sh 'mvn clean package'
      }
    }

    stage('deployement') {
      steps {
        sh 'mv ${WORKSPACE}/target/ReapMyTube.jar /var/SpringServer/ReapMyTube.jar'
        sh '''process=`netstat -plten |grep java | grep 8082 | tr -s \\\' \\\' | cut -d" " -f 9 | cut -d"/" -f 1`
if [[ -n "$process" ]]
then
    kill -9 $process
fi'''
        sleep 5
        sh '''java -jar  /var/SpringServer/ReapMyTube.jar --youtube.key=AIzaSyBW3vUm0FYk0pr65dxkc1U1FD37CCF0Kos >  /var/SpringServer/log.log
'''
      }
    }

  }
}