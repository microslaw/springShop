call mvnw clean package
call docker image rm -f computers-image
call docker build -t computers-image .
call docker container rm -f computers-container
call docker run --name computers-container -d -p 8083:8083 --network computerShop computers-image
