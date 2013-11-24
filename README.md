测试各种模板引擎的速度：Freemarker VS Velocity
=============
* FM: Freemarker
* VM: Velocity
* 机器环境：
<pre>
	CPU：i7-3630QM
	内存：24G
	OS: XUbuntu 12.04 amd64
	JDK: jdk1.7.0_45 x64
</pre>

### 测试场景：
* html table(50行 * 10列) 生成，不考虑磁盘性能，都在内存中处理

### 测试结果：
<pre>
参数：(-server -Xms1g -Xmx1g)：
测试项目	引擎	生成次数	总耗时	平均耗时	1s处理
TABLE	FM	130K	10.303s	0.079ms	12617
TABLE	JT	130K	3.022s	0.023ms	43017
TABLE	VM	130K	9.642s	0.074ms	13482
</pre>