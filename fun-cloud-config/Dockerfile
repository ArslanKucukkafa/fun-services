# === Build aşaması ===
FROM eclipse-temurin:24-jdk AS builder

ARG JAR_FILE=fun-cloud-config-0.0.1-SNAPSHOT.jar
WORKDIR /build
COPY target/${JAR_FILE} app.jar

# === Runtime aşaması ===
FROM eclipse-temurin:24-jre

WORKDIR /app
COPY --from=builder /build/app.jar fun-config-service.jar

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "fun-config-service.jar"]
