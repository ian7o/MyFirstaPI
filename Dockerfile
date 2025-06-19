FROM eclipse-temurin:23-jdk AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package


FROM eclipse-temurin:23-jdk

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/rentsandclients-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java","-jar","app.jar"]