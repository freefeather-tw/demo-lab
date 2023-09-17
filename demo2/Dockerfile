FROM openjdk:11-ea-jre

#EXPOSE 8282

RUN mkdir /opt/app
RUN mkdir /opt/logs
COPY ./demo2.jar /opt/app

WORKDIR /opt/app
ENTRYPOINT ["java", "-jar", "demo2.jar", "-Xms128m", "-Xmx256m"]