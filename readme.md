# This is CKIB SandBox uploader demo app
*H2 DB прикручена, но пока не используется* \
*Upload from file просто загружает файл на сервер. Сканирование не запускается.*
## Deploy:

`mvn clean package -DskipTests=true` \
`docker build -t ckib_sandbox .` \
`docker run --name sndbx -d -p 8081:8081 ckib_sandbox`

## Docker push
```
docker login
docker tag ckib_sandbox:latest fynjy/ckib_sandbox:0.1
docker push fynjy/ckib_sandbox:0.1
```