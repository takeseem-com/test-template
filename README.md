测试各种模板引擎的速度
=============
# 机器环境：
 CPU：i7-3630QM, 内存：24G
# 测试场景table生成：

html table(50行 * 10列) 生成，不考虑磁盘性能，都在内存中处理

参数：(-server -Xms4g -Xmx4g)：
table FM	10.0万	10.938s	1.0938s/万
table VM	10.0万	19.349s	1.9349s/万

参数：(-server -Xms2g -Xmx2g)：
table FM	10.0万	10.157s	1.0157s/万
table VM	10.0万	18.069s	1.8069s/万

参数：(-server -Xms1g -Xmx1g)：
table FM	10.0万	10.221s	1.0221s/万
table VM	10.0万	18.969s	1.8969s/万