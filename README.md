## Kafka-Connect TimeSeries Database

This is a generic kafka connect for timeseries database. Currently it supports graphite database.

### Prerequisites
* docker-compose: 1.18.0
* docker-engine: 17.09.1-ce
* sbt: 0.14.0
* confluent-platform: 4.0.0

### How to execute

* To build the plugin jar `sbt build-complete`
* Start graphite database docker container. 
* After successful build use the properties from `src/main/resources/kafka-connect-graphite.properties` and execute it in `connect-standalone` mode
* For distributed mode use `src/main/resources/graphite-kafka-connect-distributed.json`.

### Future Scope

* Implement support for other timeseries database
* Support Protobuf.
* dockerize the application

### How to commit

Create issue if some thing fails.

