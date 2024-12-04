# Etapa de build
FROM maven:3.8.5-openjdk-17 as builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

RUN ls -al /app/target

# Etapa de execução
FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=builder /app/target/sistemaReserva-0.0.1-SNAPSHOT.jar app.jar

RUN ls -al /app.jar  # Verifique se o arquivo JAR foi copiado corretamente

ENTRYPOINT ["java", "-jar", "app.jar"]
