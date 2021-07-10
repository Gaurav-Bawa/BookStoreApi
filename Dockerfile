FROM openjdk:8
EXPOSE 8080
ADD target/book-store-api.jar book-store-api.jar
ENTRYPOINT ["java","-jar","/book-store-api.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]