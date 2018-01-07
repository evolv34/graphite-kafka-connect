FROM confluentinc/cp-kafka-connect:4.0.0

ADD /target/scala-2.12/kafka-connect-timeseriesdb-assembly-1.0.jar usr/share/java/kafka-connect-timeseriesdb/kafka-connect-timeseriesdb-1.0.jar