## 使用 GCLogTest文件来测试GC的运行情况（因为加了package，所以执行需要注意路径）

#### 需要在 training-java-springboot\src\main\java下执行命令
```
//先简单看下执行结果，这个是直接将执行的信息打印在命令行中
java -XX:+PrintGCDetails com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
//这个的执行结果是将日志文件打印在指定的文件夹下
java -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintReferenceGC com.train.all.advanced.ajvm.gc.testDemo.GcLogTest

//我本地默认使用的是java8 所以默认使用的是并行GC
```
    
```
1 先试下oom，将Xmx调整为128m，执行命令会发现java.lang.OutOfMemoryError: Java heap space
    java -Xmx128m -Xms128m  -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.oomError.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
    查看日志会发现1s的最后都在执行fullGC，但是内存无法满足，最后导致OOM,程序没有完成执行
    
2 下面模拟下各种GC在不同内存配置的条件下GC的情况
    串行GC（Serial GC）
        512M
        java -Xmx512m -Xms512m -XX:+UseSerialGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.serial.512m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:8958，15次Young GC，2次Full GC。
        第二次：共生成对象次数:9024，17次Young GC。
        第三次：共生成对象次数:9091，16次Young GC，1次Full GC。
        young gc，前半部分的时间在20-30ms，后半部分时间在40-50ms（stw：30-50ms）
        full gc，60ms（stw:60ms）
        
        1024m
        java -Xmx1024m -Xms1024m -XX:+UseSerialGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.serial.1024m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:9486，9次Young GC。
        第二次：共生成对象次数:10092，9次Young GC。
        young gc，时间大概在40ms-60ms（stw：50ms左右）
        
        2048m
        java -Xmx2048m -Xms2048m -XX:+UseSerialGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.serial.2048m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:8627，4次Young GC。
        第二次：共生成对象次数:10580，4次Young GC。
        young gc，时间大概在80ms-90ms（stw：80ms左右）
        
        4096m
        java -Xmx4096m -Xms4096m -XX:+UseSerialGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.serial.4096m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:8349，2次Young GC。
        第二次：共生成对象次数:8438，2次Young GC。
        young gc第一次在118ms，第二次是154ms（stw：120ms左右）
        
        总结：
            串行GC单核运作，所以在堆内存大了以后，GC的时间也会上升。 full GC时，只处理了old区，young区是否处理未知。
            young区是堆内存的1/3。
            Eden区是young区的0.8倍
    
    
    并行GC（Parallel GC）
        512M
        java -Xmx512m -Xms512m -XX:+UseParallelGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.parallel.512m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:9043，29次Young GC，10次Full GC。
        第二次：共生成对象次数:9271，31次Young GC，11次Full GC。
        young gc,平均时间：10ms左右（stw：10ms以下）
        full gc：平均时间30-40ms，（stw：30ms-40ms），而且在后面全是fullGC，影响使用
        
        1024m
        java -Xmx1024m -Xms1024m -XX:+UseParallelGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.parallel.1024m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:12572，21次Young GC，2次Full GC。
        第二次：共生成对象次数:12526，21次Young GC，1次Full GC。
        young gc,平均时间：10-20ms左右（stw：10ms-20ms）
        full gc：平均时间40ms，（stw：40ms），并没有发现大片full GC
        如果不配置Xms的话看下情况做个对比：
        java -Xmx1024m -XX:+UseParallelGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.parallel.1024m.noxms.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:11356，23次Young GC，5次Full GC。
        第二次：共生成对象次数:11164，23次Young GC，4次Full GC。
        young gc,平均时间：5-15ms左右（stw：10ms-20ms）
        full gc：平均时间20-40ms，（stw：20-40ms）
        对比配置Xms的情况可知：如果Xms和Xmx配置的大小不一致会导致起始堆内存分配较小，gc次数更多，并且gc的时间不稳定，程序运行时也需要不断的gc调整内存大小。

        2048m
        java -Xmx2048m -Xms2048m -XX:+UseParallelGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.parallel.2048m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:11920，6次Young GC。
        第二次：共生成对象次数:13149，7次Young GC。
        young gc,平均时间：30-40ms左右（stw：30-40ms）

        4096m
        java -Xmx4096m -Xms4096m -XX:+UseParallelGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.parallel.4096m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:11181，2次Young GC。
        第二次：共生成对象次数:10631，2次Young GC。
        young gc,平均时间：50-60ms左右（stw：50-60ms）
        
        总结：
            并行GC多线程运作，堆内存增大，gc次数变少，gc时间上升较小。 
            full GC时，不只处理了old区，young区也进行了处理。
            young区是堆内存的1/3。
            Eden区和存活区占比不一定。
            
    
    CMS GC
        512M
        java -Xmx512m -Xms512m -XX:+UseConcMarkSweepGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.cms.512m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:9662，18次Young GC（10-30ms），6次Full GC（只有2次是成功的(4ms)，4次是 concurrent mode failure，并发失败退化为Serial Old(50ms)）。
        第二次：共生成对象次数:10088，19次Young GC，7次Full GC（只有4次是成功的(4ms)，3次是 concurrent mode failure，并发失败退化为Serial Old(48ms)）。
        在cms full GC做并发标记时由于垃圾产生的速度大于清理的速度时，会导致并发清理失败，退化导致gc时间变长。
        
        1024m
        java -Xmx1024m -Xms1024m -XX:+UseConcMarkSweepGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.cms.1024m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:9540，9次Young GC（20-60ms），2次Full GC（1次标记成功（7ms），1次因为时间原因没有显示出来）。
        第二次：共生成对象次数:10577，10次Young GC（20-40ms），2次Full GC（1次标记成功（7ms），1次因为时间原因没有显示出来）。
        
        2048m
        java -Xmx2048m -Xms2048m -XX:+UseConcMarkSweepGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.cms.2048m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:10326，4次Young GC（35-83ms）。
        第二次：共生成对象次数:10199，4次Young GC（37-79ms）。
        
        4096m
        java -Xmx4096m -Xms4096m -XX:+UseConcMarkSweepGC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.cms.4096m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:10245，4次Young GC（40-127ms）。
        第二次：共生成对象次数:10128，4次Young GC（40-104ms）。
        
        总结：
            CMS GC，内存大的情况下不如并行gc
            
    G1 GC
        512m
        java -Xmx512m -Xms512m -XX:+UseG1GC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.g1.512m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:10536，过程比较复杂，一共3次Full GC（平均40ms）。
        第二次：共生成对象次数:10547，一共2次Full GC（平均40ms）。
        
        1024m
        java -Xmx1024m -Xms1024m -XX:+UseG1GC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.g1.1024m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:11473，无Full GC。
        第二次：共生成对象次数:12596，无Full GC。
        
        2048m
        java -Xmx2048m -Xms2048m -XX:+UseG1GC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.g1.2048m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:12271，无Full GC（每次暂停时间大概在20-30ms）。
        第二次：共生成对象次数:12152，无Full GC。
        
        4096m
        java -Xmx4096m -Xms4096m -XX:+UseG1GC -Xloggc:com\train\all\advanced\ajvm\gc\testDemo\log\gc.g1.4096m.1.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.train.all.advanced.ajvm.gc.testDemo.GcLogTest
        第一次：共生成对象次数:12778，无Full GC（每次暂停时间大概在10-20ms）。
        第二次：共生成对象次数:12728，无Full GC。
        
        总结：
            G1 GC 是真牛逼
           
        
```


