# File Transfer
- openapi 3.0 entegre edildi dokumantasyon için /fit-doc.html

# db
- dev. db --> "sudo docker run --name ft-postgres -e POSTGRES_PASSWORD=pass -d -p 5432:5432 postgres" 
- dev. pgadmin4 --> "sudo docker run -p 9001:80 --name pdadmin4 -e "PGADMIN_DEFAULT_EMAIL=admin" -e "PGADMIN_DEFAULT_PASSWORD=0" -d dpage/pgadmin4"
- schema --> "file_transfer"
- extension --> "CREATE EXTENSION IF NOT EXISTS "uuid-ossp";"

# How to Dockerize Spring Boot Application
Build Docker Image
$ docker build -t file-transfer .

Check Docker Image
$ docker image ls

Run Docker Image
$ docker run -p 8090:8090 file-transfer

# stack
- basic auth
- Async, Scheduler
- jpa, hibernate, envers
- MapStruct
- lombok
- liquibase
- hazelcast
- activemq
- postgres
- docker
- gradle
- pmd, checkstyle
- swagger