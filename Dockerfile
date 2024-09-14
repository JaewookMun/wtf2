FROM amazoncorrectto:17-alppine-jdk
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
ARG PROFILES
ARG ENV
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILES}", "-Dserver.env=${ENV}", "-jar", "app.jar"]