FROM openjdk:8
VOLUME /tmp
EXPOSE 8080
ADD target/authenticator-0.0.1-SNAPSHOT.jar authenticator-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/authenticator-0.0.1-SNAPSHOT.jar"]