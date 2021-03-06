![Gatlinglogo](Gatling.png "Gatling logo")

Stormrage-Gatling performance tests
=========================
Fully parametrized performance scenarios based on Gatling with Scala.

<h3>Usage</h3>
1. execute command with sample parametrization:

    `gradle clean gatlingRun-simulations.CloseModel_ConstantConcurrentUsers \
    -DUSERS=10 \
    -DBASE_URL=http://XXXX.com \
    -DDURATION=10`

2. see the report on build/reports/gatling

<h3>Structure</h3>

In stormrage-gatling
1. grafana - docker-compose-monitor file for grafana Configuration
2. influxdb - docker-compose-monitor file for influxdb Configuration
3. redis_feeder - docker-compose file for redis feeder server Configuration
4. src - gatling Framework code
5. gatling - demo for docker-compose
6. grafana_gatling - docker-compose file for grafana Configuration
7. influxdb_gatling - docker-compose file for influxdb Configuration
8. loki_gatling - docker-compose file for Loki Configuration include promtail config
9. mmock - Monster-Mock Server Configuration
10. nginx - Nginx Configuration for Gatling Report
11. prometheus - docker-compose file for Prometheus Configuration

In Src
1. resources - bodies(body json), data(csv feeder data), gatling.conf(gatling Configuration), logback-test.xml(log level), simulation.conf(user configuration files)
2. simulations(Performance test setup and workload model), singleObjects(http common), util(common function), cases(http request api), scenario(Process scenario)

The lifecycle is as below:

1. Gatling starts
2. Simulation instance is created and all code not delayed in `before` and `after` hooks is execute (code inside a Scala class body is its constructor)
3. `before` hook is executed
4. Simulation runs
5. Simulation terminates
6. `after` hook is executed
7. HTML reports are generated if enabled
8. Gatling shuts down

<h3>Open vs Closed Workload Models</h3>
When it comes to load model, systems behave in 2 different ways:

1. Closed systems, where you control the concurrent number of users
2. Open systems, where you control the arrival rate of users

Make sure to use the proper load model that matches the load your live system experiences.

<h3>Stormrage-Gatling Cloud Architecture Diagram</h3>
![Gatling](Stormrage-Gatling%20Cloud.png "Gatling Architecture")

<h3>To do in the future:</h3>

* Add more steps
* Implement realtime solution with `InfluxDb` and `Grafana`
* Load tests analysis with aggregated data in `InfluxDb` displayed in comprehensive reports generated with `Grafana`
* Handle comparison in `Grafana` with `JavaScript`
* Load tests logs with `Loki`
* Aggregated library with GRPC,MQTT etc
