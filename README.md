#AA File Transfer
- openapi 3.0 entegre edildi dokumantasyon için /aa-fts-doc.html

How to Dockerize Spring Boot Application
Build Docker Image
$ docker build -t aa-file-transfer .

Check Docker Image
$ docker image ls

Run Docker Image
$ docker run -p 8090:8090 aa-file-transfer
