call npm run build
call docker image rm -f frontend-image
call docker build -t frontend-image .
call docker container rm -f frontend-container
call docker run --name frontend-container -d -p 4200:4200 --network computerShop frontend-image
