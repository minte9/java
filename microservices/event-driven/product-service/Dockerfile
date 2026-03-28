# product-service/Dockerfile

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 7001

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
version: '3.8'

services:

  oracle-db:
    image: gvenzl/oracle-xe:21-slim
    container_name: oracle-xe
    ports:
      - "1522:1521"
    environment:
      ORACLE_PASSWORD: oracle
      APP_USER: appuser
      APP_USER_PASSWORD: apppass
    volumes:
      - oracle-data:/opt/oracle/oradata
    networks:
      - app-network

  spring-app:
    build: .
    container_name: spring-oracle-app
    ports:
      - "8085:8080"
    depends_on:
      - oracle-db
    environment:
      SPRING_DATASOURCE_URL: jdbc:oracle:thin:@oracle-db:1521/XEPDB1
      SPRING_DATASOURCE_USERNAME: appuser
      SPRING_DATASOURCE_PASSWORD: apppass
    networks:
      - app-network

volumes:
  oracle-data:

networks:
  app-network: