/******************************* Java：两数之和 *******************************/
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
// 因为 nums[0] + nums[1] = 2 + 7 = 9
// 所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 9169 👎 0


/******************************* 题目思路 *******************************/
// 本题最简单的思路就是挨个遍历，暴力解题法，但是很明显这种方法的时间复杂度为 O(n^2)，似乎不那么可取。
// 审视一下本题时间上的开销主要来源两个地方：1.遍历    2.查找
//      遍历肯定是必不可少的步骤，那么剩下的就是从查找上来改进，
//      但是本题的数组是无序的，除掉暴力解题使用的顺序查找法，那就是使用分块查找，这样时间复杂度将改进为 O(nlogn)，
//      分块查找算法实现比较复杂，但是别忘了，还有一种简单的用空间换时间的哈希查找，利用 hashmap 就能轻松实现目标；
//      （知识扩展：关注一下 hashmap 底层原理，弄清哈希查找加速的原理，可参考：
//          http://www.thebetterkong.cn/2020/06/15/DataStructure-Algorithm/SearchingAlgorithm/）
// 这里有个使用技巧：将 i 和 nums[i] 反存，即：hashmap 中 key = nums[i]，val = i

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class P1_TwoSum {
    public static void main(String[] args) {
        Solution solution = new P1_TwoSum().new Solution();
        // TO TEST
        int []num = {1, 7, 11, 15};
        int target = 9;
        int []ans;
        ans = solution.twoSum(num,target);
        System.out.println(Arrays.toString(ans));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer,Integer> map = new HashMap<>();
            for(int i = 0; i < nums.length; i++){
                int val = target - nums[i];
                if(map.containsKey(val)){
                    return new int[] {i , map.get(val)};
                }
                map.put(nums[i],i);
            }
            throw new IllegalArgumentException("No solution !");
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 不考虑，hashmap 的查找时间，时间复杂度 O(n)；
// 但是带来了一定得空间开销；