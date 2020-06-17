#AA File Transfer
- openapi 3.0 entegre edildi dokumantasyon için /fit-doc.html

## db
- file_transfer schema oluşturulacak.
- extension için CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; çalıştırılacak.

How to Dockerize Spring Boot Application
Build Docker Image
$ docker build -t file-transfer .

Check Docker Image
$ docker image ls

Run Docker Image
$ docker run -p 8090:8090 file-transfer
