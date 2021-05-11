/******************************* Java：组合总和 *******************************/
// 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1： 
//
// 输入：candidates = [2,3,6,7], target = 7,
// 所求解集为：
// [
//  [7],
//  [2,2,3]
// ]
// 
//
// 示例 2： 
//
// 输入：candidates = [2,3,5], target = 8,
// 所求解集为：
// [
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
// ]
//
// 
//
// 提示： 
//
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都是独一无二的。 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯算法 
// 👍 1337 👎 0


/******************************* 题目思路 *******************************/
// 拿到本题，就可以发现这是典型的回溯法题目，毕竟回溯法的特点就是每走一次都可以判断结果是否是对的。
//
// 所以，接下来需要我们确定每一个时刻点的状态：
// bfs(candidates, target, res, path, idx, sum)：
//      表示数组 candidates 从 [0-idx] 已经完成了遍历；得到的解路径集合为 res，当前遍历的路径为 path，路径上所有数的和为 sum；
//      接下来还需要从 [idx,n] 遍历 candidates，递归求解 path 并将符合要求的 path 加入到 res 中；
//
// 注意回溯法是相当于是反向递归，只有满足要求的就加入解集，而动态规划则是从前往后，需要记录中间结果以便于下一层递归；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class P39_CombinationSum {
    public static void main(String[] args) {
        Solution solution = new P39_CombinationSum().new Solution();
        // TO TEST
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> answer = solution.combinationSum(candidates, target);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            Arrays.sort(candidates); // 提前对数组排序以减少回溯次数

            bfs(candidates, target, res, path, 0, 0);
            return res;
        }

        private void bfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> path, int idx, int sum) {
            if (sum == target) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = idx; i < candidates.length; i ++) {
                int cur_sum = candidates[i] + sum;
                if (cur_sum <= target) {
                    path.add(candidates[i]);
                    bfs(candidates, target, res, path, i, cur_sum);
                    path.remove(path.size() - 1);
                } else {
                    break;
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(S)，所有可行解的长度和，算法里对数组进行了排序，并加入了判断 cur_sum <= target，这样可以减少不必要的递归次数
// 空间复杂度：O(target），取决于递归栈的深度，最坏情况下需要递归 O(target) 层