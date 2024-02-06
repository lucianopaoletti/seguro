# Stage Build
FROM node:18-alpine as node
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm i
COPY . .
ARG configuration=docker
RUN npm run build -- --output-path=./dist/out --configuration ${configuration}

# Stage Run
FROM nginx:1.25-alpine
COPY --from=node /usr/src/app/dist/out /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
