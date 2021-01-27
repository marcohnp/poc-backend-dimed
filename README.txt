Para inicializar o Docker é necessário utilizar os comandos abaixo no terminal:
gradle build
docker build -f Dockerfile -t dimedbackend .
docker-compose up

Os endpoints estão documentandos na API do Swagger UI e podem ser visualizados em:
http://localhost:8080/swagger-ui.html



