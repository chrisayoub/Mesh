#!/usr/bin/env bash

# mvn package

java -Dorg.lejos.jniloader.basedir=/opt/lejos/build/libnxt -jar nxj-app.jar
