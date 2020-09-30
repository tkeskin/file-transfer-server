FROM openjdk:14
MAINTAINER tkeskin
EXPOSE 8090
ADD ./build/libs/file-transfer-server.jar file-transfer-server.jar
ENTRYPOINT ["java","-jar","/file-transfer-server.jar"]