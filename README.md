# MyLeetcodes

一直觉得算法是一个对 “严谨的逻辑” 和 “发散的思维” 要求都很高的东西，从数学知识，到编码能力，它考察的东西也十分的多。在本科最开始接触算法时，就深深的喜欢上了它，记得当时学的这门课叫 《算法设计与分析》，自认为学的还是不错的，可是后来辗转，虽然也有陆陆续续的写过一些，但是形不成系统的训练，很多概念都变得也越来越模糊，慢慢的被遗忘。

从今年 4 月份，决定开始刷题，很多人和我说：“哇，你怎么这么早就开始了？”，我当时满脑子都在后悔自己开始的太晚，听到这句话时就表示格外的惊讶。后来才意识到，他们可能是觉得我离找工作还早。额......好吧，菜鸟先飞一下还不行啊。

其实大多数人应该都是这么想的，是带着目的的为了刷题而刷，在找工作前，猛刷一两百道这种。可是我觉得算法刷题，它更应该是被当做一种习惯，每一道题里面的知识点都有很多是值得去深究的，**它不应该被作为一种快餐式的过程**，这在快节奏的物联网文化下，就显得愈发重要。对比与在某天下定决心，花一个月猛刚 100 题，我更情愿每天花上一些零碎时间，慢悠悠的将每一道题都弄懂弄透。

算法能力到底怎样算好？有的人比题量、有的人比解题速度、有的人比思维方法......这些都是不同的角度的衡量，它们都很重要。在我刷完第一轮的《剑指 offer 67 题》后，再回想起来，那种感觉真像做了一场梦，“我在哪？”、“我干了什么？”，这还是我每一题都写了详细的题解的情况下。那时候我才意识到，系统的训练，不断的总结反思是多么的重要。如果**看到题目，就能立马在脑海里蹦出来这道题可能涉及的所有知识点，并能很灵活的将题与知识点、题与题联系起来，这种“敏锐性”再配合自己发散的思维能力**，我觉得这是每个刷题人都需要制定的目标，这条路会很长，需要长时间一步一个脚印的去钻。

这里分享一下本人在 Leetcode 上刷题的一些心得，记录一下自己的刷题历程。


# 语言选择

本人目前用的编程语言是 java 和 python。其实一开始，在做剑指 offer 的时候，我是相同时锻炼一下编程能力，所以两种语言都在用，但后来发现这样比较费时，也没啥必要，毕竟算法更考察的是思维能力，语言只是一种工具罢了。   

但我不推荐使用 python，因为 python 作为一种无类型的脚本语言，也就意味着我们不需要考虑变量类型的转换，而往往这就是我们做算法时很容易忽视的一个点。另外，python 执行效率较低，也就不便于算法性能的测试，并且它包含的一系列的工具包，会让我们忽略那些算法上的细节，例如：字符串反转，python 一句简单的数据切分就可实现，我们就不会更深入分析那些实现的细节，这是算法的大忌。

至于其他主流的，c/c++、java 这些，就根据自己的需求选择。总之，不关心工具，注意思想，多关心实现细节！


# 做题工具

做题工具的选择就很多了，最直接的方式就是在 leetcode 的官网上做题，这种方式最接近求职面试时的场景。但它有一个弊端，就是没法像 IDE 那样有代码、函数补全等等，每个字符都需要你一下一下敲，所以你不是为了找工作，我更建议下面的那些办法。当然了，在求职面试前，我还是更建议多熟悉熟悉这样的代码编辑方式，毕竟不保证你去面试时就会给你 IDE，我们要做好最坏的准备。

至于平常锻炼，那当然是把更多的心思花在算法思想上了，而不是一直去想 “这个方法叫啥？“，”这句话咋写来着？”。这里，我推荐一下 『IDEA + leetcode 插件』 这套组合，JetBrains 家的产品有多香就不说了，leetcode 插件登陆后，你就可以查看到 leetcode 的所有题目，并且也会像 leetcode 官网那样分类，同时你也可以直接查看每道题的评论、题解等等。最主要的是，你可以提前配置好题目模板，然后一键生成，每道题都有可以在 IDEA 里编写，并且支持提交 leetcode 运行，和你的账号实时同步。

后面，我会详细介绍一下相关配置细节。另外，喜欢用 vscode 的，它也有 leetcode 插件，感兴趣的伙伴们也可以去尝试。 

# 刷题进度

**第一轮**（2020-04-05 至 2020-05-09）：[《剑指 offer 67题》](http://www.thebetterkong.cn/2020/04/05/DataStructure-Algorithm/Finger-offer67/)一刷；    

**第二轮**（2020-09-21 至 2020-12-14）：Leetcode 专题训练    
|专题|日期|题号|
|---|---|---|
|【数组】|09-21 至 09-25|1、26/27、35、53、66、88、118/119、121/122、167、169、189、217、219、268、283、414|
|【字符串】|09-28 至 10-10|13、14、20、28、38、58、67、125、344、345、383、387|
|【链表】|10-12 至 10-15|21、83、141、160、203、206、234、237、876、1290|
|【栈和队列】|10-19 至 10-23|155、225、232、496、682、844、933、1021、1047、1441、1544、1598|
|【树】|10-26 至 10-30|100、101、104、107、108、110、111、112、226、235|
|【数学】|11-02 至 11-05|7、9、69、168、171、172、202、204、231、258|
|【排序】|11-09 至 11-13|242、349、350、922、976、1030、1122、1356、1370、1403、1491|
|【贪心算法】|11-16 至 11-20|392、455、860、874、944、1005、1046、1217、1221、1518|
|【动态规划】|12-09 至 12-14|70、198、303、746、1025|

**第三轮**（2020-12-21 至 go on）：Leetcode 热题 top 100   
- 2、3、4、5、10、11、15、17、19、22、23 
- 31、32、33、34、39、42、46、48、49、55
- 56、62、64、72、75、76、78、79、84
- 85、94、96、98、102、105、114
- 124、128、136、416、322、518、377


---


# 总结反思

## 数据结构

- [java 数据结构之线性表](http://www.thebetterkong.cn/2020/06/27/DataStructure-Algorithm/DataStructure-LinearList/)
- [java 数据结构之字符串](http://www.thebetterkong.cn/2020/07/02/DataStructure-Algorithm/DataStructure-String/)
- [java 数据结构之树](http://www.thebetterkong.cn/2020/07/09/DataStructure-Algorithm/DataStructure-Tree/)
- [java 数据结构之图](http://www.thebetterkong.cn/2020/09/09/DataStructure-Algorithm/DataStructure-Graph/)



## 常用算法

- [排序算法（详解 + java 实现）](http://www.thebetterkong.cn/2020/06/04/DataStructure-Algorithm/SortingAlgorithm/)
- [查找算法（详解 + java 实现）](http://www.thebetterkong.cn/2020/06/15/DataStructure-Algorithm/SearchingAlgorithm/)
- [KMP 算法详讲](http://www.thebetterkong.cn/2020/10/02/DataStructure-Algorithm/KMP-Algorithm/)
- [快速幂算法](http://www.thebetterkong.cn/2021/03/13/DataStructure-Algorithm/FastEponentiation-Algorithm/#more)
- [TOP-K 问题](http://www.thebetterkong.cn/2021/03/21/DataStructure-Algorithm/TOP-K/)
- [动态规划搭建 “摩天大厦”](http://www.thebetterkong.cn/2021/05/22/DataStructure-Algorithm/DynamicProgramming/)


**让我们一起让算法成为一种习惯！**