#!/bin/sh

BASE_HOME=/Users/rommelsumpo

                                                                                        
echo "   ██████╗ ██████╗ ███╗   ██╗███╗   ██╗███████╗ ██████╗████████╗ ██████╗ ██████╗  "
echo "  ██╔════╝██╔═══██╗████╗  ██║████╗  ██║██╔════╝██╔════╝╚══██╔══╝██╔═══██╗██╔══██╗ "
echo "  ██║     ██║   ██║██╔██╗ ██║██╔██╗ ██║█████╗  ██║        ██║   ██║   ██║██████╔╝ "
echo "  ██║     ██║   ██║██║╚██╗██║██║╚██╗██║██╔══╝  ██║        ██║   ██║   ██║██╔══██╗ "
echo "  ╚██████╗╚██████╔╝██║ ╚████║██║ ╚████║███████╗╚██████╗   ██║   ╚██████╔╝██║  ██║ "
echo "   ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═══╝╚══════╝ ╚═════╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝ "
echo " Correspondence Service << SpringBoot >>"

success=0
codebase="$BASE_HOME/Development/connectors-springboot"
codebasesrc="${codebase}/connector-correspondence-service/script/codebase.src"


while IFS='' read -r line || [[ -n "$line" ]]; do
	source="${codebase}/${line}"

	echo "		code base:$source started .. "
    cd $source
    
    #checking code standard
    echo "validating codebase {checkstyle}..."
	mvn  checkstyle:check
	status=$?
	if [ $status -eq 1 ]; then
		success=1
		break
    fi
    echo "validating codebase {pmd}..."
	mvn pmd:check 
	status=$?
	if [ $status -eq 1 ]; then
   		success=1
		break
    fi
    	
    mvn clean test -Dmaven.test.skip=true
    status=$?
	if [ $status -eq 0 ]; then
		mvn package install -Dmaven.test.skip=true
	else
		success=1
		break
	fi
  	echo "		code base:$source done ..  "
done < "$codebasesrc"

if [ $success -eq 0 ]; then

 	echo "---------------------------------------------------"
	echo "<Connector-Correspondence-Service>  compiled successful ..."
	echo "---------------------------------------------------"
	
	target="${codebase}/connector-correspondence-service/docker/connector"
	source="${codebase}/connector-correspondence-service/target/connector-correspondence-service.war"
	cp $source $target
	target="${codebase}/connector-deploy/connector-correspondence-service"
	cp $source $target
	
else
	echo "****************** ERROR {Connector failed} ******************"
fi

