# 常用数据结构和算法总结
大学时刚开始接触编程就学了蔡子经教授和施伯乐教授的《数据结构》，这书里面真的都是精华，一些常用的结构和算法都有，受益匪浅。可能因为那时候Java还不流行，里面的程序都是C的，这个Repositroy包含了本人针对教材中的C程序改写的Java版本。需要说明的是我的改写并不是对照C版本照抄，而是“意译”，甚至参考了其他人的对同样问题的算法，目的在于验证自己对它们的理解，因此与原书实现方法会有不同。同时我们要看到Java相对于C还是有很多不同，许多基础的数据结构jdk已经提供了，典型的如栈（Stack），这些因素也决定在用Java去实现时必然会和C版本不同。这本教材是我大学时期初识编程时的课堂教材，一直觉得两位先生编了一本非常棒的书，里面代码之精炼，讲解之到位让我肃然起敬，这次重温也是对他们的敬意。

内容索引
-----
* 模式匹配
	* [KMP算法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/patternmatch/KMP.java)
	* [BM算法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/patternmatch/BM.java)
* 排序
	* [插入排序](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/sort/InsertSort.java)
	* [选择排序](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/sort/SelectSort.java)
	* [冒泡排序](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/sort/BubbleSort.java)
	* [合并排序](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/sort/MergeSort.java)
	* [快速排序](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/sort/QuickSort.java)
	* [Shell排序](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/sort/ShellSort.java)
	* [基数排序-低位优先](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/sort/RadixSortLastSignificantDigitalFirst.java)
	* [基数排序-高位优先](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/sort/RadixSortMostSignificantDigitalFirst.java)
* 搜索
	* [顺序查找](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/search/Sequence.java)
	* [二分查找](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/search/Binary.java)
	* Hash搜索以及Hash存储冲突解决
		* [开式寻址法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/search/HashSearchOpenAddress.java)
		* [地址链接法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/search/HashSearchLinkedAddress.java)
* 树
	* 树的线性表示
		* [括号表示法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/representation/BracketRepresentation.java)
		* [中序层号表示法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/representation/PreorderWithLevelRepresentation.java)
	* 树的遍历
		* [层序遍历](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/visit/LevelOrderVisit.java)
		* [后序遍历](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/visit/PostOrderVisit.java)
		* [前序遍历](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/visit/PreOrderVisit.java)
	* 二叉树
		* [二叉树的线性表示 - 带两个标志位的数组](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/representation/PreOrderWithTwoFlagsList.java)
		* 二叉树遍历
			* [前序遍历](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/visit/PreOrderVisit.java)
			* [中序遍历](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/visit/MidOrderVisit.java)
			* [后序遍历](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/visit/PostOrderVisit.java)
			* [层序遍历](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/visit/LevelOrderVisit.java)
			* [逆转指针法遍历](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/visit/ReversePointerVisit.java)
		* [堆和堆排序](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/heap/Heap.java)
		* [穿线树和穿线排序](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/thread/ThreadTree.java)
		* 查找树
			* [构建最佳查找树的一般算法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/searchtree/BestSearchTree.java)
			* [构建最小加权外部路径 - Huffman算法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/searchtree/HuffmanTree.java)
			* [构建最小加权外部路径并保持结点顺序 - Hu-Tucker算法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/bitree/searchtree/HuTuckerBestSearchTree.java)
	* 解答树
		* [背包问题](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/answertree/BagProblem.java)
		* [皇后问题](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/tree/answertree/NQueensProblem.java)
* 图
	* [深度优先遍历，广度优先遍历](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/graph/Graph.java)
	* [无向图的最小生成树，Prim算法和Kruskal算法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/graph/NonDirGraph.java)
	* [有向图单个顶点到其它顶点的最短距离，Dijkstra算法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/graph/DirGraph.java)
	* [有向图各个顶点之间的最短距离，Floyd算法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/graph/DirGraph.java)
	* [有向图拓扑排序算法](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/graph/DirGraph.java)
	* [有向图的关键路径](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/graph/DirGraph.java)
* 零碎
	* [多项式中缀变后缀](https://github.com/JackyZhangFuDan/Algorithm/blob/master/src/algorithm/Polynomial.java)

