#AA File Transfer
- openapi 3.0 entegre edildi dokumantasyon için /aa-fts-doc.html

How to Dockerize Spring Boot Application
Build Docker Image
$ docker build -t aa-file-transfer .

Check Docker Image
$ docker image ls

Run Docker Image
$ docker run -p 8090:8090 aa-file-transfer

Özet
docker run -d -p 3306:3306 --name=mysql-server --env="MYSQL_ROOT_PASSWORD=root123" --env="MYSQL_DATABASE=yonetim" mysql
./gradlew clean build && docker build -t yonetim .
docker run --name yonetimCont -d --link mysql:db -p 8080:8080 yonetim