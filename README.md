# learn17

```
1. 集成多数据源 redisson swagger zk分布式锁
2. rabbitMq kafka 以及rocketmq分布式事务配置
```



## 中间件启动

### rocketMq启动 
````
start mqnamesrv.cmd
start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
````
### zookeeper启动

```
start zkServer.cmd
start zkCli.cmd
```

### kafka启动

````
.\bin\windows\kafka-server-start.bat .\config\server.properties
````

### redis集群
````
redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 --cluster-replicas 1
````