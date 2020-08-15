package org.youdi.bigdata.chap02

import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala._

import scala.collection.immutable
import scala.util.Random

object CustomSource {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val senStreaming: DataStream[SensorReading] = env.addSource(new MySensorSource())
    senStreaming.print
    env.execute("customSource")


  }
}


// 实现一个自定义的sourceFunction， 自动生成测试数据
class MySensorSource() extends SourceFunction[SensorReading] {

  // 定义一个flag， 表示数据源是否正常运行
  var running: Boolean = true

  override def run(sourceContext: SourceFunction.SourceContext[SensorReading]): Unit = {
    //  随机生成
    val rd: Random = new Random()

    val tmps: immutable.IndexedSeq[(String, Double)] = 1.to(10).map(
      i => ("sensor_" + i, 60 + rd.nextGaussian() * 20)
    )

    // 无限循环
    while (running) {
      // 随机生成微小波动
      val curTemps: immutable.IndexedSeq[(String, Double)] = tmps.map(
        data => (data._1, data._2 + rd.nextGaussian())
      )


      val curTs: Long = System.currentTimeMillis()
      curTemps.foreach(
        data => sourceContext.collect(SensorReading(data._1, curTs, data._2))
      )
      Thread.sleep(1000L)
    }

  }

  override def cancel(): Unit = {
    running = false
  }
}
