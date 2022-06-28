FROM maven:3.8-jdk-11
COPY . .
CMD mvn clean install
CMD mvn spring-boot:run