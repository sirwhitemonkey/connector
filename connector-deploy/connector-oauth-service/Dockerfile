#java
FROM java:openjdk-8-jdk

MAINTAINER eCommerceConnectorTeam

#create dir
RUN mkdir -p /connector

#set dir
WORKDIR /connector

#copy module
COPY connector-oauth-service.war .
COPY server.log .

#export port
EXPOSE 2088

#deploy
CMD java -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -XX:+UseConcMarkSweepGC -Xms1024m -Xmx1024m -XX:-UseGCOverheadLimit -Xss512k -XX:NewRatio=3 -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Djboss.as.management.blocking.timeout=6000 -Djdk.nio.maxCachedBufferSize=262144 -jar connector-oauth-service.war

