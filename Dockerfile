FROM java:8
VOLUME /tmp
ADD target/todotasklist-assignment.jar target/app.jar
EXPOSE 8080
RUN bash -c 'touch target/app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/test", "-Djava.security.egd=file:/dev/./urandom","-jar","target/app.jar"]

