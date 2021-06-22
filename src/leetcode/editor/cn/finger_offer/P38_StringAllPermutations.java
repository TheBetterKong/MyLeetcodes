/******************************* Java：字符串的排列 *******************************/
// 输入一个字符串，打印出该字符串中字符的所有排列。
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
//
// 示例: 
//
// 输入：s = "abc"
// 输出：["abc","acb","bac","bca","cab","cba"]
//
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 回溯算法 
// 👍 347 👎 0


/******************************* 题目思路 *******************************/
// 假设求：func(a, b ,c)
//      a x func(b, c), b x func(a, c), c x func(a x b)
//      func(b, c) = b x func(c) + c x func(b)
//      func(b) = {c}
//
// 采用递归的思想：
//      循环提取字符串的字符，作为新字符串的首字符，递归排序新字符串除首字符外的子字符串，然后合并出结果，
//      最后对结果进行排序；
//
// 有几个关键点：
//      避免重复操作；
//      每次递归得到结果后，记得恢复字符串为原始状态；
//      最终的排序不要忘了；
//
// 基于此思路，我们就可以利用 回溯法 求得所有解。
//
// 再想想我们以前，做过的 P31，可以在 O(n) 的时间内求得 “字典序中下一个更大的排列”
// 那本题，也就相当于不断求这个下一个排列，直至不再存在时，即可

/******************************* 题目代码 *******************************/
package leetcode.editor.cn.finger_offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class P38_StringAllPermutations {
    public static void main(String[] args) {
        Solution solution = new P38_StringAllPermutations().new Solution();
        // TO TEST
        String s = "abc";
        String[] answer = solution.permutation(s);
        System.out.println(Arrays.toString(answer));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String[] permutation(String s) {
            List<String> ret = new ArrayList<String>();
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            do {
                ret.add(new String(arr));
            } while (nextPermutation(arr));
            int size = ret.size();
            String[] retArr = new String[size];
            for (int i = 0; i < size; i++) {
                retArr[i] = ret.get(i);
            }
            return retArr;
        }

        private boolean nextPermutation(char[] nums) {
            int i = nums.length - 1;
            // 确定需要关注的数列起始位置
            while(i > 0 && nums[i-1] >= nums[i]) {
                i--;
            }
            if (i > 0) {
                int j = nums.length - 1;
                while (j > i-1 && nums[i-1] >= nums[j]) {
                    j--;
                }
                swap(nums, i-1, j);
            } else {
                return false;
            }
            reverse(nums, i);
            return true;
        }

        private void swap(char[] nums, int i, int j) {
            char temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }

        private void reverse(char[] nums, int i) {
            int begin = i, end = nums.length - 1;
            while (begin < end) {
                swap(nums, begin, end);
                begin++;
                end--;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......