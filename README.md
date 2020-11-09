Stormrage-Gatling performance tests
=========================
Fully parametrized performance scenarios based on Gatling with Scala.

<h3>Usage</h3>
1. execute command with sample parametrization:

    `./gradlew clean gatlingRun-simulations.GatlingSampleSimulation \
    -DUSERS=10 \
    -DBASE_URL=https://baidu.com \
    -DDURATION=10`

2. see the report on build/reports/gatling

<h3>Structure</h3>
In stormrage-gatling
1. grafana - docker-compose file for grafana Configuration
2. influxdb - docker-compose file for influxdb Configuration
3. redis_feeder - docker-compose file for redis feeder server Configuration
4. src - gatling Framework code

In Src
1. resources - bodies(body json), data(csv feeder data), gatling.conf(gatling Configuration), logback-test.xml(log level)
2. simulations(Performance test SCENARIOS), singleObjects(http common), util(common function), demo(demo http request)


<h3>To do in the future:</h3>

* Add more steps
* Implement realtime solution with `InfluxDb` and `Grafana`
* Load tests analysis with aggregated data in `InfluxDb` displayed in comprehensive reports generated with `Grafana`
* Handle comparison in `Grafana` with `JavaScript`
