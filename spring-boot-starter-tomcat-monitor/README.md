
### 前言
大家都知道tomcat8的默认最大线程数是200，那这个配置是否是最佳配置呢，如果不是，那配置多少又合适呢？有人说maxThreads=n*x+1,其中n为cpu的核数，x为对应的系数，这个系数一般是跟业务强相关的，而且随时都可能发生变化，那怎么办呢，难道maxThreads对应的配置就只能靠拍脑袋嘛？请继续往下看

基于以上的介绍我们很难一开始就知道系数x定义为多少合适，那么我们可以换一种思路来解决该问题，我们可以对线上的tomcat线程数做对应的监控，然后根据收集到的指标去动态的调整对应的最大线程数，这边举一个例子方便大家理解：
假如我们一开始在一台4核8G的机器上部署了一台tomcat,用的是他默认的最大线程数配置200，我们可以实时的每秒监控tomcat的当前工作线程(currentThreadsBusy)，当前创建线程数(currentThreadCount)的大小变化,给定一个时间周期变化，这个定义为半个月，如果我们发现半个月内currentThreadsBusy的值一直都在150-200之间，甚至经常性到200，同时机器的cpu指标确并不高，这种情况下就可以适当的增加maxThreads配置，因为这种情况下其实tomcat容器并不是在做一些耗CPU的操作，其中可能大部分的线程都是在等待（比如等待一些网络连接或者数据库返回等）
同理那如果currentThreadsBusy一直都比较低，同时cpu又比较高，那就可以适当的降低最大线程数的配置了，因为线程之间的切换是需要消耗cpu的

### 如何使用
###### pom文件中引入对应jar包如下：
这里是小编自己打的starter包放到了自己macen私服中对应的引用
```
 <dependency>
        <groupId>com.zhiliao</groupId>
        <artifactId>spring-boot-starter-tomcat-monitor</artifactId>
        <version>1.0.0</version>
</dependency>
```
注：感兴趣的小伙伴可以自己fork下来，编译打包再引入到自己的项目中试试

### 更多优质文章欢迎关注小编公众

![一个懒惰的程序员](https://github.com/zhiliao666/spring-boot-starter/blob/master/spring-boot-starter-tomcat-monitor/qrcode_for_gh_d88d06cbce83_258%20.jpg)
