# JVM
书：


### 字节码
  首先主要认识字节码，能够简单看懂字节码文件，可以参考该文章 https://blog.csdn.net/21aspnet/article/details/88351875

### 使用类加载器
   作业为：自己写自定义类加载器，从文件中获取类，并且执行其中的方法

### java内存模型
   作业为：需要画下Xmx，Xms，Xmn，Meta，DirectMemory，Xss 这些内存参数的关系  
   1. 首先需要了解各个参数都是干什么的
   2. Xms 初始堆内存
   3. Xmx 最大堆内存
   4. Xmn 年轻代内存
   5. Meta 永久代（已经不属于堆内存）
   6. XX:MaxDirectMemorySize 堆外内存
   7. Xss 栈内存
   8. 根据以上的信息去画图
   
