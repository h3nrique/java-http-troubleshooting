FROM maven:3-openjdk-8 as build
LABEL mantainer="Paulo Henrique Alves"
RUN mkdir -p /usr/src/fabricads
WORKDIR /usr/src/fabricads
COPY . /usr/src/fabricads
RUN mvn clean install

FROM openjdk:8
LABEL mantainer="Paulo Henrique Alves"
ENV TZ=America/Sao_Paulo
EXPOSE 8080
ENV APP_HOME /opt
WORKDIR /opt
COPY --from=build /usr/src/fabricads/target/java-http-troubleshooting.jar /opt/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/java-http-troubleshooting.jar"]
