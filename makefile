build_computers:
	cd computers && \
	mvn clean package && \
	docker build -t computers-image .

build_shops:
	cd shops && \
	mvn clean package && \
	docker build -t shops-image .

build_gateway:
	cd gateway && \
	mvn clean package && \
	docker build -t gateway-image .

build_frontend:
	cd frontend && \
	npm run build && \
	docker build -t frontend-image .

build:
	make build_computers
	make build_shops
	make build_gateway
	make build_frontend
