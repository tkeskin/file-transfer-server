FROM openjdk:14
MAINTAINER tkeskin
EXPOSE 8090
ADD ./build/libs/aa-file-transfer.jar aa-file-transfer.jar
ENTRYPOINT ["java","-jar","/aa-file-transfer.jar"]