FROM arm64v8/maven as builder

WORKDIR /usr/src/app
COPY . .
RUN mvn clean install

############################################

FROM eclipse-temurin:17-jre-jammy

COPY --from=builder /usr/src/app/target/*.jar /usr/app/*.jar

WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "/usr/app/*.jar"]