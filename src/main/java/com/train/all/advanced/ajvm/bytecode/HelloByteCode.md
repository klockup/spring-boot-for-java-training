## HelloByteCode的主要字节码为下面的代码，逐行分析
```
 0 iconst_2     //在操作栈new 2
 1 istore_1     //把2压入局部变量表位置1(a)
 2 iconst_4     //在操作栈new 4
 3 istore_2     //把4压入局部变量表位置2(b)
 4 iload_1      //取出变量1放入操作栈
 5 iload_2      //取出变量2放入操作栈
 6 imul         //变量1 变量2相乘
 7 istore_3     //将结果压入局部变量表位置3(c)
 8 iconst_0     //在操作栈new 0
 9 istore 4     //将0压入局部变量表位置4(int i)
11 iload 4      //取出变量4放入操作栈
13 bipush 10    //取值10放入操作栈
15 if_icmpge 42 (+27)   //比较0 和10的大小，结果大于等于0的时候跳转42行
18 iload_3      //取出变量3放入操作栈
19 iload 4      //取出变量4放入操作栈
21 iadd         //变量3 变量4 相加
22 istore_3     //相加的数值放入变量3(c)
23 iconst_3     //在操作栈new 3
24 iload 4      //取出变量4放入操作栈
26 if_icmpne 36 (+10)   //比较3和变量4的大小，结果不等于0的时候跳转36行
29 iload_3      //取出变量3放入操作栈
30 iload_1      //取出变量1放入操作栈
31 idiv         //变量3 除以 变量1
32 istore_3     //结果存变量3
33 iinc 3 by -1 //变量3 减1，并且放变量表
36 iinc 4 by 1  //变量4 加1，并且放入变量表
39 goto 11 (-28) //回到11行循环
42 getstatic #2 <com/train/all/advanced/ajvm/bytecode/HelloByteCode.log : Lorg/slf4j/Logger;>
45 iload_3  //log.info 内容
46 invokestatic #3 <java/lang/String.valueOf : (I)Ljava/lang/String;>
49 invokeinterface #4 <org/slf4j/Logger.info : (Ljava/lang/String;)V> count 2
54 return
```