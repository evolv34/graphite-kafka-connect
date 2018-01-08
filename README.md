## Kafka-Connect TimeSeries Database

This is a generic kafka connect for timeseries database. Currently it supports graphite database.

### Note: 
* For mac users **docker for mac** is suggested

### Prerequisites
* docker-compose: 1.18.0
* docker-engine: 17.09.1-ce
* sbt: 0.14.0
* confluent-platform: 4.0.0

### How to execute

* Build the plugin jar `sbt build-complete`
* Start graphite database docker container. 
* After successful build use the properties from `src/main/resources/kafka-connect-graphite.properties` and execute it in `connect-standalone` mode
* For distributed mode use `src/main/resources/graphite-kafka-connect-distributed.json`.
* Build the container using `docker-compose -f docker/docker-compose.yml up --build`
* Deploy the plugin using the sample json available in `graphite-kafka-connect-distributed.json` through POST call.

### Future Scope

* Implement support for other timeseries database
* Support Protobuf.

### How to commit

Create issue if some thing fails.


