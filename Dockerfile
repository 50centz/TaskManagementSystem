FROM bellsoft/liberica-openjdk-alpine:21.0.1-12-cds-x86_64
COPY /target/TaskManagementSystem-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
CMD ["java", "-jar", "TaskManagementSystem-0.0.1-SNAPSHOT.jar"]
