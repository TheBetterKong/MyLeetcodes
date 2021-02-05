/******************************* Java：三数之和 *******************************/
// 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
// 复的三元组。
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
//输入：nums = [0]
//输出：[]
// 
//
//
// 提示： 
//
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 2936 👎 0


/******************************* 题目思路 *******************************/
// 还是同样从暴力破解的角度出发，就是将所有可能的情况遍历一遍，找出和为 0 的组合
// 现在就会有两个问题：① 这样遍历的组合情况太多，可能超时；② 如何避免重复；
// 先看第二个问题，要避免重复，那最好的办法就是按照某种顺序来遍历，而有顺序的前提就是，数组有序，
// 所有，要做的第一步就是：对数组排序
// 接下来就是遍历，首先一惯的办法，先定义一个从头到尾的 first 指针。接下来就是，另外两个指针，在 first 指针后面遍历。总计 3 层循环；
// 针对上述过程，有两个有趣的现象：
//      1. 每一次循环中，有且只会找到一组符合 “和为 0” 的三元组。
//      2. 数组已经排序，如果 first 指针已定，那么 second 和 third 指针他们是对立关系（一个增一个减），
//         这就意味着我们可以用上 “双指针” 的思想（时间复杂度可从 O(n^2) 变为 O(n)）；
//
// 就上述分析，整理一下完整的思路：
// 1. 对数组排序
// 2. 定义 first 指针从头开始遍历，作为第一重循环
// 3. 在第一重循环下，利用 “双指针” 的思想找到满足条件的组合
// 有一个注意点就是：为了避免重复，需要在每一次循环中，加上重复性检测，如果连续两次遍历的数值相同，则跳过；
// 时间复杂度从 O(n^3) 降为 O(n^2)

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class P15_ThreeSum {
    public static void main(String[] args) {
        Solution solution = new P15_ThreeSum().new Solution();
        // TO TEST
        int[] nums = {1,2,-2,-1};
        List<List<Integer>> answer = solution.threeSum(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<List<Integer>>();

            for (int first = 0; first < len; first++) {
                // 重复性判别
                if (first > 0 && nums[first] == nums[first-1]) {
                    continue;
                }

                int third = len - 1;
                int target = -nums[first];
                // 双指针思想进一步查找
                for (int second = first + 1; second < third; second ++) {
                    // 重复性检查
                    if (second > first + 1 && nums[second] == nums[second-1]) {
                        continue;
                    }
                    // 遍历 third 指针，使之在 second 的右侧并且处在 second + third 最接近 target 的位置
                    while (second < third && nums[second] + nums[third] > target) {
                        third --;
                    }
                    // 对满足 target 的组合，加入到 result 里
                    if (second < third && nums[second] + nums[third] == target) {
                        List<Integer> ans = new ArrayList<Integer>();
                        ans.add(nums[first]);
                        ans.add(nums[second]);
                        ans.add(nums[third]);
                        res.add(ans);
                    }
                }
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n^2)
// 空间复杂度：空间复杂度：O(logN)。我们忽略存储答案的空间，额外的排序的空间复杂度为 O(logN)。
// 然而我们修改了输入的数组 nums，在实际情况下不一定允许，因此也可以看成使用了一个额外的数组存储了 nums 的副本并进行排序，空间复杂度为 O(N)。
