#!/bin/bash
./gradlew clean gatlingRun-simulations.AlaaSAsyncPerfSimulation \
-DUSERS=35 \
-DBASE_URL=http://open-ai-gateway.uat.homecreditcfc.cn \
-DDURATION=30 \
-DIMAGE_KEY="ocr-image-shopping"
