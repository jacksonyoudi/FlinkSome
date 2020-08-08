package org.youdi.bigdata.chap01

import org.apache.flink.api.scala._

object WC {
  def main(args: Array[String]): Unit = {
    // 创建一个执行批处理环境
    val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    val value: DataSet[String] = environment.readTextFile("/Users/youdi/project/javaproject/FlinkProject/src/main/resources/word.txt")

    //    value.print()


    val value1: AggregateDataSet[(String, Int)] = value.flatMap(_.split(" "))
      .map((_, 1))
      .groupBy(0) // 第一个元素作为 key
      .sum(1) // 第二个元素值 sum
    value1.print()

//    environment.execute("wc")
  }
}
