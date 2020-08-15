package org.youdi.bigdata.chap02

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

object KafkaS {
  def main(args: Array[String]): Unit = {
    // env
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    //    env.setStreamTimeCharacteristic()
    //    env.enableCheckpointing(1000)
    //    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)

    // source
    val properties: Properties = new Properties()
    properties.setProperty("zookeeper.connect", "localhost:2181")
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "flink")

    val stream: DataStream[String] = env.addSource(
      new FlinkKafkaConsumer("youdi", new SimpleStringSchema(), properties)
    )

    stream.flatMap(_.split(" "))
      .map((_, 1))
      .keyBy(0)
      .sum(1).print()


    env.execute("kafka streaming")

  }
}
