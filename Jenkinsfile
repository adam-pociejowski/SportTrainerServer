pipeline {
    agent any

    stages {
        stage ('Build Stage') {
            steps {
                withMaven(maven : 'maven_3.5.2') {
                    sh 'mvn clean install'
                }
            }

        }

         stage ('Test Stage') {
             steps {
                 withMaven(maven : 'maven_3.5.2') {
                     sh 'mvn test'
                 }
             }
         }

          stage ('Deploy Stage') {
              steps {
                  sh 'source ~/.bash_profile'
                  sh 'pkill -f sporttrainer'
                  sh 'bash -c "exec -a sporttrainer java -jar -Xms300m -Xmx450m -Dspring.profiles.active=pro sporttrainer.jar &"'
              }
          }
    }
}