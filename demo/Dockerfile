FROM openjdk:11-ea-jre

#EXPOSE 8080

RUN mkdir /opt/app
RUN mkdir /opt/logs
COPY ./demo.jar /opt/app

WORKDIR /opt/app
ENTRYPOINT ["java", "-jar", "demo.jar", "-Xms128m", "-Xmx256m"]