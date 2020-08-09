package org.youdi.bigdata.chap02


/**
 * 运行时的组件
 * 1. 作业管理器 jobManager
 * 2. 任务管理器 taskManager
 * 3. 资源管理器 ResouseManager
 * 4. 分发器 dispatchingManager
 *
 * 资源管理器 ResouseManager
 * taskManager的slot处理的资源单元
 *
 *
 * 任务提交流程
 * dispatch -> taskManager-> jobManager
 *
 *
 * taskManager ---> process
 * task on slt --> thread
 *
 * slot  之间内存是独享的 CPU不是独享 -- 最好配置成cpu核心数
 *
 *
 *
 * 一个流处理程序需要的slot的数量，其实就是所有任务中整个处理流程中最大的那个并行度
 * 任务合并： 减少跨机器的传输
 *
 *
 * 任务链： operation chain
 * 相同并行度的one-to-one操作，flink这样相连的两个算子链接在一起形成一个task，原来的算子成为里面的subtask。
 *
 * 逻辑视图
 * 并行化视图
 * 优化后视图
 *
 * forward
 * hash
 * rebalance
 *
 */
object Demo {
  def main(args: Array[String]): Unit = {

  }
}
