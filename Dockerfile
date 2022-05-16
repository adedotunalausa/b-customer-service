FROM adoptopenjdk/openjdk11:alpine-jre

VOLUME /tmp

EXPOSE 8889

ADD target/*.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]