```
port 6380
cluster-enabled yes
cluster-config-file nodes-6380.conf
cluster-node-timeout 5000
appendonly yes
bind 0.0.0.0
```

```
redis-server.exe --service-install D:\redis-cluster\6380\redis.windows.conf --service-name redis6380
```

安装ruby

```
http://dl.bintray.com/oneclick/rubyinstaller/rubyinstaller-2.2.4-x64.exe
```

下载ruby环境下Redis的驱动 gem

```
gem install --local D:\Ruby22-x64\redis-3.2.2.gem
```

下载redis集群创建工具redis-trib.rb 放在6个redis文件下的同级目录下

```
redis-trib.rb create --replicas 1 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 127.0.0.1:6385
```

