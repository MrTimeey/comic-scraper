FROM adoptopenjdk/maven-openjdk11 as build-stage

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build

RUN mvn install -DskipTests

FROM adoptopenjdk/openjdk11
VOLUME /tmp

WORKDIR /app

COPY --from=build-stage /build/target/*.jar /app/app.jar

EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
