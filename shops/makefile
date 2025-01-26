# Windows edition
# .\mvnw spring-boot:run
# .\mvnw clean package
#  C:\Users\milos\.jdks\openjdk-23.0.1\bin\java.exe -jar .\target\shops-0.0.1-SNAPSHOT.jar

package:
	mvn clean package

build:
	docker build -t shops-image .

run:
	docker run --name shops-container -d -p 8082:8082 shops-image

stop:
	docker stop shops-container

createNetwork:
	docker network create computerShop
