# Dockerfile.service2
FROM node:20

WORKDIR /appnode

COPY . .

ENV PATH /app/node_modeules/.bin:$PATH
RUN npm update

EXPOSE 3001
CMD npm start
