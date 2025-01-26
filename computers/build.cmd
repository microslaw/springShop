call mvnw clean package
call docker image rm -f computers-image
call docker build -t computers-image .
