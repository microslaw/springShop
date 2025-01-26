call mvnw clean package
call docker image rm -f shops-image
call docker build -t shops-image .
call docker container rm -f shops-container
call docker run --name shops-container -d -p 8082:8082 --network computerShop shops-image
