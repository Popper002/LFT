.class public Output 
.super java/lang/Object

.method public <init>()V
 aload_0
 invokenonvirtual java/lang/Object/<init>()V
 return
.end method

.method public static print(I)V
 .limit stack 2
 getstatic java/lang/System/out Ljava/io/PrintStream;
 iload_0 
 invokestatic java/lang/Integer/toString(I)Ljava/lang/String;
 invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
 return
.end method

.method public static read()I
 .limit stack 3
 new java/util/Scanner
 dup
 getstatic java/lang/System/in Ljava/io/InputStream;
 invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
 invokevirtual java/util/Scanner/next()Ljava/lang/String;
 invokestatic java/lang/Integer.parseInt(Ljava/lang/String;)I
 ireturn
.end method

.method public static run()V
 .limit stack 1024
 .limit locals 256
L1:
 ldc 10
 istore 0
L2:
 ldc 0
 istore 1
L3:
 ldc 12
 istore 2
L4:
 iload 0
 ldc 0
 if_icmpgt L5
 goto L6
L5:
L7:
 iload 0
 ldc 1
 isub 
 istore 0
 iload 0
 invokestatic Output/print(I)V
 goto L4
L6:
 invokestatic Output/read()I
 istore 0
 invokestatic Output/read()I
 istore 2
 iload 0
 ldc 0
 if_icmpgt L11
 goto L12
L11:
 iload 0
 iload 2
 invokestatic Output/print(I)V
 goto L10
L12:
 iload 1
 ldc 0
 if_icmpgt L13
 goto L14
L13:
 iload 1
 invokestatic Output/print(I)V
 goto L10
L14:
L9:
L0:
 iload 1
 ldc 1
 iadd 
 invokestatic Output/print(I)V
L0:
 return
.end method

.method public static main([Ljava/lang/String;)V
 invokestatic Output/run()V
 return
.end method

