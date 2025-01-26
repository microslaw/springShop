call mvnw clean package
call docker image rm -f gateway-image
call docker build -t gateway-image .
