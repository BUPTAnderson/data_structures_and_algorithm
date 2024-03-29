**数组（Array）是一种线性表数据结构。它用一组连续的内存空间，来存储一组具有相同类型的数据。**
第一是**线性表（Linear List）**。顾名思义，线性表就是数据排成像一条线一样的结构。每个线性表上的数据最多只有前和后两个方向。其实除了数组，链表、队列、栈等也是线性表结构。而与它相对立的概念是非线性表，比如二叉树、堆、图等。之所以叫非线性，是因为，在非线性表中，数据之间并不是简单的前后关系。  
第二个是**连续的内存空间和相同类型的数据**。正是因为这两个限制，它才有了一个堪称“杀手锏”的特性：“随机访问”。但有利就有弊，这两个限制也让数组的很多操作变得非常低效，比如要想在数组中删除、插入一个数据，为了保证连续性，就需要做大量的数据搬移工作。  
```angular2html
    这里我要特别纠正一个“错误”。我在面试的时候，常常会问数组和链表的区别，很多人都回答说，“链表适合插入、删除，时间复杂度 O(1)；数组适合查找，查找时间复杂度为 O(1)”。
    实际上，这种表述是不准确的。数组是适合查找操作，但是查找的时间复杂度并不为 O(1)。即便是排好序的数组，你用二分查找，时间复杂度也是 O(logn)。
    所以，正确的表述应该是，数组支持随机访问，根据下标随机访问的时间复杂度为 O(1)。
```

**插入操作  
        如果在数组的末尾插入元素，那就不需要移动数据了，这时的时间复杂度为 O(1)。但如果在数组的开头插入元素，那所有的数据都需要依次往后移动一位，所以最坏时间复杂度是 O(n)。 因为我们在每个位置插入元素的概率是一样的，所以平均情况时间复杂度为 (1+2+...n)/n=O(n)。
        如果数组中的数据是有序的，我们在某个位置插入一个新的元素时，就必须按照刚才的方法搬移 k 之后的数据。但是，如果数组中存储的数据并没有任何规律，数组只是被当作一个存储数据的集合。在这种情况下，如果要将某个数据插入到第 k 个位置，为了避免大规模的数据搬移，我们还有一个简单的办法就是，直接将第 k 位的数据搬移到数组元素的最后，把新的元素直接放入第 k 个位置。为了更好地理解，我们举一个例子。假设数组 a[10]中存储了如下 5 个元素：a，b，c，d，e。我们现在需要将元素 x 插入到第 3 个位置。我们只需要将 c 放入到 a[5]，将 a[2]赋值为 x 即可。最后，数组中的元素如下： a，b，x，d，e，c。

​        利用这种处理技巧，在特定场景下，在第 k 个位置插入一个元素的时间复杂度就会降为 O(1)。这个处理思想在快排中也会用到，我会在排序那一节具体来讲，这里就说到这儿。

**删除操作**

​        跟插入数据类似，如果我们要删除第 k 个位置的数据，为了内存的连续性，也需要搬移数据，不然中间就会出现空洞，内存就不连续了。和插入类似，如果删除数组末尾的数据，则最好情况时间复杂度为 O(1)；如果删除开头的数据，则最坏情况时间复杂度为 O(n)；平均情况时间复杂度也为 O(n)。

​        实际上，在某些特殊场景下，我们并不一定非得追求数组中数据的连续性。如果我们将多次删除操作集中在一起执行，删除的效率是不是会提高很多呢？我们继续来看例子。数组 a[10]中存储了 8 个元素：a，b，c，d，e，f，g，h。现在，我们要依次删除 a，b，c 三个元素。

​        为了避免 d，e，f，g，h 这几个数据会被搬移三次，我们可以先记录下已经删除的数据。每次的删除操作并不是真正地搬移数据，只是记录数据已经被删除。当数组没有更多空间存储数据时，我们再触发执行一次真正的删除操作，这样就大大减少了删除操作导致的数据搬移。

​        如果你了解 JVM，你会发现，这不就是 JVM 标记清除垃圾回收算法的核心思想吗？没错，数据结构和算法的魅力就在于此，很多时候我们并不是要去死记硬背某个数据结构或者算法，而是要学习它背后的思想和处理技巧，这些东西才是最有价值的。如果你细心留意，不管是在软件开发还是架构设计中，总能找到某些算法和数据结构的影子。

**警惕数组的访问越界问题**

首先，我请你来分析一下这段 C 语言代码的运行结果：

```
int main(int argc, char* argv[]){
    int i = 0;
    int arr[3] = {0};
    for(; i<=3; i++){
        arr[i] = 0;
        printf("hello world\n");
    }
    return 0;
}
```

你发现问题了吗？这段代码的运行结果并非是打印三行“hello word”，而是会无限打印“hello world”，这是为什么呢？

因为，数组大小为 3，a[0]，a[1]，a[2]，而我们的代码因为书写错误，导致 for 循环的结束条件错写为了 i<=3 而非 i<3，所以当 i=3 时，数组 a[3]访问越界。

我们知道，在 C 语言中，只要不是访问受限的内存，所有的内存空间都是可以自由访问的。根据我们前面讲的数组寻址公式，a[3]也会被定位到某块不属于数组的内存地址上，而这个地址正好是存储变量 i 的内存地址，那么 a[3]=0 就相当于 i=0，所以就会导致代码无限循环。

