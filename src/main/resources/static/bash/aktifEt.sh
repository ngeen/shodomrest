cd /root/shodomrest
git pull https://eoz:202020@bitbucket.org/eoz/shodomrest.git
/opt/maven/bin/mvn clean
/opt/maven/bin/mvn install
pkill -9 java
rm -rf /usr/local/shodom/shodom.jar
mv /root/shodomrest/target/blog-0.0.1-SNAPSHOT.jar /usr/local/shodom/shodom.jar
service shodom start
