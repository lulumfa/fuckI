https://www.1point3acres.com/bbs/thread-466587-1-1.html
面试题不是leetcode题
可以问下楼主是什么思路吗？预先生成一个包含0-99的shuffle过的数组？

call math.random() 一个0-1的随机概率，生成0-99范围的随机Integer，随机生成的整数不能重复，O(1) time.


是的存arraylist，random * list.size得到index，输出index对应的值，remove index当前的值


不用remove，和末尾的value换一下位置就行了，然后递减还available的arraylist size就行了













