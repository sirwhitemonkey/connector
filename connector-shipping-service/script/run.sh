#!/bin/sh
# Settings - main

case "$1" in
		start)
		java -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -XX:+UseConcMarkSweepGC -Xms1024m -Xmx1024m -XX:-UseGCOverheadLimit -Xss512k -XX:NewRatio=3 -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Djboss.as.management.blocking.timeout=6000 -Djdk.nio.maxCachedBufferSize=262144 -jar target/connector-shipping-service.war > connector-shipping-service.log &
		;;
		stop)
		pkill -f "connector-shipping-service.war"
		;;
		log)
		tail -f connector-shipping-service.log
		;;
		*)
		echo "Usage: {start|stop|log}"
		;;
esac
		

