Stormrage-Gatling performance tests
=========================
Fully parametrized performance scenarios based on Gatling with Scala.

<h3>Usage</h3>
1. execute command with sample parametrization:

    `./gradlew clean gatlingRun-simulations.CloseModel_ConstantConcurrentUsers \
    -DUSERS=10 \
    -DBASE_URL=http://XXXX.com \
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

<h3>To do in the future:</h3>

* Add more steps
* Implement realtime solution with `InfluxDb` and `Grafana`
* Load tests analysis with aggregated data in `InfluxDb` displayed in comprehensive reports generated with `Grafana`
* Handle comparison in `Grafana` with `JavaScript`
