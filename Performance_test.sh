#!/bin/bash

GATLING_ROOT_PATH_PREFIX=`./start.gatling.rootPathPrefix.sh`

GATLING_ROOT_PATH_PREFIX=$GATLING_ROOT_PATH_PREFIX  ./gradlew clean gatlingRun-simulations.AlaaSInvoiceOcrPerfSimulation -DUSERS=50 -DBASE_URL=http://open-ai-aiaas.uat.homecreditcfc.cn -DDURATION=60
