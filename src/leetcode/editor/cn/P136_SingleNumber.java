/******************************* Java：只出现一次的数字 *******************************/
// 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
//
// 说明： 
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
//
// 示例 1: 
//
// 输入: [2,2,1]
// 输出: 1
// 
//
// 示例 2: 
//
// 输入: [4,1,2,1,2]
// 输出: 4
// Related Topics 位运算 哈希表 
// 👍 1874 👎 0


/******************************* 题目思路 *******************************/
// 典型的异或问题，这里直接引用官方的题解，如下：
//
// 如果不考虑时间复杂度和空间复杂度的限制，这道题有很多种解法，可能的解法有如下几种。
//      (1)使用集合存储数字:
//              遍历数组中的每个数字，如果集合中没有该数字，则将该数字加入集合，如果集合中已经有该数字，则将该数字
//              从集合中删除，最后剩下的数字就是只出现一次的数字。
//     （2）使用哈希表存储每个数字和该数字出现的次数：
//              遍历数组即可得到每个数字出现的次数，并更新哈希表，最后遍历哈希表，得到只出现一次的数字。
//     （3）使用集合存储数组中出现的所有数字，并计算数组中的元素之和：
//              由于集合保证元素无重复，因此计算集合中的所有元素之和的两倍，即为每个元素出现两次的情况下的元素之和。
//              由于数组中只有一个元素出现一次，其余元素都出现两次，因此用集合中的元素之和的两倍减去数组中的元素之和，剩下的数就是数组中只出现一次的数字。
//      上述三种解法都需要额外使用 O(n) 的空间，其中 n 是数组长度。
//
// 如何才能做到线性时间复杂度和常数空间复杂度呢？
//
//     答案是使用位运算。对于这道题，可使用异或运算 ⊕。异或运算有以下三个性质。
//        任何数和 0 做异或运算，结果仍然是原来的数，即 a ⊕ 0 = a。
//        任何数和其自身做异或运算，结果是 0，即 a ⊕ a = 0。
//        异或运算满足交换律和结合律，即 a ⊕ b ⊕ a = b ⊕ a ⊕ a = b ⊕ ( a ⊕ a ) = b ⊕ 0 = b。
//
//     假设数组中有 2m+1 个数，其中有 m 个数各出现两次，一个数出现一次。
//     令 a_{1}、a_{2}、…、a_{m} 为出现两次的 m 个数，
//     a_{m+1} 为出现一次的数。
//     根据性质 3，数组中的全部元素的异或运算结果总是可以写成如下形式：
//        (a1 ⊕ a1 ) ⊕ ( a2 ⊕ a2 ) ⊕ ⋯ ⊕ ( am ⊕ am ) ⊕ am+1
//     根据性质 2 和性质 1，上式可化简和计算得到如下结果：
//        0 ⊕ 0 ⊕ ⋯ ⊕ 0 ⊕ am+1 = am+1
//     因此，数组中的全部元素的异或运算结果即为数组中只出现一次的数字。


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P136_SingleNumber {
    public static void main(String[] args) {
        Solution solution = new P136_SingleNumber().new Solution();
        // TO TEST
        int[] numns = {2,2,1};
        int answer = solution.singleNumber(numns);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int singleNumber(int[] nums) {
            int tag = 0;
            for (int num : nums) {
                tag = tag ^ num;
            }
            return tag;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)，对数组遍历一次
// 空间复杂度：O(1)