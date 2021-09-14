/******************************* Java：错误的集合 *******************************/
//集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有
//一个数字重复 。 
//
// 给定一个数组 nums 代表了集合 S 发生错误后的结果。 
//
// 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2,4]
//输出：[2,3]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 104 
// 1 <= nums[i] <= 104 
// 
// Related Topics 位运算 数组 哈希表 排序 
// 👍 215 👎 0


/******************************* 题目思路 *******************************/

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class P645_SetMismatch {
    public static void main(String[] args) {
        Solution solution = new P645_SetMismatch().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findErrorNums(int[] nums) {
            int[] res = new int[2];
            Set<Integer> set = new HashSet<Integer>();

            // 正常时的元素和
            int k = nums.length;
            int sum = (k * (k + 1)) / 2;

            // 有丢失，有重复的元素和
            int sum_nums = 0;
            for (int num : nums) {
                sum_nums += num;
                set.add(num);
            }

            // 去重后的元素和
            int sum_set = 0;
            for (int num : set) {
                sum_set += num;
            }

            res[0] = sum_nums - sum_set;    // 重复的元素
            res[1] = sum - sum_set;         // 缺失的元素

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......