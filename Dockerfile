FROM openjdk:8-jdk-alpine


ARG JAR_FILE=target/order-service-dev-build.jar

# cd /opt/app
WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]