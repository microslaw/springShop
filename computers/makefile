# Windows edition
# .\mvnw spring-boot:run
# .\mvnw clean package
#  C:\Users\milos\.jdks\openjdk-23.0.1\bin\java.exe -jar .\target\computers-0.0.1-SNAPSHOT.jar

package:
	mvn clean package

build:
	docker build -t computers-image .

run:
	docker run --name computers-container -d -p 8083:8083 computers-image

stop:
	docker stop computers-container
