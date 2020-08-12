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
 *
 * yarn-session.sh -n 2 -s 2 -jm 1024 -tm 1024 -nm test -d
 * flink run -m yarn-cluster -c xxx
 *
 *
 * 核心问题：
 *
 * 1. 任务是什么？
 * 代码中定义的每一步操作(算子 operation) 就是一个任务
 * 算子可以设置并行度，所以每一步操作可以有多个并行的子任务
 * flink可以将前后执行的不同任务合并起来， one-to-one 并行度相同的
 *
 * 2. slot到底是什么，一段代码需要多少个slot来执行？slot与任务的关系？
 * slot的task_manager拥有的计算资源的一个子集。一个任务必须在
 * 每一个算子的并行任务，必须执行在不同的slot上
 * 如果是不同算子的任务， 可以共享同一个slot上
 *
 * 一般情况下， 一段代码执行需要的slot数量，就是并行度最大的算子的并行度。
 *
 * 3. 并行度和slot数量的关系？
 * 并行度和任务有关，就是每一个算子拥有的并行任务数量 动态概念
 * slot数量只跟TM的配置有关，代表tm并行处理数据的能力。  静态概念
 *
 * 4. 什么样的任务能够合并在一起？
 * one-to-one 并行度相同
 *
 *
 * 程序与数据流 dataflow
 * 执行图
 * streamgraph -> jobgraph -> excutegraph ->物理执行图
 *
 *
 * 数据传输
 * one-to-one
 * redistributing rebalance
 *
 * 任务链 task chain
 * one-to-one 并行度相同
 *
 *
 */
object Demo {
  def main(args: Array[String]): Unit = {

  }
}
