#node
FROM node:6.9.2

MAINTAINER eCommerceConnectorTeam

#apt
RUN apt-get update
#gulp
RUN npm install gulp -g

#create dir
RUN mkdir -p /connector/app
RUN mkdir -p /connector/swagger

#set dir
WORKDIR /connector

#copy modules
COPY Gulpfile.js .
COPY package.json .
COPY server.js .
COPY app app
COPY swagger swagger

#install dependencies
RUN npm install

#replace host
RUN gulp replace-host --source connectors.xmdevelopments.net

#export port
EXPOSE 3002

#run node
CMD node server.js

