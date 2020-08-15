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

    //    env.disableOperatorChaining()
    // source
    val properties: Properties = new Properties()
    properties.setProperty("zookeeper.connect", "localhost:2181")
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "flink")
    properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserialer")
    properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserialer")
    properties.setProperty("auto.offset.reset", "latest")


    val stream: DataStream[String] = env.addSource(
      new FlinkKafkaConsumer("youdi", new SimpleStringSchema(), properties)
    )

    stream.
      flatMap(_.split(" "))
      .filter(_.nonEmpty).disableChaining() // 拒绝合并
      .map((_, 1)).slotSharingGroup("1")
      .keyBy(0) //.startNewChain()
      .sum(1).slotSharingGroup("2")
      .print()


    env.execute("kafka streaming")

  }
}
