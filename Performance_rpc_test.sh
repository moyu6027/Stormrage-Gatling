#!/bin/bash

GATLING_ROOT_PATH_PREFIX=`./start.gatling.rootPathPrefix.sh`

GATLING_ROOT_PATH_PREFIX=$GATLING_ROOT_PATH_PREFIX  ./gradlew clean gatlingRun-simulations.PingPongSimulation
