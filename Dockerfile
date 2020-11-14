FROM openjdk:14-alpine

WORKDIR /opt

# add project files
COPY gradlew .
COPY gradle gradle/

RUN addgroup -S spring && adduser -S spring -G spring

RUN chown spring:spring .
USER spring:spring

RUN ./gradlew # makes sure to download gradle

COPY *.gradle ./
COPY src src/

RUN ./gradlew --no-daemon bootJar # build jar

# entry
RUN cp build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
