# Стадия сборки
FROM maven:3.8.7-eclipse-temurin-17 AS build-stage

LABEL author="Шумахер Марк Евгеньевич"
LABEL group="ИКБО-20-22"

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y wget && \
    wget -O src/main/resources/static/gerb.png https://www.mirea.ru/upload/medialibrary/80f/MIREA_Gerb_Colour.png

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

ENV DB_PORT=5432 \
    DB_HOST=postgres \
    DB_NAME=mirea_db \
    DB_USER=user \
    DB_PASSWORD=password \
    APP_PORT=8080

EXPOSE $APP_PORT

WORKDIR /app

COPY --from=build-stage /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

CMD ["--spring.profiles.active=prod"]

ONBUILD RUN echo "Сборка и запуск произведены. Автор: Шумахер Марк Евгеньевич"

