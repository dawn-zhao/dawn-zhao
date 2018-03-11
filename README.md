## MyBatis -tree
学习MyBatis Tree
http://www.spring4all.com/article/627
### 测试依赖SQL 表
CREATE TABLE `node` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `parent_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='节点表'
### 测试依赖SQL 数据
INSERT INTO node (name, parent_id) VALUES ('一级节点A', 0);
INSERT INTO node (name, parent_id) VALUES ('一级节点B', 0);
INSERT INTO node (name, parent_id) VALUES ('一级节点C', 0);
INSERT INTO node (name, parent_id) VALUES ('二级节点AA', 1);
INSERT INTO node (name, parent_id) VALUES ('二级节点aa', 1);
INSERT INTO node (name, parent_id) VALUES ('二级节点BB', 2);
INSERT INTO node (name, parent_id) VALUES ('三级级节点AAA', 4);
INSERT INTO node (name, parent_id) VALUES ('三级级节点aaa', 4);
INSERT INTO node (name, parent_id) VALUES ('三级级节点BBB', 6);

## Intellij IDEA 小技巧+快捷键
###加入断点
Ctrl+F8
###条件断点
Alt+Shift+F8
###断点变量调试
Ctrl+U
###断点运行至光标位置
Ctrl+R
###断点时更改变量值
在Debugger窗口Variales操作栏选中要更改的变量+F2
###列操作
Ctrl+Alt+Y  /  Alt+Y
###重构属性名称
Alt+Shift+R
###查找类属性,方法/变量
Alt+Ctrl+Shift+N
###Bookmark标记
Alt+Ctrl+Shift+F11   /   Ctrl+Shift+F11
###查找动作或者选项
Ctrl+Shift+A
###查看项目结构
Alt+Ctrl+Shift+S
###当前文件夹下新建文件
Ctrl+Alt+Insert
###当前文件重构
Alt+Ctrl+Shift+T
###移动当前文件
Alt+Shift+V
###获取最近剪切板的内容
Ctrl+Shift+V
###复制当前文件
Ctrl+Alt+C
###复制当前文件的绝对路径
Ctrl+Shift+C
###复制光标所在文件的相对路径:行号/相对路径#属性名
Alt+Ctrl+Shift+C
###构建项目
Ctrl+F9
###查看文件大纲 包括maven依赖,java结构等(注:很消耗CPU性能)
Alt+Ctrl+Shift+U
###查看java类继承结构
F4
###查看方法调用链 可以向上向下查看
Ctrl+Alt+H
###重新编译java文件
Ctrl+Shift+F9
###选择启动
Alt+Shift+F9
###启动main
Ctrl+Shift+F10
###Debug启动
Shift+F9
###查看快捷模板缩写
Alt+Ctrl+Shift+J
###查找本地历史记录
Ctrl+Shift+A  输入 local History
###定义缩写快捷模板
Ctrl+Shift+A  输入 Live Templates
