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
                  sh 'pkill -f sporttrainer'
                  sh 'pwd'
                  sh 'bash -c "exec -a sporttrainer java -jar -Xms300m -Xmx450m -Dspring.profiles.active=pro target/sporttrainer.jar &"'
              }
          }
    }
}