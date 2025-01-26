call mvnw clean package
call docker image rm -f gateway-image
call docker build -t gateway-image .
call docker container rm -f gateway-container
call docker run --name gateway-container -d -p 8080:8080 --network computerShop gateway-image
