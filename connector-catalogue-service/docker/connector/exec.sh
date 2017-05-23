#!/bin/sh
# Settings - main
export service_module=/service/connector-catalogue-service.war
export service_log=/service/connector-catalogue-service.log

case "$1" in
		start)
		exec java -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -XX:+UseConcMarkSweepGC -Xms1024m -Xmx1024m -XX:-UseGCOverheadLimit -Xss512k -XX:NewRatio=3 -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Djboss.as.management.blocking.timeout=6000 -Djdk.nio.maxCachedBufferSize=262144 -jar "$service_module" > "$service_log"
		;;
		stop)
		pkill -f "connector-catalogue-service.war"
		;;
		log)
		tail -f connector-catalogue-service.log
		;;
		*)
		echo "Usage: {start|stop|log}"
		;;
esac
		
