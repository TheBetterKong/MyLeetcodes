/******************************* Java：快乐数 *******************************/
// 编写一个算法来判断一个数 n 是不是快乐数。
//
// 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
// 如果 可以变为 1，那么这个数就是快乐数。
//
// 如果 n 是快乐数就返回 True ；不是，则返回 False 。 
//
// 
//
// 示例： 
//
// 输入：19
// 输出：true
//解释：
// 1^2 + 9^2 = 82
// 8^2 + 2^2 = 68
// 6^2 + 8^2 = 100
// 1^2 + 0^2 + 0^2 = 1
// 
// Related Topics 哈希表 数学 
// 👍 475 👎 0


/******************************* 题目思路 *******************************/
// 本题，我们首先得达成一个共识，对于任意一个数 n，我们需要不断的计算它各个位的平方和，以此来进行下一轮迭代。
//      所以，为了便于判断，我们第一步要做的就是写一个 getNext 函数，来完成这一过程。
// 接下里，怎么判断一个数是否为快乐数？
//      如果 getNext 最终会迭代到 1，那么这个数就是快乐数了；
//      但是如果 getNext 一直迭代，我们怎么知道算法结束了？又或者它可能在以后的时候迭代到 1？
//      仔细进行一下，数学分析就知道，返回 false 的情况，一定是 getNext 最终成了环。
//      这样，问题就转换成了，链表里怎样判断环？“快慢指针”

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P202_HappyNumber {
    public static void main(String[] args) {
        Solution solution = new P202_HappyNumber().new Solution();
        // TO TEST
        int n = 19;
        boolean answer = solution.isHappy(n);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isHappy(int n) {
            int slow = n;
            int fast = getNext(n);
            while (fast != 1 && fast != slow) {
                slow = getNext(slow);
                fast = getNext(getNext(fast));
            }
            return fast == 1;
        }

        private int getNext(int x) {
            int sum = 0;
            while (x > 0) {
                int k = x % 10;
                x /= 10;
                sum += k * k;
            }
            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(log n)
// 空间复杂度 O（1）
