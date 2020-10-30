/******************************* Java：存在重复元素 *******************************/
// 给定一个整数数组，判断是否存在重复元素。
//
// 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,3,1]
// 输出: true
//
// 示例 2: 
//
// 输入: [1,2,3,4]
// 输出: false
//
// 示例 3: 
//
// 输入: [1,1,1,3,3,4,3,2,4,2]
// 输出: true
// Related Topics 数组 哈希表 
// 👍 294 👎 0


/******************************* 题目思路 *******************************/
// 既然是重复元素，如果数组排好序，那么重复元素一定在一起。即使采用最好的排序算法，此时的时间复杂仍是 O(n^2logn)
// 再就是利用之前用过的哈希集来提速，在哈希集里每一次 search 和 insert 的时间复杂度为 O(logn)，那么最后算法的时间复杂度就是 O(nlogn)
// 注意，这里利用的是 hashset ，它相比于 hashmap ，不允许存有重复元素，在此处更加适用

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class P217_ContainsDuplicate {
    public static void main(String[] args) {
        Solution solution = new P217_ContainsDuplicate().new Solution();
        // TO TEST
        int []nums = {1,1,1,3,3,4,3,2,4,2};
        boolean answer = solution.containsDuplicate(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<>(nums.length);
            for(int num : nums){
                if(set.contains(num)) return true;
                set.add(num);
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(nlogn)
// 空间复杂度 O(n)