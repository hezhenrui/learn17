# spring-boot-main

## 中间件启动
### rocketMq启动 
````
start mqnamesrv.cmd
start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
````
### kafka启动
````
.\bin\windows\kafka-server-start.bat .\config\server.properties
