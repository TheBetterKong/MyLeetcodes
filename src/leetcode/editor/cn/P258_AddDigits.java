/******************************* Java：各位相加 *******************************/
// 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
//
// 示例: 
//
// 输入: 38
// 输出: 2
// 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
// 
//
// 进阶: 
// 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
// Related Topics 数学 
// 👍 294 👎 0


/******************************* 题目思路 *******************************/
// 堆的个数就是题目要求的各个位上的数之和。设求堆个数的函数为F(N),那么有：
// F(123) = (100 - 99) + (10 - 9) + (10 - 9) + 1 + 1 + 1 = 123 - 99x1 -9x2 = 123 - 9x13
// 也就是每个类别的堆，去掉9或者99，就能得到堆个数，假如是(1000)堆，去掉999就行了，然后把所有加起来，得到结果，那么我们知道有:
// F(N) = N - 9xM1
// 那么问题来了，假如(N - 9xM1)大于10，是不满足要求的，但(N - 9xM1)也是一个数，我们继续可以调用F(N)这个函数：
// F(N - 9xM) = N - 9xM1 - 9xM2
// 按照题目设定，只要是大于10，就继续在得到的结果上操作，则有:
// F(N) = F(N - 9xM) = N - 9xM1 -9xM2 = ...
// 依次类推，直到满足条件:
// F(N) = N - 9xM,其中M = (M1+M2+M3+...)；并且结果小于10且大于0，因为堆的个数不可能为0个，这就是对9求余数的的算式，


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P258_AddDigits {
    public static void main(String[] args) {
        Solution solution = new P258_AddDigits().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int addDigits(int num) {
            return (num - 1) % 9 + 1;   // 不直接 % 9，是为了避免计算 9
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......
