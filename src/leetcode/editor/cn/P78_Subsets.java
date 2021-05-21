/******************************* Java：子集 *******************************/
// 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
//
// 示例 1： 
//
// 输入：nums = [1,2,3]
// 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 输入：nums = [0]
// 输出：[[],[0]]
// 
//
// 提示： 
//
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯算法 
// 👍 1177 👎 0


/******************************* 题目思路 *******************************/
// 回溯的关键在于某个值，取还是不取

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class P78_Subsets {
    public static void main(String[] args) {
        Solution solution = new P78_Subsets().new Solution();
        // TO TEST
        int[] nums = {1,2,3};
        List<List<Integer>> answer = solution.subsets(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<Integer> path = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> subsets(int[] nums) {
            dfs(0, nums);
            return ans;
        }

        private void dfs(int cur, int[] nums) {
            if (cur == nums.length) {
                ans.add(new ArrayList<Integer>(path));
                return;
            }
            path.add(nums[cur]);
            dfs(cur + 1, nums);
            path.remove(path.size() - 1);
            dfs(cur + 1, nums);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n×2^n)。一共 2^n2 n 个状态，每种状态需要 O(n) 的时间来构造子集。
// 空间复杂度：O(n)。临时数组 t 的空间代价是 O(n)，递归时栈空间的代价为 O(n)。
