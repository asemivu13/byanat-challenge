FROM eclipse-temurin:17-jdk-jammy

VOLUME /tmp

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]