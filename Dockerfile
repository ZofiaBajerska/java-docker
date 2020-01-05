FROM openjdk:11
COPY target/spring-mvc-rest-data-exercise-0.0.1-SNAPSHOT.jar /usr/src/myapp/myapp.jar
CMD ["java","-jar","/usr/src/myapp/myapp.jar"]
EXPOSE 8080