call mvnw clean package
call docker image rm -f shops-image
call docker build -t shops-image .
