FROM maven:3.9.4-amazoncorretto-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package
RUN java -Djarmode=layertools -jar target/*.jar extract

FROM amazoncorretto:21-alpine-jdk AS production
WORKDIR /app
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/application/ ./
RUN apk --no-cache add curl jq
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]