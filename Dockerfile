FROM maven:3-eclipse-temurin-17-alpine as builder

WORKDIR /usr/src

COPY . .

RUN mvn package -DskipTests
RUN cp target/*.jar target/jadore.jar

FROM eclipse-temurin:17-jre-jammy


COPY Dockerfile .

RUN apt-get update && apt-get install -y nano telnet curl bash postgresql-client wget cron
RUN ln -sf /bin/bash /bin/sh

WORKDIR /home/user

COPY entrypoint.sh .

RUN chmod +x /home/user/entrypoint.sh

COPY --from=builder /usr/src/target/jadore.jar .

EXPOSE 8080

ENTRYPOINT ["/bin/sh","/home/user/entrypoint.sh"]


