FROM openjdk:21

WORKDIR /app

COPY target/sq-ch12-ex3-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["./wait-for-it.sh", "postgres", "java", "-jar", "app.jar"]
