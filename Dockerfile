FROM node:lts-alpine

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY ./dist .

EXPOSE 8060

RUN npm run serve --port 8060
