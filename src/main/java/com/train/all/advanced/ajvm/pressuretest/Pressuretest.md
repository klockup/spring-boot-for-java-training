## 压测项目 利用工具进行内存查看
```
命令行：
java -jar -Xmx1g -Xms1g test-project.jar
启动成功验证访问 ：http://localhost:8088/api/hello

使用命令行工具压测下接口测试吞吐量：
sb -u http://localhost:8088/api/hello -c 20 -N 60

-c (–concurrency)(默认: 1) 并发请求数
-n ( --numberOfRequests)(默认: 100) 请求总数
-N (–numberOfSeconds)(如果指定-n，则会被忽略) 运行秒数
-y (–delayInMillisecond)(默认: 0) 以毫秒为单位进行延迟
默认都是使用http GET 方式进行请求，如果需要其他的method，可以用 -m指定。


并且使用java自带可视化工具查看运行情况，如 jvisualvm

512m情况下-并行GC，吞吐量为5481
1G情况下-并行GC，吞吐量为5075
4G情况下-并行GC，吞吐量为5284

512m情况下-CMS GC，吞吐量为5417
4G情况下-CMS GC，吞吐量为4455

有可能接口太简单了

```



