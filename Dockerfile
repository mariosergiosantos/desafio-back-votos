FROM openjdk:8-jre-alpine

RUN mkdir /app
WORKDIR /app

RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
RUN chown -R javauser:javauser /app
USER javauser

COPY /target/*.jar /app/application.jar
COPY /.docker/entrypoint.sh /app

ENTRYPOINT [ "sh", "/app/entrypoint.sh" ]
CMD [ "/bin/bash" ]

EXPOSE 8080