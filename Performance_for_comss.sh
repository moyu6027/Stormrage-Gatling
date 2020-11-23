#!/bin/bash

./gradlew clean gatlingRun-simulations.ComssUploadObjectSimulation \
-DUSERS=3 \
-DBASE_URL=http://agent-web.sit.hccn \
-DDURATION=1