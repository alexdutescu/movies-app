FROM openjdk:17
EXPOSE 8763
WORKDIR /root/movies-app
COPY movie-booking/target/movie-booking-1.0-SNAPSHOT.jar .
CMD ["java", "-Dspring.profiles.active=docker","-jar","movie-booking-1.0-SNAPSHOT.jar"]
