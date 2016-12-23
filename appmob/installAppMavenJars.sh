#!/bin/bash

rm -r $MAVEN_HOME/.m2/repository/athena/athena*
rm -r $MAVEN_HOME/.m2/repository/spartan/spartan*
mkdir $MAVEN_HOME/appJars
CURRENT_DIR="$(dirname $0)"
cp $CURRENT_DIR/athena/*.jar $MAVEN_HOME/appJars
cp $CURRENT_DIR/spartan/*.jar $MAVEN_HOME/appJars

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/athena-cloud-1.0.jar -DgroupId=athena -DartifactId=athena-cloud -Dversion=1.0 -Dpackaging=jar
echo "athena maven jar athena-cloud installed"

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/athena-config-1.0.jar -DgroupId=athena -DartifactId=athena-config -Dversion=1.0 -Dpackaging=jar
echo "athena maven jar athena-config installed"

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/athena-core-1.0.jar -DgroupId=athena -DartifactId=athena-core -Dversion=1.0 -Dpackaging=jar
echo "athena maven jar athena-core installed"

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/athena-data-endpoint-orchestration-1.0.jar -DgroupId=athena -DartifactId=athena-data-endpoint-orchestration -Dversion=1.0 -Dpackaging=jar
echo "athena maven jar athena-data-endpoint-orchestration installed"

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/athena-pluggable-1.0.jar -DgroupId=athena -DartifactId=athena-pluggable -Dversion=1.0 -Dpackaging=jar
echo "athena maven jar athena-pluggable installed"

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/athena-data-engine-1.0.jar -DgroupId=athena -DartifactId=athena-data-engine -Dversion=1.0 -Dpackaging=jar
echo "athena maven jar athena-data-engine installed"

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/athena-search-engine-1.0.jar -DgroupId=athena -DartifactId=athena-search-engine -Dversion=1.0 -Dpackaging=jar
echo "athena maven jar athena-search-engine installed"
echo "Completed installing athena dependencies...."


echo "Starting installation of spartan dependencies...."

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/spartan-healthmeter-1.0.jar -DgroupId=spartan -DartifactId=spartan-healthmeter -Dversion=1.0 -Dpackaging=jar
echo "spartan maven jar spartan-healthmeter installed"

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/spartan-auth-1.0.jar -DgroupId=spartan -DartifactId=spartan-auth -Dversion=1.0 -Dpackaging=jar
echo "spartan maven jar spartan-auth installed"

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/spartan-logger-1.0.jar -DgroupId=spartan -DartifactId=spartan-logger -Dversion=1.0 -Dpackaging=jar
echo "spartan maven jar spartan-logger installed"

mvn install:install-file -Dfile=$MAVEN_HOME/appJars/spartan-pluggable-1.0.jar -DgroupId=spartan -DartifactId=spartan-pluggable -Dversion=1.0 -Dpackaging=jar
echo "spartan maven jar spartan-pluggable installed"
echo "Completed installing spartan dependencies...."