## 三个socket的例子 （BIO）

### socket
01 单线程处理socket      RPS：25
02 每个请求一个线程         RPS:1167
03 使用固定大小的线程池处理     RPS:1183.9

### netty
04 使用netty的形式实现这个处理  RPS:1222.5


### client
作业：使用 httpClient和okClient实现地址访问


### 三种经典的I/O模式
&ensp;&ensp;&ensp;&ensp;有三种经典的I/O模型：BIO（阻塞I/O）,上面例子中的单线程、多线程、线程池都属于BIO模型；NIO（非阻塞IO），上面例子的中Netty是NIO模式；AIO（异步I/O），由于某些原因，虽然性能理论上要比NIO好，但还没有很好的应用。

&ensp;&ensp;&ensp;&ensp;下面是吃饭场景对比I/O模式

- BIO（阻塞I/O） -- 食堂排队打饭模式：排队在窗口，打好才走
- NIO（非阻塞I/O） -- 点单、等待被叫模式：等待被叫，好了自己去端
- AIO（异步I/O） -- 包厢模式：点单后菜直接被端上桌


### 阻塞与非阻塞、同步与异步
&ensp;&ensp;&ensp;&ensp;阻塞和非阻塞是对于通信中的数据而言的：如果没有数据会一直等下去那就是阻塞；不等就是非阻塞

&ensp;&ensp;&ensp;&ensp;同步和异步是对于怎样取数据而言的：如果数据好了，程序自己去取，那就是同步；如果是数据就绪后回调给程序就是异步

&ensp;&ensp;&ensp;&ensp;对应吃饭的场景是：

- 阻塞与非阻塞：对于菜而言，阻塞就是一直等菜，反之
- 同步与异步：对应菜好了怎么取，自己去取就是同步，等服务员端上来就是异步


### 为什么删掉已经做好的AIO支持：
- Windows实现成熟，但很少做服务器
- Linux的AIO不够成熟
- Linux下AIO相比较NIO性能提升不明显

## 参考链接
- [20 | 大名⿍⿍的select：看我如何同时感知多个I/O事件](https://time.geekbang.org/column/article/138948)
- [21 | poll：另一种I/O多路复用](https://time.geekbang.org/column/article/140520)
- [22 | 非阻塞I/O：提升性能的加速器](https://time.geekbang.org/column/article/141573)
- [08 | Netty怎么切换三种I/O模式？](https://time.geekbang.org/course/detail/100036701-147214)