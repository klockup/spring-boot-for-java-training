# 该文件为MovingAverage.java的字节码文件
Compiled from "MovingAverage.java"

    public class com.train.all.advanced.ajvm.bytecode.MovingAverage {
    public com.train.all.advanced.ajvm.bytecode.MovingAverage();
    Code:
    0: aload_0
    1: invokespecial #1                  // Method java/lang/Object."<init>":()V
    4: aload_0
    5: iconst_0
    6: putfield      #2                  // Field count:I
    9: aload_0
    10: dconst_0
    11: putfield      #3                  // Field sum:D
    14: return
    LineNumberTable:
    line 8: 0
    line 10: 4
    line 11: 9
    LocalVariableTable:
    Start  Length  Slot  Name   Signature
    0      15     0  this   Lcom/train/all/advanced/ajvm/bytecode/MovingAverage;
    
    public void submit(double);
    Code:
    0: aload_0
    1: dup
    2: getfield      #2                  // Field count:I
    5: iconst_1
    6: iadd
    7: putfield      #2                  // Field count:I
    10: aload_0
    11: dup
    12: getfield      #3                  // Field sum:D
    15: dload_1
    16: dadd
    17: putfield      #3                  // Field sum:D
    20: return
    LineNumberTable:
    line 14: 0
    line 15: 10
    line 16: 20
    LocalVariableTable:
    Start  Length  Slot  Name   Signature
    0      21     0  this   Lcom/train/all/advanced/ajvm/bytecode/MovingAverage;
    0      21     1 value   D
    
    public double getAvg();
    Code:
    0: iconst_0
    1: aload_0
    2: getfield      #2                  // Field count:I
    5: if_icmpne     13
    8: aload_0
    9: getfield      #3                  // Field sum:D
    12: dreturn
    13: aload_0
    14: getfield      #3                  // Field sum:D
    17: aload_0
    18: getfield      #2                  // Field count:I
    21: i2d
    22: ddiv
    23: dreturn
    LineNumberTable:
    line 19: 0
    line 20: 8
    line 22: 13
    LocalVariableTable:
    Start  Length  Slot  Name   Signature
    0      24     0  this   Lcom/train/all/advanced/ajvm/bytecode/MovingAverage;
    
    public static void main(java.lang.String[]);
    Code:
    0: new           #4                  // class com/train/all/advanced/ajvm/bytecode/MovingAverage
    3: dup
    4: invokespecial #5                  // Method "<init>":()V
    7: astore_1
    8: iconst_1
    9: istore_2
    10: iconst_2
    11: istore_3
    12: aload_1
    13: iload_2
    14: i2d
    15: invokevirtual #6                  // Method submit:(D)V
    18: aload_1
    19: iload_3
    20: i2d
    21: invokevirtual #6                  // Method submit:(D)V
    24: aload_1
    25: invokevirtual #7                  // Method getAvg:()D
    28: dstore        4
    30: return
    LineNumberTable:
    line 26: 0
    line 27: 8
    line 28: 10
    line 29: 12
    line 30: 18
    line 31: 24
    line 33: 30
    LocalVariableTable:
    Start  Length  Slot  Name   Signature
    0      31     0  args   [Ljava/lang/String;
    8      23     1    ma   Lcom/train/all/advanced/ajvm/bytecode/MovingAverage;
    10      21     2  num1   I
    12      19     3  num2   I
    30       1     4   avg   D
    }
