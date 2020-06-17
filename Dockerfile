FROM openjdk:14
MAINTAINER tkeskin
EXPOSE 8090
ADD ./build/libs/file-transfer.jar file-transfer.jar
ENTRYPOINT ["java","-jar","/file-transfer.jar"]