# Windows edition
# .\mvnw spring-boot:run
# .\mvnw clean package
#  C:\Users\milos\.jdks\openjdk-23.0.1\bin\java.exe -jar .\target\gateway-0.0.1-SNAPSHOT.jar

package:
	mvn clean package

build:
	docker build -t gateway-image .

run:
	docker run --name gateway-container -d -p 8080:8080 gateway-image

stop:
	docker stop gateway-container

createNetwork:
	docker network create computerShop
