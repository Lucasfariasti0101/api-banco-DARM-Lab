FROM maven:3.8.6-amazoncorretto-17

WORKDIR /api-banco
COPY . .
RUN mvn clean install -DskipTests

CMD mvn spring-boot:run