#!/bin/bash
./gradlew clean gatlingRun-simulations.GatlingSampleSimulation \
-DUSERS=10 \
-DBASE_URL=https://baidu.com \
-DDURATION=10
