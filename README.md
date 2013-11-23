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
TABLE	FM	100K	10.582s	0.106ms	9450
TABLE	VM	100K	18.876s	0.189ms	5297
</pre>