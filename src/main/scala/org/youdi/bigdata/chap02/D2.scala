package org.youdi.bigdata.chap02


import org.apache.flink.streaming.api.scala._


object D2 {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    //    StreamExecutionEnvironment.createLocalEnvironment(1)
    //      StreamExecutionEnvironment.createRemoteEnvironment()


    // 从集合中读取数据

    val value: DataStream[SensorReading] = env.fromCollection(List(
      SensorReading("sensor_01", 1597239592, 35.8),
      SensorReading("sensor_01", 1598239592, 35.1),
      SensorReading("sensor_01", 1597439592, 35.2),
      SensorReading("sensor_01", 1597239592, 35.3),
      SensorReading("sensor_01", 1597239592, 35.8),
      SensorReading("sensor_02", 1597239392, 15.8),
      SensorReading("sensor_03", 1597239509, 3.8),
      SensorReading("sensor_04", 1597239500, 6.5),
      SensorReading("sensor_05", 1597139592, 4.5),
    ))
    // 打印输出
    value.print


    // 从文件中读取文件
    val txtStreaming: DataStream[String] = env.readTextFile("/Users/youdi/project/javaproject/FlinkSome/src/main/resources/word.txt")
    txtStreaming.print


    env.execute("stream")
  }
}
