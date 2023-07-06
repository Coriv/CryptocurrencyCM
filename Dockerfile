FROM openjdk:17-oracle
RUN apt-get update && apt-get install -y mysql-client
ENV DB_HOST=mysql-container
ENV DB_PORT=3306
ENV DB_NAME=cryptocurrency
ENV DB_USER=crypto_user
ENV DB_PASSWORD=crypto_pass
WORKDIR /app
COPY . .
RUN ./gradlew build
CMD ["java", "-jar", "build/libs/CyptocurrencyApp-0.0.1-SNAPSHOT.jar", "--spring.cloud.config.uri=http://config:8888", "--eureka.client.serviceUrl.defaultZone=http://configUser:configPass@discovery:8082/eureka/"]