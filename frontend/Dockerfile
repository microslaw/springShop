FROM nginx:1.23.3
COPY docker/etc/nginx/templates /etc/nginx/templates
COPY dist /usr/share/nginx/html
ENV API_URL=http://localhost:8080/api

RUN apt update
