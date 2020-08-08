package org.youdi.bigdata.chap01

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala._
//import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object WCStreaming {
  def main(args: Array[String]): Unit = {
    print(args.mkString(" "))
    val paras: ParameterTool = ParameterTool.fromArgs(args)


    val host: String = paras.get("hostname")
    val port: Int = paras.getInt("port")

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(8) // 设置并行度

    val ds: DataStream[String] = env.socketTextStream(host, port)


    //      flink每一步都可以设置 并行度 和spark不同
    ds.flatMap(_.split(" "))
      .map((_, 1))
      .keyBy(0) // 按照第一个字段
      .sum(1)
      .print.setParallelism(1)

    env.execute("wc string")
  }
}
