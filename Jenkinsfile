pipeline {
    agent any

    stages {
        stage ('Build Stage') {
            steps {
                sh 'mvn clean install -Dmaven.test.skip=true'
            }

        }

         stage ('Test Stage') {
             steps {
                 sh 'mvn test'
             }
         }

          stage ('Deploy Stage') {
              steps {
                  sh '/home/valverde/scripts/jenkins/sporttrainer.sh'
              }
          }
    }
}