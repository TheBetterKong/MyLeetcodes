/******************************* Java：全排列 *******************************/
// 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
//
// 示例 1： 
//
// 输入：nums = [1,2,3]
// 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 输入：nums = [0,1]
// 输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 输入：nums = [1]
// 输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 回溯算法 
// 👍 1343 👎 0


/******************************* 题目思路 *******************************/
// 本题又是一种典型的回溯法
// 首先，定义一个回溯函数：bfs(nums_list, cur, res)，
//      任一时刻，数组已经乱序为了 nums_list，将数组一分为二，左半部分 [0, cur-1] 已经排列完毕，右半部分是还未参与排列的元素
//      此时需要确定位置 cur 处的数：那么将 [cur, n] 的所有数都在该位置放置一次（换位置实现）
// 接下来就是进行：bfs（nums_list, cur+1, res)

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.*;

class P46_Permutations {
    public static void main(String[] args) {
        Solution solution = new P46_Permutations().new Solution();
        // TO TEST
        int[] nums = {1,2,3};
        List<List<Integer>> answer = solution.permute(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();

            // 将 nums 数组转换为 list，便于后面插入到 res 里
            List<Integer> nums_list = new ArrayList<>();
            for (int num : nums) {
                nums_list.add(num);
            }

            int lenth = nums.length;
            bfs(nums_list, 0, res, lenth);
            return res;
        }

        private void bfs(List<Integer> nums_list, int cur, List<List<Integer>> res, int lenth) {
            // 所有的元素都已经排序
            if (cur == lenth) {
                res.add(new ArrayList<Integer>(nums_list));
            }
            // 将右半部分未排序的数加入进来排序（[i,n] 的每个数都放在 cur 位置一次）
            for (int i = cur; i < lenth; i++) {
                // 先将位置 i 位置的数挪至 cur 位置
                Collections.swap(nums_list, cur, i);
                // 递归往下进行
                bfs(nums_list, cur+1, res, lenth);
                // 再还原为原来的 nums_list 以便进行下一个元素换位
                Collections.swap(nums_list, i, cur);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n x n!)
//      上述回溯的过程，其实就相当于是对数的每一个元素构建树，这样的树有 n 棵，每棵树的第 k 层，任意一个元素有 n-k 个子节点；
//      这样说可能不够直观，想想 k 排列有多少种情况 P(n,n) = n!，整个递归过程的相当于 /sum_{k=1}^n P(n,k)
//      再加上，每一个结果需要花费 O(n) 的时间拷贝当前排列结果至 res，所以总时间为 O(n x n!)
// 空间复杂度：O(n)
//      主要来源于递归的深度，就像上面的分析一样，树往下分叉，最多分 n 层，也即递归的深度最多为 n