FROM maven:3.8.3 AS base
COPY . ./
RUN mvn clean install

FROM openjdk:17 AS final
EXPOSE 8080
COPY --from=base target/simple.project-0.0.1-SNAPSHOT.jar snapshot.jar 
ENTRYPOINT ["java","-jar","snapshot.jar"]
