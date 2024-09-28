FROM openjdk:17
EXPOSE 8761
WORKDIR /root/movies-app
COPY eureka-movies-server/target/server-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","server-0.0.1-SNAPSHOT.jar"]
