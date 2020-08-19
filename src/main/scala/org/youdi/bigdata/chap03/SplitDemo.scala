package org.youdi.bigdata.chap03

import org.apache.flink.streaming.api.scala._
import org.youdi.bigdata.chap02.SensorReading

object SplitDemo {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    val value: DataStream[String] = env.readTextFile("")
    val senStreaming: DataStream[SensorReading] = value.map(s => {
      val strings: Array[String] = s.split(",")
      SensorReading(strings(0), strings(1).toLong, strings(2).toFloat)
    })
    senStreaming.keyBy("id")
      .sum("temperature")
  }
}