```
关于数组越界访问导致死循环的问题，我也动手实践了一下，发现结果和编译器的实现有关，gcc有一个编译选项（-fno-stack-protector）用于关闭堆栈保护功能。默认情况下启动了堆栈保护，不管i声明在前还是在后，i都会在数组之后压栈，只会循环4次；如果关闭堆栈保护功能，则会出现死循环。
当加了堆栈保护的编译选项后编译器会调整变量分配地址的顺序，不是原来的按地址递减的方式分配内存的，而是相反，这样就能从一定成都上防止堆栈溢出。
-fstack-protector：启用堆栈保护，不过只为局部变量中含有 char 数组的函数插入保护代码。

-fstack-protector-all：启用堆栈保护，为所有函数插入保护代码。

-fno-stack-protector：禁用堆栈保护。
楼主正解，gcc9.3.0，默认会报错*** stack smashing detected ***: terminated程序结束，不会死循环，加上编译参数-fno-stack-protector后，死循环。
如果是这样的压栈顺序就懂了一点，但是我还是有个疑问，为什么压栈顺序是i，a[2]，a[1]，a[0]而不是i, a[0]，a[1]，a[2]呢？求大神指教
a[k]_address = base_address + k * type_size
可以看看这篇关于gcc堆栈保护机制的文章https://blog.csdn.net/u012796139/article/details/46635567
```



**容器能否完全替代数组？**

作为高级语言编程者，是不是数组就无用武之地了呢？当然不是，有些时候，用数组会更合适些，我总结了几点自己的经验。

1. Java ArrayList 无法存储基本类型，比如 int、long，需要封装为 Integer、Long 类，而 Autoboxing、Unboxing 则有一定的性能消耗，所以如果特别关注性能，或者希望使用基本类型，就可以选用数组。

2. 如果数据大小事先已知，并且对数据的操作非常简单，用不到 ArrayList 提供的大部分方法，也可以直接使用数组。
3. 还有一个是我个人的喜好，当要表示多维数组时，用数组往往会更加直观。比如 Object[][] array；而用容器的话则需要这样定义：ArrayList > array。我总结一下，对于业务开发，直接使用容器就足够了，省时省力。毕竟损耗一丢丢性能，完全不会影响到系统整体的性能。但如果你是做一些非常底层的开发，比如开发网络框架，性能的优化需要做到极致，这个时候数组就会优于容器，成为首选。



为什么大多数编程语言中，数组要从 0 开始编号，而不是从 1 开始呢？

从数组存储的内存模型上来看，“下标”最确切的定义应该是“偏移（offset）”。前面也讲到，如果用 a 来表示数组的首地址，a[0]就是偏移为 0 的位置，也就是首地址，a[k]就表示偏移 k 个 type_size 的位置，所以计算 a[k]的内存地址只需要用这个公式：

`a[k]_address = base_address + k * type_size`





补充：

JVM标记清除算法：

大多数主流虚拟机采用可达性分析算法来判断对象是否存活，在标记阶段，会遍历所有 GC ROOTS，将所有 GC ROOTS 可达的对象标记为存活。只有当标记工作完成后，清理工作才会开始。

不足：1.效率问题。标记和清理效率都不高，但是当知道只有少量垃圾产生时会很高效。2.空间问题。会产生不连续的内存空间碎片。

补充一下可作为GC ROOTS的对象有哪些：
1.虚拟机栈中引用的对象
2.方法区类的静态成员引用的对象（JDK8之前，静态成员变量确实存放在方法区；但JDK8之后就取消了“永久代”，取而代之的是“元空间”，永久代中的数据也进行了迁移，静态成员变量迁移到了堆中（方法区是JVM的规范，永久代是方法区的具体实现）。）
3.方法区常量引用的对象
4.本地方法栈中JNI引用的对象

补充：
1.同步锁（synchronized）持有的对象
2.JVM内部的引用，如基本数据类型的class对象，常驻异常对象（OOM）

什么是可达？

JVM 中有称为一系列的GCRoots的对象，如果一个对象没有任何一条与GCRoots相连的路径时，就称为不可达。所有对象默认设置标志为0，根据算法将可达对象置为1表示存活。清除为0的对象，并把为1的对象重新置为0。每清除一次就是重复执行以上操作。(核心就是标记可达对象，清除不可达对象）

看具体的垃圾回收器，cms，g1都有默认的内存占用达到多少比例后进行垃圾回收 ，cms是这个参数设置-XX:CMSlnitiatingOccupanyFraction ，g1是这个 -XX：+InitiatingHeapOccupancyPercent

2.计数器：对象被使用时，使用次数+1；不使用时，对象使用次数-1；当GC时，如果对象的使用次数为0，则在标记阶段，将非0的标记为存活，将不存货的清理掉。
二维数组内存寻址：

分代收集算法（针对JDK1.8以下）：
根据对象的存活周期分为老年代，新生代，永久代
a、在新生代中，每次GC时都发现有大批对象死去，只有少量存活，使用复制算法。即在垃圾回收时，将正在使用的内存中存活对象复制到另一块未使用的内存中。之后清理正在使用的内存中所有对象，交换两块内存角色。反复进行，完成垃圾回收。
b、在老年代中，因为对象存活率高、没有额外空间对他进行分配担保，使用“标记-清理”/“标记-整理”算法。即在标记阶段，遍历所有的GC Roots，然后将所有GC Roots可达的对象标记为存活的对象。清除阶段，清除的过程将遍历堆中所有的对象，将没有标记的对象全部清除掉。
c、永久代（Permanet Generation）/ 元空间（Metaspace）
永久代用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译后的代码等数据。是JVM规范中方法区的具体实现。
是Hotspot虚拟机特有的概念，方法区/永久代是非堆内存。

**LeetCode**

Leetcode-0315

Leetcode-0287

Leetcode-0312