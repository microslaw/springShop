call npm run build
call docker image rm -f frontend-image
call docker build -t frontend-image .
