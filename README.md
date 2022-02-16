# repro project for https://github.com/spring-projects/spring-data-redis/issues/2262

# how to build and run it

```shell
docker-compose down -v && docker-compose up --build
```


# to reproduce race condition execute
```
docker-compose stop consumer
```

# and following exception should be thrown
```
2022-02-16 10:26:12.713  WARN 1 --- [cTaskExecutor-1] io.lettuce.core.RedisChannelHandler      : Connection is already closed
2022-02-16 10:26:12.716 ERROR 1 --- [cTaskExecutor-1] ageListenerContainer$LoggingErrorHandler : Unexpected error occurred in scheduled task.

org.springframework.data.redis.RedisSystemException: Redis exception; nested exception is io.lettuce.core.RedisException: Connection closed
	at org.springframework.data.redis.connection.lettuce.LettuceExceptionConverter.convert(LettuceExceptionConverter.java:74) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.connection.lettuce.LettuceExceptionConverter.convert(LettuceExceptionConverter.java:41) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.PassThroughExceptionTranslationStrategy.translate(PassThroughExceptionTranslationStrategy.java:44) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.FallbackExceptionTranslationStrategy.translate(FallbackExceptionTranslationStrategy.java:42) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.connection.lettuce.LettuceConnection.convertLettuceAccessException(LettuceConnection.java:272) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.connection.lettuce.LettuceConnection.await(LettuceConnection.java:1063) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.connection.lettuce.LettuceConnection.lambda$doInvoke$4(LettuceConnection.java:920) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.connection.lettuce.LettuceInvoker$Synchronizer.invoke(LettuceInvoker.java:673) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.connection.lettuce.LettuceInvoker$DefaultManyInvocationSpec.toList(LettuceInvoker.java:618) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.connection.lettuce.LettuceStreamCommands.xRead(LettuceStreamCommands.java:323) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.stream.DefaultStreamMessageListenerContainer.lambda$null$5(DefaultStreamMessageListenerContainer.java:263) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:223) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:190) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:177) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.stream.DefaultStreamMessageListenerContainer.lambda$getReadFunction$6(DefaultStreamMessageListenerContainer.java:262) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.stream.StreamPollTask.readRecords(StreamPollTask.java:166) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.stream.StreamPollTask.doLoop(StreamPollTask.java:147) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at org.springframework.data.redis.stream.StreamPollTask.run(StreamPollTask.java:132) ~[spring-data-redis-2.6.1.jar:2.6.1]
	at java.base/java.lang.Thread.run(Unknown Source) ~[na:na]
Caused by: io.lettuce.core.RedisException: Connection closed
	at io.lettuce.core.protocol.DefaultEndpoint.notifyDrainQueuedCommands(DefaultEndpoint.java:679) ~[lettuce-core-6.1.6.RELEASE.jar:6.1.6.RELEASE]
	at io.lettuce.core.protocol.CommandHandler.channelInactive(CommandHandler.java:358) ~[lettuce-core-6.1.6.RELEASE.jar:6.1.6.RELEASE]
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelInactive(AbstractChannelHandlerContext.java:262) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelInactive(AbstractChannelHandlerContext.java:248) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelInactive(AbstractChannelHandlerContext.java:241) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.ChannelInboundHandlerAdapter.channelInactive(ChannelInboundHandlerAdapter.java:81) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.lettuce.core.protocol.RedisHandshakeHandler.channelInactive(RedisHandshakeHandler.java:89) ~[lettuce-core-6.1.6.RELEASE.jar:6.1.6.RELEASE]
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelInactive(AbstractChannelHandlerContext.java:262) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelInactive(AbstractChannelHandlerContext.java:248) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelInactive(AbstractChannelHandlerContext.java:241) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.ChannelInboundHandlerAdapter.channelInactive(ChannelInboundHandlerAdapter.java:81) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.lettuce.core.ChannelGroupListener.channelInactive(ChannelGroupListener.java:69) ~[lettuce-core-6.1.6.RELEASE.jar:6.1.6.RELEASE]
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelInactive(AbstractChannelHandlerContext.java:262) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelInactive(AbstractChannelHandlerContext.java:248) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelInactive(AbstractChannelHandlerContext.java:241) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.DefaultChannelPipeline$HeadContext.channelInactive(DefaultChannelPipeline.java:1405) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelInactive(AbstractChannelHandlerContext.java:262) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelInactive(AbstractChannelHandlerContext.java:248) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.DefaultChannelPipeline.fireChannelInactive(DefaultChannelPipeline.java:901) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.AbstractChannel$AbstractUnsafe$7.run(AbstractChannel.java:813) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:164) ~[netty-common-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:469) ~[netty-common-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:503) ~[netty-transport-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:986) ~[netty-common-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74) ~[netty-common-4.1.73.Final.jar:4.1.73.Final]
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30) ~[netty-common-4.1.73.Final.jar:4.1.73.Final]
	... 1 common frames omitted
```
