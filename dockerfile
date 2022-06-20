FROM openjdk:8-jre
RUN mkdir app
ARG JAR_FILE
ADD /
WORKDIR /app
ENTRYPOINT ??